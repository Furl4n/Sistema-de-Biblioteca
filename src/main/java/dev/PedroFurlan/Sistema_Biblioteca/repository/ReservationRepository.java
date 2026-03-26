package dev.PedroFurlan.Sistema_Biblioteca.repository;

import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser(User user);
}
