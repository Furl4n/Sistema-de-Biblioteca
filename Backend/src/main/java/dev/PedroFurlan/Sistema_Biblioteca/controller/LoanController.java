package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan.AddLoanRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan.LoanResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService service;

    @PostMapping("/new")
    public ResponseEntity<LoanResponseDTO> newLoan(@RequestBody AddLoanRequestDTO data, Principal connectedUser){
        LoanResponseDTO response = service.addLoan(data, connectedUser);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<LoanResponseDTO>> getAll(){
        List<LoanResponseDTO> response = service.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable Long id, Principal connectedUser){
        LoanResponseDTO response = service.GetById(id, connectedUser);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/user")
    public ResponseEntity<List<LoanResponseDTO>> getLoansByUserId(Principal connectedUser){
        List<LoanResponseDTO> response = service.getByUserId(connectedUser);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id, Principal connectedUser){
        service.deleteById(id, connectedUser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}