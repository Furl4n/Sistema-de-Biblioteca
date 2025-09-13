package Acoes;

import Livros.*;

import java.time.LocalDate;
import java.time.Period;

//todo adicionar calcularMulta
public class Emprestimo{
    private static int contador=0;
    private Livro livroEmprestado;
    private int id;
    private LocalDate dataEmprestimo;
    private int prazoDevolucao;
    private LocalDate dataDevolucao;
    private LocalDate dataEfetivaDevolucao;
    private statusReserva statusReserva;

    public Emprestimo(Livro livroEmprestado, int prazoDevolucao) { //inicia os dados do emprestimo com as datas atuais

        contador++;
        id=contador;
        dataEmprestimo = LocalDate.now();
        this.livroEmprestado = livroEmprestado;
        this.prazoDevolucao = prazoDevolucao;
        dataDevolucao = dataEmprestimo.plusDays(prazoDevolucao);
        livroEmprestado.setStatus(StatusLivro.Emprestado);
    }

    public float calcularMulta(LocalDate dataEntrega){

        Period periodo = Period.between(dataDevolucao, dataEntrega);
        int diasAtraso = periodo.getDays();


        if (diasAtraso <= 0) {
            return 0f; // sem multa
        }
        //usei final para ser alo imutavel;

        final float TAXA_POR_DIA = 0.3f;
        final float VALOR_BASE = 20f;


        return VALOR_BASE * TAXA_POR_DIA * diasAtraso;
    }

    public void setStatusReserva(statusReserva statusReserva) {
        this.statusReserva = statusReserva;
    }

    public Livro getLivroEmprestado() {
        return livroEmprestado;
    }

    public statusReserva getStatusReserva() {
        return statusReserva;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public LocalDate getDataEfetivaDevolucao() {
        return dataEfetivaDevolucao;
    }

    public void setDataEfetivaDevolucao(LocalDate dataEfetivaDevolucao) {
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }
}


