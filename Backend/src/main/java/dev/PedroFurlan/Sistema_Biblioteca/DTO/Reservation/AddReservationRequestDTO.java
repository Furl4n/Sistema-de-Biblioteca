package dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record AddReservationRequestDTO(
        @Schema(
                description = "Book ID",
                example = "125"
        )
        @NotNull(message = "Book id is required.")
        @Positive(message = "Book id must be greater than zero.")
        Long bookId,

        @Schema(
                description = "Expiration date",
                example = "2007-12-03"
        )
        @NotNull(message = "Expiration date is required.")
        @FutureOrPresent(message = "Expiration date must be today or in the future.")
        LocalDate expirationDate
) {
}
