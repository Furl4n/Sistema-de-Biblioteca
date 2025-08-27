package Livros;

import Usuario.Leitor;

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

    public Livro() {
    }

    @Override
    public void emprestar(Leitor leitor) {

    }

    @Override
    public void devolver(){

    }
}
