import java.util.*;

// ------------- Pronto! -------------- // 
public class Condutor {
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private Date dataNasc;
    private ArrayList<Sinistro> listaSinistros;

    // Construtor
    public Condutor(String cpf, String nome, String telefone, String email,
            String endereco, Date dataNasc, ArrayList<Sinistro> listaSinistros) throws Exception {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNasc = dataNasc;
        if (listaSinistros == null) {
            this.listaSinistros = new ArrayList<Sinistro>();
        } else
            this.listaSinistros = listaSinistros;
        if (Validacao.validaCpf(cpf)) {
            this.cpf = Validacao.formatacaoId(cpf);
        } else {
            throw new Exception("CPF inválido");
        }
    }

    public String getCpf() {
        return cpf;
    }

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public boolean adicionarSinistro(Sinistro sinistro) {
        listaSinistros.add(sinistro);
        return true;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "; CPF: " + cpf + "; Telefone: " + telefone +
                ";\nEndereco: " + endereco + ";Email: " + email + ";Data de Nascimento: " + dataNasc +
                ";\nSinistros: " + listaSinistros + ";\n";
    }

}
