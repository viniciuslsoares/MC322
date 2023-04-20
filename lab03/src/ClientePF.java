import java.util.Date;

// -------------- Pronto! ------------------ //

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private Date dataLicenca;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;


    public ClientePF(String nome, String endereco, String cpf, 
            String genero, Date dataLicenca, String educacao, 
            Date dataNascimento, String classeEconomica) 
            throws Exception{
        
        super(nome, endereco);
        this.genero = genero;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.classeEconomica = classeEconomica;
        if (validaCpf(cpf)) {
            this.cpf = formatacaoCpf(cpf);
        } else {
            throw new Exception("CPF inválido");
        }
    }


    public String getCpf() {
        return cpf;          
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    @Override
    public String toString() {
        return super.toString() + "CPF: " + cpf + ";\nGênero: " + genero +  
            ";\nData da Licença: " + dataLicenca + ";\nEducação: " + educacao + 
            ";\nData de Nascimento: " + dataNascimento + ";\nClasse Economica: " + classeEconomica + ";\n";
    }

    // Validação CPF

    public static String formatacaoCpf(String cpf) {
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replaceAll("\\-", "");
        cpf = cpf.replaceAll("\\,", "");
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

    public static Boolean validaCpf(String cpf) {
        String[] lista_aux = formatacaoCpf(cpf).split("");

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

    @Override
    public String idCliente() {
        return cpf;
    }

} 