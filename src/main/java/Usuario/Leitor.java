package Usuario;

import java.util.ArrayList;
import java.util.List;
import Acoes.Emprestimo;

public class Leitor extends Usuario{

    private List<Emprestimo> historicoEmprestimo = new ArrayList<>();
    public Leitor() {
    }

    public Leitor(String id, String nome, String email) {
        super(id, nome, email);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo){ //salva o emprestimo no historico do Leitor
        historicoEmprestimo.add(emprestimo);
    }
}


