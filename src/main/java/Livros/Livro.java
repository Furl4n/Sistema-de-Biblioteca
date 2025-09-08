package Livros;

import Acoes.Emprestimo;
import Biblioteca.Biblioteca;
import Usuario.Leitor;

import java.util.Scanner;

//todo criar metodo devolver

public class Livro implements Emprestavel{

    private static int contadorLivro = 0;
    private String idUnico;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String genero;
    private StatusLivro status;

    public Livro(String titulo, String autor, int anoPublicacao, String genero, StatusLivro status) {
        this.idUnico = "LIV-"+contadorLivro++; // Ex ->  ID: LIV-1
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

    public void setStatus(StatusLivro status){
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public Livro() {
    }


    @Override
    public void devolver(){

    }

    public void mostrarLivro(){
        System.out.println("\nNome: " + titulo);
        System.out.println("Id: " + idUnico);
        System.out.println("Autor: " + autor);
        System.out.println("Gênero: " + genero);
        System.out.println("Ano de publicação: " + anoPublicacao);
        System.out.println("Status: " + status);
    }
}