package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan.AddLoanRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan.LoanResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.service.LoanService;
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

    private final LoanService service;

    @PostMapping
    public ResponseEntity<LoanResponseDTO> createLoan(@RequestBody AddLoanRequestDTO data, Principal connectedUser){
        LoanResponseDTO response = service.addLoan(data, connectedUser);

        URI uri = URI.create("/loans/" + response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/reservation/{id}")
    public ResponseEntity<LoanResponseDTO> convertReservationToLoan(@PathVariable Long id, Principal connectedUser){
        LoanResponseDTO response = service.convertReservationToLoan(id, connectedUser);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable Long id, Principal connectedUser){
        LoanResponseDTO response = service.GetById(id, connectedUser);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public ResponseEntity<List<LoanResponseDTO>> getLoansByUserId(Principal connectedUser){
        List<LoanResponseDTO> response = service.getByUserId(connectedUser);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<LoanResponseDTO> returnLoan(@PathVariable Long id, Principal connectedUser){
        LoanResponseDTO response = service.returnLoan(id, connectedUser);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id, Principal connectedUser){
        service.deleteById(id, connectedUser);

        return ResponseEntity.noContent().build();
    }
}