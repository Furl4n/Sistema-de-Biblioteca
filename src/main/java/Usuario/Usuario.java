package Usuario;

public abstract class Usuario {

    private int id;
    private String nome;
    private String email;

    public Usuario() {
    }

    public Usuario(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
}
