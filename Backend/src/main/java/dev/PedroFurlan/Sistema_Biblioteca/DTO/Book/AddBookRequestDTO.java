package dev.PedroFurlan.Sistema_Biblioteca.DTO.Book;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddBookRequestDTO(
        @Schema(
        description = "Book title",
        example = "Clean Code"
        )
        @NotBlank(message = "Title is required.")
        @Size(max = 150, message = "Title must have at most 150 characters.")
        String title,

        @Schema(
                description = "Book author",
                example = "Robert C. Martin"
        )
        @NotBlank(message = "Author is required")
        @Size(max = 100, message = "Author must have at most 100 characters.")
        String author,

        @Schema(
                description = "Publication year",
                example = "2008"
        )
        @NotNull(message = "Publication year is required.")
        Integer year,

        @Schema(
                description = "Book genre",
                example = "Programming"
        )
        @NotBlank(message = "Genre is required.")
        @Size(max = 50, message = "Genre must have at most 50 characters.")
        String genre) {
}
