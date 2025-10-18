package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoanRequest;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.model.User;
import dev.PedroFurlan.Sistema_Biblioteca.model.enums.StatusLoan;
import dev.PedroFurlan.Sistema_Biblioteca.repository.BookRepository;
import dev.PedroFurlan.Sistema_Biblioteca.repository.LoanRepository;
import dev.PedroFurlan.Sistema_Biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public Optional<Loan> addLoan(@RequestBody LoanRequest loanRequest) {
        Optional<User> optionalUser = userRepository.findById(loanRequest.getUserId());
        Optional<Book> optionalBook = bookRepository.findById(loanRequest.getBookId());

        if(optionalUser.isPresent() && optionalBook.isPresent()){
            Loan loan = new Loan(optionalBook.get(), optionalUser.get(), loanRequest.getLoanDate(), loanRequest.getDueDate(), loanRequest.getStatus());

            if(loanRequest.getStatus() == null){
                loan.setStatus(StatusLoan.ACTIVE);
            }
            if(loan.getLoanDate() == null){
                loan.setLoanDate(LocalDate.now());
            }
            return Optional.of(loanRepository.save(loan));
        }

        return Optional.empty();
    }

    public List<Loan> getAll() {
        return loanRepository.findAll();
    }

    public Optional<Loan> GetById(Long id) {
        return loanRepository.findById(id);
    }

    public Loan convertReservationToLoan(Reservation reservation) {
        Loan loan = new Loan(reservation.getBook(), reservation.getUser(), LocalDate.now(), reservation.getExpirationDate(), StatusLoan.ACTIVE);
        return loanRepository.save(loan);
    }

    public boolean deleteById(Long id) {
        if(loanRepository.existsById(id)){
            loanRepository.deleteById(id);
            return true;
        } else return false;
    }

    public List<Reservation> getByUserId(Long userId) {
        return loanRepository.findByUserId(userId);
    }
}
