package dev.PedroFurlan.Sistema_Biblioteca.DTO.Book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AddBookRequestDTO(@NotEmpty(message = "Title is required.")
                                String title,
                                @NotEmpty(message = "Author is not required")
                                String author,
                                @NotNull(message = "Publication year is required.")
                                Integer year,
                                @NotBlank(message = "Genre is required.")
                                String genre) {
}
