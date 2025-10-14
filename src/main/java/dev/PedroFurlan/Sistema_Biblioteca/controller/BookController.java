package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.model.Book;
import dev.PedroFurlan.Sistema_Biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book newBook){
        Book book = service.addBock(newBook);
        return new ResponseEntity<>(book,HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Book>> getAll(){
        List<Book> books = service.getAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/get/title={title}")
        public ResponseEntity<List<Book>> findBook(@PathVariable String title){
        List<Book> books = service.findByName(title);
        if(books.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>(books, HttpStatus.FOUND);
        }
    }


}
