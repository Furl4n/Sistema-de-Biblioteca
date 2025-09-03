package Livros;

import Biblioteca.Biblioteca;
import Usuario.Leitor;

public interface Emprestavel {

    public boolean emprestar(Leitor leitor, int prazoDevolucao);
    public void devolver();

}
