import Biblioteca.Biblioteca;
import Usuario.Administrador;
import Usuario.Leitor;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Administrador adm = new Administrador();

        biblioteca.cadastrarLeitor();
        biblioteca.cadastrarAdministrador();

        adm.cadastrarLivro(biblioteca);

        Leitor leitor = new Leitor();

        biblioteca.mostrarAcervo();
        leitor.realizarEmprestimo(biblioteca);
    }
}
