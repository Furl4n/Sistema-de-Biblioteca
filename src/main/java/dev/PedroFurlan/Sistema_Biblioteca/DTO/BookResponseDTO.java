package dev.PedroFurlan.Sistema_Biblioteca.DTO;

import dev.PedroFurlan.Sistema_Biblioteca.model.Book.Book;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.StatusBook;

public record BookResponseDTO(Long id, String title, String author, int year, String genre, StatusBook status){

    public static BookResponseDTO create(Book book){
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getGenre(),
                book.getStatus()
        );
    }
}
