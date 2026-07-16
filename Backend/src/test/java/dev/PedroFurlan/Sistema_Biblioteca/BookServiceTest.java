package dev.PedroFurlan.Sistema_Biblioteca;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.AddBookRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.BookResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.infra.exception.ResourceNotFoundException;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.Book;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.StatusBook;
import dev.PedroFurlan.Sistema_Biblioteca.repository.BookRepository;
import dev.PedroFurlan.Sistema_Biblioteca.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest  {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    void shouldCreateBookSuccessfully() {
        //Arrange
        AddBookRequestDTO dto =
                new AddBookRequestDTO(
                        "Clean Code",
                        "Robert C. Martin",
                        2008,
                        "Programming"
                );

        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setTitle(dto.title());
        savedBook.setAuthor(dto.author());
        savedBook.setGenre(dto.genre());
        savedBook.setYear(dto.year());
        savedBook.setStatus(StatusBook.AVAILABLE);

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        //Act
        BookResponseDTO response = bookService.addBook(dto);

        //Assert
        assertNotNull(response);
        assertEquals(savedBook.getTitle(), response.title());
        assertEquals(savedBook.getAuthor(), response.author());
        assertEquals(savedBook.getYear(), response.year());
        assertEquals(savedBook.getGenre(), response.genre());
        assertEquals(StatusBook.AVAILABLE, response.status());


        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void shouldReturnAllBooks() {
        //Arrange
        Book book1 = new Book();
        book1.setTitle("Clean Code");

        Book book2 = new Book();
        book2.setTitle("Java");

        //Act
        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));

        List<BookResponseDTO> books = bookService.getAll();

        //Assert
        assertEquals(2, books.size());
        assertEquals("Clean Code", books.get(0).title());
        assertEquals("Java", books.get(1).title());

        verify(bookRepository).findAll();
    }

    @Test
    void shouldReturnEmptyList() {
        //Arrange
        when(bookRepository.findAll()).thenReturn(List.of());

        //Act
        List<BookResponseDTO> books = bookService.getAll();

        //Assert
        assertTrue(books.isEmpty());

        verify(bookRepository).findAll();
    }

    @Test
    void shouldFindBooksByTitle() {
        //Arrange
        Book book = new Book();
        book.setTitle("Java");

        when(bookRepository.findByTitle("Java")).thenReturn(List.of(book));

        //Act
        List<BookResponseDTO> books = bookService.findByName("Java");

        //Assert
        assertEquals(1, books.size());
        assertEquals("Java", books.get(0).title());

        verify(bookRepository).findByTitle("Java");
    }

    @Test
    void shouldReturnEmptyListWhenTitleDoesNotExists() {
        //Arrange
        when(bookRepository.findByTitle("Python")).thenReturn(List.of());

        //Act
        List<BookResponseDTO> books = bookService.findByName("Python");

        //Assert
        assertTrue(books.isEmpty());

        verify(bookRepository).findByTitle("Python");
    }

    @Test
    void shouldDeleteBook() {
        //Arrange
        Book book = new Book();
        book.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        //Act
        bookService.deleteById(1L);

        //Assert
        verify(bookRepository).delete(book);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingBook() {
        //Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        //Act
        assertThrows(ResourceNotFoundException.class, ()->bookService.deleteById(1L));

        //Assert
        verify(bookRepository, never()).delete(any());
    }
}
