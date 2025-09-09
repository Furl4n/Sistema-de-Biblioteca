package Acoes;

import Livros.Livro;
import Livros.StatusLivro;

import java.time.LocalDate;

public class Reserva{
    private static int contador=0;
    private Livro livroReservado;
    private int idReserva;
    private LocalDate dataReserva;
    private int tempoParaBuscar;
    private statusReserva statusReserva;

    public Reserva(Livro livroReservado, int tempoParaBuscar){
        contador++;
        idReserva = contador;
        this.livroReservado = livroReservado;
        dataReserva = LocalDate.now();
        this.tempoParaBuscar = tempoParaBuscar;
    }

    public int getIdReserva() {
        return idReserva;
    }
    public void setStatusReserva(statusReserva statusReserva) {
        this.statusReserva = statusReserva;
    }

    public statusReserva getStatusReserva() {
        return statusReserva;
    }

    public Livro getLivroReservado() {
        return livroReservado;
    }

    public static int getContador() {
        return contador;
    }
}
