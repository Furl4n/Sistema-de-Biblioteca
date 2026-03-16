package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.AddLoanRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoanResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService service;

    @PostMapping("/new")
    public ResponseEntity<LoanResponseDTO> newLoan(@RequestBody AddLoanRequestDTO data){
        LoanResponseDTO response = service.addLoan(data);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<LoanResponseDTO>> getAll(){
        List<LoanResponseDTO> response = service.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable Long id){
        LoanResponseDTO response = service.GetById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<List<LoanResponseDTO>> getLoansByUserId(@PathVariable Long userId){
        List<LoanResponseDTO> response = service.getByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id){
        if(service.deleteById(id)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return ResponseEntity.notFound().build();
    }
}