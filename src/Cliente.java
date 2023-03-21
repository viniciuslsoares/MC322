

public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    // Constructor
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente [nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", idade=" + idade
                + ", endereco=" + endereco + "]";
    }
    
    public String formatacaoCpf(String cpf) {
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("\\-", "");
        return cpf;
    }
    
    public static int primeitoDigito(String[] cpf) {
        int aux = 0;
        for (int i = 0; i < 9; i++) {
            aux += Integer.valueOf(cpf[i]) * (10 - i);
        }
        int resto = aux % 11;
        int dig1 = 11 - resto;
        if (dig1 >= 10) dig1 = 0;
        return dig1;
    }

    public static int segundoDigito(String[] cpf) {
        int aux = 0;
        for (int i = 0; i < 10; i++) {
            aux += Integer.valueOf(cpf[i]) * (11 - i);
        }
        int resto = aux % 11;
        int dig2 = 11 - resto;
        if (dig2 >= 10) dig2 = 0;
        return dig2;
    }

    public static int digitosIguais(String[] lista) {
        int flag = 0;
        for (int i = 1; i < lista.length; i++) {
            if (lista[0].equals(lista[i])) {
            } else {
                flag = 1;
                break;
            }
        }
        return flag;
    }

    public Boolean validaCpf(String cpf) {
        String[] lista_aux = cpf.split("");

        // Verificação de 11 dígitos
        if (lista_aux.length != 11)
            return false;
        
        // Verificação de dígitos iguais
        int flag = digitosIguais(lista_aux);
        if (flag == 0) return false;

        // Calculando 1° dígito verificador
        int dig1 = primeitoDigito(lista_aux);
        if (dig1 != Integer.valueOf(lista_aux[9])) return false;

        // Calculando 2° dígito verificador
        int dig2 = segundoDigito(lista_aux);
        if (dig2 != Integer.valueOf(lista_aux[10])) return false;

        return true;
    }

}
