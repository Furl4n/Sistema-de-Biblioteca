package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoanRequest;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @PostMapping("/new")
    public ResponseEntity<Loan> newLoan(@RequestBody LoanRequest newLoan){
        Optional<Loan> optionalLoan = service.addLoan(newLoan);

        return optionalLoan.map(loan -> new ResponseEntity<>(loan, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Loan>> getAll(){
        List<Loan> loans = service.getAll();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id){
        Optional<Loan> optionalLoan = service.GetById(id);

        if(optionalLoan.isPresent()){
            Loan loan = optionalLoan.get();
            return new ResponseEntity<>(loan, HttpStatus.FOUND);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<List<Reservation>> getLoansByUserId(@PathVariable Long userId){
        List<Reservation> reservations = service.getByUserId(userId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteLoan(@PathVariable Long id){
        if(service.deleteById(id)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
