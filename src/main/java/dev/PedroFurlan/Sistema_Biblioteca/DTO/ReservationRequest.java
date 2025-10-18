package dev.PedroFurlan.Sistema_Biblioteca.DTO;

import dev.PedroFurlan.Sistema_Biblioteca.model.enums.StatusLoan;
import dev.PedroFurlan.Sistema_Biblioteca.model.enums.StatusReservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    private Long userId;
    private Long bookId;
    private LocalDate loanDate;
    private LocalDate expirationDate;
    private StatusReservation status;
}
