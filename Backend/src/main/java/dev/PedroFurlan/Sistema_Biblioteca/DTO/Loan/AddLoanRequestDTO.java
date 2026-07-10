package dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
public record AddLoanRequestDTO(
        @Schema(
                description = "Book ID",
                example = "125"
        )
        @NotNull(message = "Book id is required.")
        @Positive(message = "Book id must be greater than zero.")
        Long bookId,

        @Schema(
                description = "Due date",
                example = "2007-12-03"
        )
        @NotNull(message = "Expected return date is required.")
        @Future(message = "Expected return date must be in the future.")
        LocalDate dueDate
){
}
