package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.ReservationRequest;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping("/new")
    public ResponseEntity<Reservation> newReservation(@RequestBody ReservationRequest newReservation){
        Optional<Reservation> optionalReservation = service.addReservation(newReservation);

        return optionalReservation.map(Reservation -> new ResponseEntity<>(Reservation, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Reservation>> getAll(){
        List<Reservation> reservations = service.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id){
        Optional<Reservation> optionalReservation = service.getById(id);

        if(optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();
            return new ResponseEntity<>(reservation, HttpStatus.FOUND);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationByUserId(@PathVariable Long userId){
        List<Reservation> reservations = service.getByUserId(userId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable Long id){
        if(service.deleteById(id)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/new/loan/{reservationId}")
    public ResponseEntity<Loan> reservationToLoan(@PathVariable long reservationId){
        Optional<Loan> optionalLoan = service.reservationToLoan(reservationId);

        if(optionalLoan.isPresent()){
            Loan loan = optionalLoan.get();
            return new ResponseEntity<>(loan, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
