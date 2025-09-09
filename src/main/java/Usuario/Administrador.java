package Usuario;
import Livros.*;
import Biblioteca.*;
import java.util.Scanner;

//todo criar metodos gerenciarUsuario, gerarRelatorio e adcionar atributo

public class Administrador extends Usuario{

    private String senha;

    public Administrador(String nome, String email, String senha) {
        super( nome, email);
        this.senha = senha;
    }

    public Administrador() {
    }

    @Override

    public void mostrarUsuario() {
        System.out.println("\n---------------------------");
        System.out.println("Nome: " + this.getNome());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Senha: " + this.senha);
        System.out.println("---------------------------\n");

    }


    //Chama o metodo cadastrarLivro da biblioteca, serve para assegurar que apenas administradores consigam
    public void cadastrarLivro(Biblioteca biblioteca){

        System.out.println("\n--Cadastro de novo livro--\n");

        biblioteca.cadastrarLivro();
    }

    //Chama o metodo removerLivro da biblioteca, serve para assegurar que apenas administradores consigam
    public void removerLivro(Biblioteca biblioteca){
        System.out.println("\n--Apagar livro do acervo--\n");

        biblioteca.removerLivro();
    }

    public void gerenciarUsuario(){}
    public void gerarRelatorio(Biblioteca  biblioteca){
        System.out.println("\n--Relatorio da Biblioteca--\n");
        System.out.println("Usuarios: " + getContadorId());
        System.out.println("  * Leitores: " );
        System.out.println("  * Administradores: " );
        System.out.println("Livros: " );
        System.out.println("  * Livros Disponiveis: " );
        System.out.println("  * Livros Emprestados: " );
        System.out.println("  * Livros Resesrvados: " );
        System.out.println("\n-------Fim Relatorio-------\n");
    }
}
