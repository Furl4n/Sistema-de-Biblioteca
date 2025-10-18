package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.ReservationRequest;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.model.User;
import dev.PedroFurlan.Sistema_Biblioteca.model.enums.StatusBook;
import dev.PedroFurlan.Sistema_Biblioteca.model.enums.StatusReservation;
import dev.PedroFurlan.Sistema_Biblioteca.repository.BookRepository;
import dev.PedroFurlan.Sistema_Biblioteca.repository.ReservationRepository;
import dev.PedroFurlan.Sistema_Biblioteca.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private LoanService loanService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public Optional<Reservation> addReservation(ReservationRequest reservationRequest) {
        Optional<User> optionalUser = userRepository.findById(reservationRequest.getUserId());
        Optional<Book> optionalBook = bookRepository.findById(reservationRequest.getBookId());

        if(optionalUser.isPresent() && optionalBook.isPresent()){
            Reservation reservation = new Reservation(optionalBook.get(), optionalUser.get(), reservationRequest.getLoanDate(), reservationRequest.getExpirationDate(), reservationRequest.getStatus());

            if(reservationRequest.getStatus() == null){
                reservation.setStatus(StatusReservation.RESERVED);
            }
            if(reservation.getReservationDate() == null){
                reservation.setReservationDate(LocalDate.now());
            }
            return Optional.of(reservationRepository.save(reservation));
        }
        return Optional.empty();
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getById(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getByUserId(Long userId){
        return reservationRepository.findByUserId(userId);
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
