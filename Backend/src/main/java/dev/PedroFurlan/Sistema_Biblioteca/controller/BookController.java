package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.BookResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.AddBookRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService service;

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

    @GetMapping("/title/{title}")
        public ResponseEntity<List<BookResponseDTO>> findBook(@PathVariable String title){
        List<BookResponseDTO> response = service.findByName(title);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        service.deleteById(id) ;
        return ResponseEntity.noContent().build();
    }
}
