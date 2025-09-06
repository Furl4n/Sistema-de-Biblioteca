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
    public void gerarRelatorio(){}
}
