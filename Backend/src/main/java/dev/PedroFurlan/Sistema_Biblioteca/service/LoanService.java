package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan.AddLoanRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Loan.LoanResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.infra.exception.BusinessRuleException;
import dev.PedroFurlan.Sistema_Biblioteca.infra.exception.ResourceNotFoundException;
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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final ReservationRepository reservationRepository;
    private final UserService userService;

    @Transactional
    public LoanResponseDTO addLoan(AddLoanRequestDTO data, Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);
        Book book = bookRepository.findById(data.bookId())
                .orElseThrow( ()-> new ResourceNotFoundException("The requested book does not exist."));

        if (!book.getStatus().equals(StatusBook.AVAILABLE)) {
            throw new BusinessRuleException("This book is not available for loan.");
        }

        Loan loan = new Loan(book, user, data.dueDate(), StatusLoan.ACTIVE);

        loan.setLoanDate(data.loanDate() == null ? LocalDate.now() : data.loanDate());

        book.setStatus(StatusBook.ON_LOAN);

        bookRepository.save(loan.getBook());
        loanRepository.save(loan);

        return LoanResponseDTO.create(loan, calculateOverdue(loan));
    }

    public LoanResponseDTO GetById(Long id, Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The loan does not exist."));

        if(!loan.getUser().equals(user))
            throw new AccessDeniedException("User can not access this loan");

        return LoanResponseDTO.create(loan, calculateOverdue(loan));

    }

    @Transactional
    public LoanResponseDTO convertReservationToLoan(Long idReservation, Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new ResourceNotFoundException("The reservation does not exist."));

        if(!reservation.getUser().equals(user))
            throw new AccessDeniedException("User can not access this reservation.");

        if(!reservation.getStatus().equals(StatusReservation.RESERVED))
            throw new BusinessRuleException("The reservation is: " + reservation.getStatus());

        Loan loan = new Loan(reservation);
        reservation.setStatus(StatusReservation.COLLECTED);
        loan.getBook().setStatus(StatusBook.ON_LOAN);
        reservationRepository.save(reservation);
        loanRepository.save(loan);
        bookRepository.save(loan.getBook());

        return LoanResponseDTO.create(loan, calculateOverdue(loan));
    }

    public void deleteById(Long id, Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The loan does not exist."));

        if(!loan.getUser().equals(user))
            throw new AccessDeniedException("User can not access this loan.");

        loanRepository.deleteById(id);
    }

    public List<LoanResponseDTO> getByUserId(Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);

         List<Loan> loans = loanRepository.findByUser(user);

        return loans.stream().map(loan -> LoanResponseDTO.create(loan, calculateOverdue(loan))).toList();
    }

    public Long calculateOverdue(Loan loan){
        if(loan.getReturnDate() == null){
            long daysOverdue = loan.getDueDate().until(LocalDate.now(), DAYS);

            if(daysOverdue <=0) return 0L;
            return daysOverdue;
        }

        long days = loan.getDueDate().until(loan.getReturnDate(), DAYS);

        if(days<=0) return 0L;
        return days;
    }

    @Transactional
    public LoanResponseDTO returnLoan(Long id, Principal connectedUser) {
        User user = userService.getAuthenticatedUser(connectedUser);

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The loan does not exist."));

        if(!loan.getUser().equals(user))
            throw new AccessDeniedException("User can not access this loan.");

        if(!loan.getStatus().equals(StatusLoan.ACTIVE))
            throw new BusinessRuleException("The loan is: " + loan.getStatus());

        loan.setStatus(StatusLoan.RETURNED);
        loan.setReturnDate(LocalDate.now());
        loan.getBook().setStatus(StatusBook.AVAILABLE);

        loanRepository.save(loan);
        bookRepository.save(loan.getBook());

        return LoanResponseDTO.create(loan, calculateOverdue(loan));
    }
}