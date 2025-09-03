package Usuario;

public abstract class Usuario {

    private String id;
    private String nome;
    private String email;

    public Usuario() {
    }

    public  Usuario(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public String getId(){
        return id;
    }
}
