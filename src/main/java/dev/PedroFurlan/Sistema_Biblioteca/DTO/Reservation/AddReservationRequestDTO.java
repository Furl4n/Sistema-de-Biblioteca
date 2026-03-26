package dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation;

import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.StatusReservation;

import java.time.LocalDate;

public record AddReservationRequestDTO(Long bookId, LocalDate reservationDate, LocalDate expirationDate, StatusReservation status) {
}
