package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan.AddLoanRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan.LoanResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.Book;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.StatusBook;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.StatusReservation;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.StatusLoan;
import dev.PedroFurlan.Sistema_Biblioteca.repository.BookRepository;
import dev.PedroFurlan.Sistema_Biblioteca.repository.LoanRepository;
import dev.PedroFurlan.Sistema_Biblioteca.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final ReservationRepository reservationRepository;
    private final UserService userService;

    public LoanResponseDTO addLoan(@RequestBody AddLoanRequestDTO data, Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);
        Optional<Book> optionalBook = bookRepository.findById(data.bookId());

        if(optionalBook.isPresent()){
            Loan loan = new Loan(optionalBook.get(), user, data.loanDate(), data.dueDate(), data.status());

            if(data.status() != null){
                loan.setStatus(StatusLoan.ACTIVE);
            }
            if(data.loanDate() == null){
                loan.setLoanDate(LocalDate.now());
            }
            return LoanResponseDTO.create(loan);
        }

        return null; //temporally
    }

    public List<LoanResponseDTO> getAll() {
        List<Loan> loans = loanRepository.findAll();

        return loans.stream().map(LoanResponseDTO::create).toList();
    }

    public LoanResponseDTO GetById(Long id, Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);
        Optional<Loan> opLoan = loanRepository.findById(id);

        if(opLoan.isPresent() && opLoan.get().getUser()==user){
            Loan loan = opLoan.get();

            return LoanResponseDTO.create(loan);
        }
        return null;
    }

    @Transactional
    public LoanResponseDTO convertReservationToLoan(Long idReservation, Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);
        Optional<Reservation> optReservation = reservationRepository.findById(idReservation);

        if(optReservation.isPresent()){
            Reservation reservation = optReservation.get();

            if(reservation.getUser().equals(user) && reservation.getStatus().equals(StatusReservation.RESERVED)){
                Loan loan = new Loan(reservation);
                reservation.setStatus(StatusReservation.COLLECTED);
                loan.getBook().setStatus(StatusBook.ON_LOAN);
                reservationRepository.save(reservation);
                loanRepository.save(loan);
                bookRepository.save(loan.getBook());

                return LoanResponseDTO.create(loan);
            }
        }

        return null; //todo: adicionar throw
    }

    public void deleteById(Long id, Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);

        //TODO: Change exception
        Loan loan= loanRepository.findById(id).orElseThrow();

        if(loan.getUser()==user)
            loanRepository.deleteById(id);

    }

    public List<LoanResponseDTO> getByUserId(Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);

         List<Loan> loans = loanRepository.findByUser(user);

        return loans.stream().map(LoanResponseDTO::create).toList();
    }
}