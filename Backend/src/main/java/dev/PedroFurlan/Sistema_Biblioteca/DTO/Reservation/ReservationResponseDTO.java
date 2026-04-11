package dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.BookResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.StatusReservation;

import java.time.LocalDate;

public record ReservationResponseDTO(Long id, LocalDate reservationDate, LocalDate expirationDate, StatusReservation status, BookResponseDTO book) {

    public static ReservationResponseDTO create(Reservation reservation){
        return new ReservationResponseDTO(reservation.getId(),
                reservation.getReservationDate(),
                reservation.getExpirationDate(),
                reservation.getStatus(),
                BookResponseDTO.create(reservation.getBook())
        );
    }
}
