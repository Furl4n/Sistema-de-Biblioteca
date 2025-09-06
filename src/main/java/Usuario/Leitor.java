package Usuario;

import java.util.ArrayList;
import java.util.List;
import Acoes.Emprestimo;

public class Leitor extends Usuario{

    private List<Emprestimo> historicoEmprestimo = new ArrayList<>();
    public Leitor() {
    }

    public Leitor( String nome, String email) {
        super(nome, email);
    }

    @Override

    public void mostrarUsuario() {
        System.out.println("\n---------------------------");
        System.out.println("Nome: " + this.getNome());
        System.out.println("Email: " + this.getEmail());
        System.out.println("---------------------------\n");

    }

    public void adicionarEmprestimo(Emprestimo emprestimo){ //salva o emprestimo no historico do Leitor
        historicoEmprestimo.add(emprestimo);
    }
}


