package dev.PedroFurlan.Sistema_Biblioteca.repository;

import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserId(String userId);
}
