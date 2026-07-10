package dev.PedroFurlan.Sistema_Biblioteca.DTO.Book;

import dev.PedroFurlan.Sistema_Biblioteca.model.Book.Book;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.StatusBook;
import io.swagger.v3.oas.annotations.media.Schema;

public record BookResponseDTO(
        @Schema(
                description = "Book ID",
                example = "125"
        )
        Long id,

        @Schema(
                description = "Book title",
                example = "Clean Code"
        )
        String title,

        @Schema(
                description = "Book author",
                example = "Robert C. Martin"
        )
        String author,

        @Schema(
                description = "Publication year",
                example = "2008"
        )
        int year,

        @Schema(
                description = "Book genre",
                example = "Programming"
        )
        String genre,

        @Schema(
                description = "Book status",
                example = "RESERVED"
        )
        StatusBook status
){

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
