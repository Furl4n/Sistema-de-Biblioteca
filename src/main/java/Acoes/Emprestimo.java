package Acoes;

import Livros.Livro;
import Livros.StatusLivro;

import java.util.Date;

public class Emprestimo extends Livro {
    private int id;
    private Date dataRetirada;
    private Date prazoDevolucao;
    private Date dataEntregaEfetiva;

    public Emprestimo(String idUnico, String titulo, String autor, int anoPublicacao, String genero, StatusLivro status, int id, Date dataRetirada, Date prazoDevolucao, Date dataEntregaEfetiva) {

        super(idUnico, titulo, autor, anoPublicacao, genero, status);
        this.id = id;
        this.dataRetirada = dataRetirada;
        this.prazoDevolucao = prazoDevolucao;
        this.dataEntregaEfetiva = dataEntregaEfetiva;
    }

    public Emprestimo() {
    }

    public float calcularMulta(){

        return 0;
    }

}
