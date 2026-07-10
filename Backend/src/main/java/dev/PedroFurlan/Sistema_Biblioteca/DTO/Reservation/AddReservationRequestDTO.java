package dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record AddReservationRequestDTO( @NotNull(message = "Book id is required.")
                                        @Positive(message = "Book id must be greater than zero.")
                                        Long bookId,
                                        @NotNull(message = "Expiration date is required.")
                                        @FutureOrPresent(message = "Expiration date must be today or in the future.")
                                        LocalDate expirationDate) {
}
