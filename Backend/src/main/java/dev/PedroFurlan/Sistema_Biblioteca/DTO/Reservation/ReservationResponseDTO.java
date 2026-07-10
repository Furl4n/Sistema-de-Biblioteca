package dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.BookResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.StatusReservation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record ReservationResponseDTO(
        @Schema(
                description = "Reservation ID",
                example = "125"
        )
        Long id,

        @Schema(
                description = "Reservation date",
                example = "2007-12-03"
        )
        LocalDate reservationDate,

        @Schema(
                description = "Expiration date",
                example = "2007-12-03"
        )
        LocalDate expirationDate,

        @Schema(
                description = "Reservation status",
                example = "RETURNED"
        )
        StatusReservation status,

        @Schema(
                description = "Book",
                example = "Book response"
        )
        BookResponseDTO book
) {

    public static ReservationResponseDTO create(Reservation reservation){
        return new ReservationResponseDTO(reservation.getId(),
                reservation.getReservationDate(),
                reservation.getExpirationDate(),
                reservation.getStatus(),
                BookResponseDTO.create(reservation.getBook())
        );
    }
}
