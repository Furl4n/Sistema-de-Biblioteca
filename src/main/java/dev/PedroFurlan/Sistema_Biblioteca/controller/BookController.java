package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.BookResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.AddBookRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    @PostMapping("/new")
    public ResponseEntity<BookResponseDTO> newBook(@RequestBody AddBookRequestDTO data){
        BookResponseDTO response = service.addBook(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<BookResponseDTO>> getAll(){
        List<BookResponseDTO> response = service.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/title/{title}")
        public ResponseEntity<List<BookResponseDTO>> findBook(@PathVariable String title){
        List<BookResponseDTO> response = service.findByName(title);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        if(service.deleteById(id)) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

}
