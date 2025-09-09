package Usuario;

public abstract class Usuario {

    private static int contadorId = 0;
    private int id;
    private String nome;
    private String email;

    public Usuario() {
    }

    public  Usuario( String nome, String email) {
        this.id = contadorId++;
        this.nome = nome;
        this.email = email;
    }

    abstract public void mostrarUsuario();

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getId(){
        return id;
    }

    public static int getContadorId() {
        return contadorId;
    }
}
