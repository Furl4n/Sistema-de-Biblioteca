package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.BookResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.AddBookRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Books", description = "Books management endpoints")
public class BookController {

    private final BookService service;

    @Operation(
            summary = "Create a new book",
            description = "Creates a new book in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody AddBookRequestDTO data){
        BookResponseDTO response = service.addBook(data);

        URI uri = URI.create("/books/" + response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAll(){
        List<BookResponseDTO> response = service.getAll();
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Find a book by title",
            description = "Find a book by title in the library."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book found successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/title/{title}")
        public ResponseEntity<List<BookResponseDTO>> findBook(@PathVariable String title){
        List<BookResponseDTO> response = service.findByName(title);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete a book by ID",
            description = "Delete a book in the library by ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        service.deleteById(id) ;
        return ResponseEntity.noContent().build();
    }
}
