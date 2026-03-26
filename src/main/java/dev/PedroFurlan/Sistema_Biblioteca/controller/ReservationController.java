package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.AddReservationRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.ReservationResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/new")
    public ResponseEntity<ReservationResponseDTO> newReservation(@RequestBody AddReservationRequestDTO newReservation, Principal connectedUser){
        ReservationResponseDTO response = reservationService.addReservation(newReservation, connectedUser);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ReservationResponseDTO>> getAll(){
        List<ReservationResponseDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id, Principal connectedUSer){
        ReservationResponseDTO response = reservationService.getById(id, connectedUSer);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/user")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationByUserId(Principal connectedUSer){
        List<ReservationResponseDTO> response = reservationService.getByUserId(connectedUSer);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id, Principal connectedUser){
        if(reservationService.deleteById(id, connectedUser)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/new/loan/{reservationId}")
    public ResponseEntity<Loan> reservationToLoan(@PathVariable long reservationId, Principal connectedUSer){
        Optional<Loan> optionalLoan = reservationService.reservationToLoan(reservationId, connectedUSer);

        if(optionalLoan.isPresent()){
            Loan loan = optionalLoan.get();
            return new ResponseEntity<>(loan, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
