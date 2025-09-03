package Livros;

import Acoes.Emprestimo;
import Biblioteca.Biblioteca;
import Usuario.Leitor;

import java.util.Scanner;

//todo criar metodo devolver

public class Livro implements Emprestavel{

    private String idUnico;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String genero;
    private StatusLivro status;

    public Livro(String idUnico, String titulo, String autor, int anoPublicacao, String genero, StatusLivro status) {
        this.idUnico = idUnico;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
        this.status = status;
    }

    public String getIdUnico() {
        return idUnico;
    }

    public StatusLivro getStatus() {
        return status;
    }

    public String getTitulo() {
        return titulo;
    }

    public Livro() {
    }

    @Override
    public boolean emprestar( Leitor leitor, int prazoDevolucao){ //metodo que cria o emprestimo
        if(this.status ==  StatusLivro.Disponivel){ //caso o livro esteja 'Disponivel'
            this.status = StatusLivro.Emprestado; //altera para 'Emprestado'
            Emprestimo emprestimo = new Emprestimo(this, prazoDevolucao); //Cria o emprestimo
            leitor.adicionarEmprestimo(emprestimo); //adiciona o emprestimo no historico do leitor
            return true; //indica que deu certo
        } else{ //caso o livro esteja 'emprestado' ou 'Reservado'
            return false; //indica o erro
        }
    }

    @Override
    public void devolver(){

    }
}
