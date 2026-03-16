package dev.PedroFurlan.Sistema_Biblioteca.DTO;

import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.StatusLoan;

import java.time.LocalDate;

public record LoanResponseDTO(Long id, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate, StatusLoan status, BookResponseDTO book) {

    public static LoanResponseDTO create(Loan loan){
        return new LoanResponseDTO(loan.getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getStatus(),
                BookResponseDTO.create(loan.getBook())
        );
    }
}
