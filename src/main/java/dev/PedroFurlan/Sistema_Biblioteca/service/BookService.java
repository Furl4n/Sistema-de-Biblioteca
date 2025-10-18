package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.model.Book;
import dev.PedroFurlan.Sistema_Biblioteca.model.enums.StatusBook;
import dev.PedroFurlan.Sistema_Biblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Book addBook(Book book) {
        book.setStatus(StatusBook.AVAILABLE);
        return repository.save(book);
    }

    public List<Book> getAll() {return repository.findAll();}

    public List<Book> findByName(String title) {
        return repository.findByTitle(title);
    }

    public boolean deleteById(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        } else return false;
    }
}
