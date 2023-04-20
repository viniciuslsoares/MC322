

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    // Constructor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.telefone = telefone;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Seguradora [nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", endereco=" + endereco
                + "]";
    }

}

