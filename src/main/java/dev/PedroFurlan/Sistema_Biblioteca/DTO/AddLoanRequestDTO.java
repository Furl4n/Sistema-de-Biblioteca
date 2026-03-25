package dev.PedroFurlan.Sistema_Biblioteca.DTO;

import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.StatusLoan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddLoanRequestDTO {
    private String userId;
    private Long bookId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private StatusLoan status;
}
