package dev.PedroFurlan.Sistema_Biblioteca.service;

import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.AddReservationRequestDTO;
import dev.PedroFurlan.Sistema_Biblioteca.DTO.Reservation.ReservationResponseDTO;
import dev.PedroFurlan.Sistema_Biblioteca.infra.exception.BusinessRuleException;
import dev.PedroFurlan.Sistema_Biblioteca.infra.exception.ResourceNotFoundException;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.Book;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.Reservation;
import dev.PedroFurlan.Sistema_Biblioteca.model.User.User;
import dev.PedroFurlan.Sistema_Biblioteca.model.Book.StatusBook;
import dev.PedroFurlan.Sistema_Biblioteca.model.Reservation.StatusReservation;
import dev.PedroFurlan.Sistema_Biblioteca.repository.BookRepository;
import dev.PedroFurlan.Sistema_Biblioteca.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;

    @Transactional
    public ReservationResponseDTO addReservation(AddReservationRequestDTO data, User user) {
        Book book = bookRepository.findById(data.bookId())
                .orElseThrow(() -> new ResourceNotFoundException("The book does not exist."));

        if(book.getStatus().equals(StatusBook.RESERVED))
            throw new BusinessRuleException("This book is not available for reservation.");

        Reservation reservation = new Reservation(book, user, data);

        book.setStatus(StatusBook.RESERVED);

        reservationRepository.save(reservation);
        bookRepository.save(book);

        return ReservationResponseDTO.create(reservation);
    }

    public ReservationResponseDTO getById(Long id, User user) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The reservation does not exist"));

        if(!reservation.getUser().equals(user))
            throw new AccessDeniedException("User can not access this reservation.");

        return ReservationResponseDTO.create(reservation);
    }

    public List<ReservationResponseDTO> getByUserId(User user){
        List<Reservation> reservations = reservationRepository.findByUser(user);

        return reservations.stream().map(ReservationResponseDTO::create).toList();
    }

    @Transactional
    public ReservationResponseDTO cancelReservation(Long id, User user) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The reservation does not exist."));

        if(!reservation.getUser().equals(user))
            throw new AccessDeniedException("User can not access this reservation.");

        if(!reservation.getStatus().equals(StatusReservation.RESERVED))
            throw new BusinessRuleException("The reservation is: " + reservation.getStatus());

        reservation.setStatus(StatusReservation.CANCELED);
        reservation.getBook().setStatus(StatusBook.AVAILABLE);

        reservationRepository.save(reservation);
        bookRepository.save(reservation.getBook());

        return ReservationResponseDTO.create(reservation);
    }

    public void deleteById(Long id, User user) {
        Reservation reservation = reservationRepository.findById((id))
                .orElseThrow(() -> new ResourceNotFoundException("The reservation does not exist"));

        if(reservation.getUser()==user)
            throw new AccessDeniedException("User can not access this reservation");

        reservationRepository.deleteById(id);
    }
}