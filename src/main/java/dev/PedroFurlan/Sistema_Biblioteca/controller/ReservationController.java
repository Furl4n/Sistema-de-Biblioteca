package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.AddReservationRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.ReservationResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping("/new")
    public ResponseEntity<ReservationResponseDTO> newReservation(@RequestBody AddReservationRequestDTO newReservation){
        ReservationResponseDTO response = service.addReservation(newReservation);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ReservationResponseDTO>> getAll(){
        List<ReservationResponseDTO> reservations = service.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id){
        ReservationResponseDTO response = service.getById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationByUserId(@PathVariable String userId){
        List<ReservationResponseDTO> response = service.getByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id){
        if(service.deleteById(id)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return ResponseEntity.notFound().build();
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
