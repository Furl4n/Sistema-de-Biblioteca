package dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan;

import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.StatusLoan;

import java.time.LocalDate;
public record AddLoanRequestDTO(Long bookId, LocalDate loanDate, LocalDate dueDate, StatusLoan status){
}
