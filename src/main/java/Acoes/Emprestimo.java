package Acoes;

import Livros.Livro;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

//todo adicionar calcularMulta
public class Emprestimo{
    private static int contador=0;
    private Livro livroEmprestado;
    private int id;
    private LocalDate dataEmprestimo;
    private int prazoDevolucao;
    private LocalDate dataDevolução;

    public Emprestimo(Livro livroEmprestado, int prazoDevolucao) { //inicia os dados do emprestimo com as datas atuais

        contador++;
        id=contador;
        dataEmprestimo = LocalDate.now();
        this.livroEmprestado = livroEmprestado;
        this.prazoDevolucao = prazoDevolucao;
        dataDevolução = dataEmprestimo.plusDays(prazoDevolucao);
    }

    public Emprestimo() {
    }

    public float calcularMulta(){

        return 0;
    }

}
