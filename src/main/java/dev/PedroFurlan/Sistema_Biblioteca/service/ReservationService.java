package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.AddReservationRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.ReservationResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.Book;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.StatusBook;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.StatusReservation;
import dev.PedroFurlan.Sistema_Biblioteca.repository.BookRepository;
import dev.PedroFurlan.Sistema_Biblioteca.repository.ReservationRepository;
import dev.PedroFurlan.Sistema_Biblioteca.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final LoanService loanService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ReservationResponseDTO addReservation(AddReservationRequestDTO data) {
        Optional<User> optionalUser = userRepository.findById(data.getUserId());
        Optional<Book> optionalBook = bookRepository.findById(data.getBookId());

        //TODO: Change optionals for exceptions
        if(optionalUser.isPresent() && optionalBook.isPresent()){
            Reservation reservation = new Reservation(optionalBook.get(), optionalUser.get(), data.getExpirationDate());

            if(data.getStatus() != null)
                reservation.setStatus(data.getStatus());

            if(reservation.getReservationDate() != null)
                reservation.setReservationDate(data.getReservationDate());

            return ReservationResponseDTO.create(reservation);
        }
        return null; //temporally
    }

    public List<ReservationResponseDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(ReservationResponseDTO::create).toList();
    }

    public ReservationResponseDTO getById(Long id) {
        Optional<Reservation> opReservation = reservationRepository.findById(id);

        if(opReservation.isPresent()){
            Reservation reservation = opReservation.get();

            return ReservationResponseDTO.create(reservation);
        }

        return null; //temporally
    }

    public List<ReservationResponseDTO> getByUserId(Long userId){
        List<Reservation> reservations = reservationRepository.findByUserId(userId);

        return reservations.stream().map(ReservationResponseDTO::create).toList();
    }

    public boolean deleteById(Long id) {
        if(reservationRepository.existsById(id)){
            reservationRepository.deleteById(id);
            return true;
        } else return false;
    }

    @Transactional
    public Optional<Loan> reservationToLoan(long reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        //TODO: change to throw, so the user can know the problem
        if(optionalReservation.isPresent()){
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