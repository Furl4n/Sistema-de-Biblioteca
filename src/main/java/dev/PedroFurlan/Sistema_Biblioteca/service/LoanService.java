package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.AddLoanRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.LoanResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.Book;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.Loan;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.model.Loan.StatusLoan;
import dev.PedroFurlan.Sistema_Biblioteca.repository.BookRepository;
import dev.PedroFurlan.Sistema_Biblioteca.repository.LoanRepository;
import dev.PedroFurlan.Sistema_Biblioteca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanResponseDTO addLoan(@RequestBody AddLoanRequestDTO data) {
        Optional<User> optionalUser = userRepository.findById(data.getUserId());
        Optional<Book> optionalBook = bookRepository.findById(data.getBookId());

        if(optionalUser.isPresent() && optionalBook.isPresent()){
            Loan loan = new Loan(optionalBook.get(), optionalUser.get(), data.getLoanDate(), data.getDueDate(), data.getStatus());

            if(data.getStatus() != null){
                loan.setStatus(StatusLoan.ACTIVE);
            }
            if(data.getLoanDate() == null){
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

    public LoanResponseDTO GetById(Long id) {
        Optional<Loan> opLoan = loanRepository.findById(id);

        if(opLoan.isPresent()){
            Loan loan = opLoan.get();

            return LoanResponseDTO.create(loan);
        }
        return null;
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

    public List<LoanResponseDTO> getByUserId(String userId) {
         List<Loan> loans = loanRepository.findByUserId(userId);

        return loans.stream().map(LoanResponseDTO::create).toList();
    }
}