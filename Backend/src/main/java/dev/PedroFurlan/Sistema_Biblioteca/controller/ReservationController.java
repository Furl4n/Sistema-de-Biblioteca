package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.AddReservationRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.ReservationResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@Valid @RequestBody AddReservationRequestDTO newReservation, Principal connectedUser){
        ReservationResponseDTO response = reservationService.addReservation(newReservation, connectedUser);

        URI uri = URI.create("/reservations/" + response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id, Principal connectedUSer){
        ReservationResponseDTO response = reservationService.getById(id, connectedUSer);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationByUserId(Principal connectedUSer){
        List<ReservationResponseDTO> response = reservationService.getByUserId(connectedUSer);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponseDTO> cancelReservation(@PathVariable Long id, Principal connectedUser){
        ReservationResponseDTO response = reservationService.cancelReservation(id, connectedUser);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id, Principal connectedUser){
        reservationService.deleteById(id, connectedUser);
        return ResponseEntity.noContent().build();
    }
}
