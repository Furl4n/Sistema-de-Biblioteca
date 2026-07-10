package dev.PedroFurlan.Sistema_Biblioteca.controller;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.AddReservationRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.ReservationResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.service.ReservationService;
import dev.PedroFurlan.Sistema_Biblioteca.service.UserService;
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
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@Valid @RequestBody AddReservationRequestDTO newReservation, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        ReservationResponseDTO response = reservationService.addReservation(newReservation, user);

        URI uri = URI.create("/reservations/" + response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        ReservationResponseDTO response = reservationService.getById(id, user);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationByUserId(Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        List<ReservationResponseDTO> response = reservationService.getByUserId(user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponseDTO> cancelReservation(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        ReservationResponseDTO response = reservationService.cancelReservation(id, user);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id, Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        reservationService.deleteById(id, user);
        return ResponseEntity.noContent().build();
    }
}
