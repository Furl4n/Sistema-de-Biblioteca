import Biblioteca.Biblioteca;
import Usuario.Administrador;
import Usuario.Leitor;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Administrador administrador = new Administrador();

        biblioteca.cadastrarAdministrador();
        Leitor leitor = biblioteca.cadastrarLeitor();
        biblioteca.cadastrarLivro();

        leitor.fazerReserva(biblioteca);
        biblioteca.mostrarAcervo();
        leitor.pegarReserva(biblioteca);
    }
}
