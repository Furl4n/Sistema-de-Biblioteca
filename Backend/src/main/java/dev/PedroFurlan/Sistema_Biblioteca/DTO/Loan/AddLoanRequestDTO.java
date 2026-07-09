package dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan;

import java.time.LocalDate;
public record AddLoanRequestDTO(Long bookId, LocalDate loanDate, LocalDate dueDate){
}
