package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.AddReservationRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.ReservationResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.Book;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.StatusBook;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.StatusReservation;
import dev.PedroFurlan.Sistema_Biblioteca.repository.BookRepository;
import dev.PedroFurlan.Sistema_Biblioteca.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final UserService userService;

    public ReservationResponseDTO addReservation(AddReservationRequestDTO data, Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);
        Optional<Book> optionalBook = bookRepository.findById(data.bookId());

        if(data.returnDate().isBefore(data.reservationDate())) return null; //todo: adicionar exception

        //TODO: Change optionals for exceptions
        if(optionalBook.isPresent()){
            Reservation reservation = new Reservation(optionalBook.get(), user, data);

            if(data.status() != null)
                reservation.setStatus(data.status());

            if(reservation.getReservationDate() != null)
                reservation.setReservationDate(data.reservationDate());

            reservationRepository.save(reservation);

            return ReservationResponseDTO.create(reservation);
        }
        return null; //temporally
    }

    public List<ReservationResponseDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(ReservationResponseDTO::create).toList();
    }

    public ReservationResponseDTO getById(Long id, Principal connectedUSer) {
        User user = userService.getAuthenticatedUser(connectedUSer);
        Optional<Reservation> opReservation = reservationRepository.findById(id);

        if(opReservation.isPresent() && opReservation.get().getUser()==user){
            Reservation reservation = opReservation.get();

            return ReservationResponseDTO.create(reservation);
        }

        return null; //temporally
    }

    public List<ReservationResponseDTO> getByUserId(Principal connectedUser){
        User user = userService.getAuthenticatedUser(connectedUser);

        List<Reservation> reservations = reservationRepository.findByUser(user);

        return reservations.stream().map(ReservationResponseDTO::create).toList();
    }

    public boolean deleteById(Long id, Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);

        //TODO: Change exception
        Reservation reservation = reservationRepository.findById((id)).orElseThrow();

        if(reservation.getUser()==user){
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}