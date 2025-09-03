import Biblioteca.Biblioteca;
import Usuario.Administrador;
import Usuario.Leitor;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Administrador adm = new Administrador();
        biblioteca.cadastrarLeitor();

        adm.cadastrarLivro(biblioteca);

        biblioteca.mostrarAcervo();

        biblioteca.realizarEmprestimo();
        biblioteca.realizarEmprestimo();
    }
}
