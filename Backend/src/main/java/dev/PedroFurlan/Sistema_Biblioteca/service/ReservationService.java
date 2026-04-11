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
    private final LoanService loanService;
    private final BookRepository bookRepository;
    private final UserService userService;

    public ReservationResponseDTO addReservation(AddReservationRequestDTO data, Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);
        Optional<Book> optionalBook = bookRepository.findById(data.bookId());

        //TODO: Change optionals for exceptions
        if(optionalBook.isPresent()){
            Reservation reservation = new Reservation(optionalBook.get(), user, data.expirationDate());

            if(data.status() != null)
                reservation.setStatus(data.status());

            if(reservation.getReservationDate() != null)
                reservation.setReservationDate(data.reservationDate());

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


    @Transactional
    public Optional<Loan> reservationToLoan(long reservationId, Principal connectedUSer) {
        User user = userService.getAuthenticatedUser(connectedUSer);
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        //TODO: change to throw, so the user can know the problem
        if(optionalReservation.isPresent() && optionalReservation.get().getUser()==user){
            Reservation reservation = optionalReservation.get();

            if(reservation.getStatus().equals(StatusReservation.RESERVED)){
                Loan loan = loanService.convertReservationToLoan(reservation);
                reservation.setStatus(StatusReservation.COLLECTED);
                loan.getBook().setStatus(StatusBook.ON_LOAN);
                return Optional.of(loan);
            }
        }
        return Optional.empty();
    }
}