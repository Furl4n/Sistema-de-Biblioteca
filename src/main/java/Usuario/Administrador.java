package Usuario;
import Livros.*;
import Biblioteca.*;
import java.util.Scanner;

//todo criar metodos gerenciarUsuario e gerarRelatorio

public class Administrador extends Usuario{

    public Administrador(String id, String nome, String email) {
        super(id, nome, email);
    }

    public Administrador() {
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
