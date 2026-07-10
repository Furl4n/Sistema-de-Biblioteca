package dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Book.BookResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.StatusLoan;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record LoanResponseDTO(
        @Schema(
                description = "Loan ID",
                example = "125"
        )
        Long id,

        @Schema(
                description = "Loan date",
                example = "2007-12-03"
        )
        LocalDate loanDate,

        @Schema(
                description = "Due date",
                example = "2007-12-03"
        )
        LocalDate dueDate,

        @Schema(
                description = "Return date",
                example = "2007-12-03"
        )
        LocalDate returnDate,

        @Schema(
                description = "Loan status",
                example = "RETURNED"
        )
        StatusLoan status,

        @Schema(
                description = "Book",
                example = "Book response"
        )
        BookResponseDTO book,

        @Schema(
                description = "Days overdue",
                example = "7"
        )
        Long daysOverdue
) {

    public static LoanResponseDTO create(Loan loan, Long daysOverdue){
        return new LoanResponseDTO(loan.getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getStatus(),
                BookResponseDTO.create(loan.getBook()),
                daysOverdue
        );
    }
}
