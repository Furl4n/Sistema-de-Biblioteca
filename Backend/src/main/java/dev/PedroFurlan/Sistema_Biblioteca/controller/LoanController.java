package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan.AddLoanRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan.LoanResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.service.LoanService;
import dev.PedroFurlan.Sistema_Biblioteca.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<LoanResponseDTO> createLoan(@Valid @RequestBody AddLoanRequestDTO data, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        LoanResponseDTO response = loanService.addLoan(data, user);

        URI uri = URI.create("/loans/" + response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/reservation/{id}")
    public ResponseEntity<LoanResponseDTO> convertReservationToLoan(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        LoanResponseDTO response = loanService.convertReservationToLoan(id, user);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        LoanResponseDTO response = loanService.getById(id, user);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public ResponseEntity<List<LoanResponseDTO>> getLoansByUserId(Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        List<LoanResponseDTO> response = loanService.getByUserId(user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<LoanResponseDTO> returnLoan(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        LoanResponseDTO response = loanService.returnLoan(id, user);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        loanService.deleteById(id, user);

        return ResponseEntity.noContent().build();
    }
}