package dev.PedroFurlan.Sistema_Biblioteca.repository;

import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUser(User user);
}
