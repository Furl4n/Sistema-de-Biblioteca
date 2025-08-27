package Livros;

import Usuario.Leitor;

public interface Emprestavel {

    public void emprestar(Leitor leitor);
    public void devolver();

}
