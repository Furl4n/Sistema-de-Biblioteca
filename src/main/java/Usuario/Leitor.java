package Usuario;

import java.util.List;
import Acoes.Emprestimo;

public class Leitor extends Usuario{

    private List<Emprestimo> historicoEmprestimo;
    public Leitor() {
    }

    public Leitor(int id, String nome, String email) {
        super(id, nome, email);
    }



}
