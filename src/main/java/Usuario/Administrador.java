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
        biblioteca.cadastrarLivro();
    }

    //Chama o metodo removerLivro da biblioteca, serve para assegurar que apenas administradores consigam
    public void removerLivro(Biblioteca biblioteca){
        biblioteca.removerLivro();
    }

    public void gerenciarUsuario(){}
    public void gerarRelatorio(Biblioteca  biblioteca){

        int disponivel = 0;
        int reservado = 0;
        int emprestado = 0;

        //percore o  acervo e checa o status dos livros;
        for(Livro livro : biblioteca.getAcervo()){
            switch (livro.getStatus()){
                case Disponivel -> disponivel++;
                case Reservado -> reservado++;
                case Emprestado -> emprestado++;
            }
        }

        System.out.println("\n==== RELATORIO DA BIBLIOTECA ====\n");

        System.out.println("\n-- Acervo de Livros --\n");
        System.out.println("Livros Disponiveis: " + disponivel);
        System.out.println("Livros Emprestados: " + emprestado);
        System.out.println("Livros Resesrvados: " +  reservado);

        System.out.println("\n-- Leitores --\n");
        //Percorre todos os leitores consultando seus atributos
        for(Leitor leitor : biblioteca.getLeitores()){
            System.out.println("Nome: " + leitor.getNome() + " | Emprestimos: " + leitor.getHistoricoEmprestimo()
            + " | Reservas ativas: " + leitor.getLivrosReservados());
        }
        //Percorre todos os administradores consultando seus atributos
        for(Administrador administrador : biblioteca.getAdministradores()){
            System.out.println("Nome: " + administrador.getNome() + " | Email: " + administrador.getEmail());
        }

        System.out.println("\n==== FIM RELATORIO ====\n");
    }
}
