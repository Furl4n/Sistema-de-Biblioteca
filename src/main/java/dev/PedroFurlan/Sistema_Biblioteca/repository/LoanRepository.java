package dev.PedroFurlan.Sistema_Biblioteca.repository;

import dev.PedroFurlan.Sistema_Biblioteca.model.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Reservation> findByUserId(Long userId);
}
