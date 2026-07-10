package dev.PedroFurlan.Sistema_Biblioteca.DTO.Book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddBookRequestDTO(@NotBlank(message = "Title is required.")
                                @Size(max = 150, message = "Title must have at most 150 characters.")
                                String title,
                                @NotBlank(message = "Author is required")
                                @Size(max = 100, message = "Author must have at most 100 characters.")
                                String author,
                                @NotNull(message = "Publication year is required.")
                                Integer year,
                                @NotBlank(message = "Genre is required.")
                                @Size(max = 50, message = "Genre must have at most 50 characters.")
                                String genre) {
}
