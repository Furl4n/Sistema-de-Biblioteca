package Acoes;

import Livros.*;

import java.time.LocalDate;

//todo adicionar calcularMulta
public class Emprestimo{
    private static int contador=0;
    private Livro livroEmprestado;
    private int id;
    private LocalDate dataEmprestimo;
    private int prazoDevolucao;
    private LocalDate dataDevolução;
    private statusReserva statusReserva;

    public Emprestimo(Livro livroEmprestado, int prazoDevolucao) { //inicia os dados do emprestimo com as datas atuais

        contador++;
        id=contador;
        dataEmprestimo = LocalDate.now();
        this.livroEmprestado = livroEmprestado;
        this.prazoDevolucao = prazoDevolucao;
        dataDevolução = dataEmprestimo.plusDays(prazoDevolucao);
        livroEmprestado.setStatus(StatusLivro.Emprestado);
    }

    public float calcularMulta(){

        return 0;
    }

    public void setStatusReserva(statusReserva statusReserva) {
        this.statusReserva = statusReserva;
    }
}
