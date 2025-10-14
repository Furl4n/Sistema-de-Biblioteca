package dev.PedroFurlan.Sistema_Biblioteca.repository;

import dev.PedroFurlan.Sistema_Biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);
}
