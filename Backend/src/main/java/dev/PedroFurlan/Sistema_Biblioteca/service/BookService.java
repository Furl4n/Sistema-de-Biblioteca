package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.BookResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.AddBookRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.Book;
import dev.PedroFurlan.Sistema_Biblioteca.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public BookResponseDTO addBook(AddBookRequestDTO data) {

        Book book = new Book();

        book.setTitle(data.title());
        book.setAuthor(data.author());
        book.setGenre(data.genre());
        book.setYear(data.year());

        Book newBook = repository.save(book);

        return BookResponseDTO.create(newBook);
    }

    public List<BookResponseDTO> getAll() {
        List<Book> books = repository.findAll();

        return books.stream().map(BookResponseDTO::create).toList();
    }

    public List<BookResponseDTO> findByName(String title) {
        List<Book> books = repository.findByTitle(title);

        return books.stream().map(BookResponseDTO::create).toList();
    }

    public boolean deleteById(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        } else return false;
    }
}