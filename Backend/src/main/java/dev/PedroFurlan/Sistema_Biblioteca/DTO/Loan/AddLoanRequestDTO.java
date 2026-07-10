package dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
public record AddLoanRequestDTO(@NotNull(message = "Book id is required.")
                                @Positive(message = "Book id must be greater than zero.")
                                Long bookId,
                                @NotNull(message = "Expected return date is required.")
                                @Future(message = "Expected return date must be in the future.")
                                LocalDate dueDate){
}
