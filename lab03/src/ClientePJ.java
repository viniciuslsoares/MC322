import java.util.Date;

// -------------- Pronto! ------------------ //

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;


    public ClientePJ(String nome, String endereco, String cnpj,
            Date dataFundacao) throws Exception {
        
        super(nome, endereco);
        this.dataFundacao = dataFundacao;
        if (validaCnpj(cnpj)) {
            this.cnpj = formatacaoCnpj(cnpj);
        } else {
            throw new Exception("CNPJ inválido");
        }
    }


    public String getCnpj() {
        return cnpj;
    }


    public Date getDataFundacao() {
        return dataFundacao;
    }


    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }


    @Override
    public String toString() {
        return super.toString() + "CNPJ: " + cnpj + ";\nData de Fundação: "
        + dataFundacao + ";\n";
    }


    public static String formatacaoCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("\\.", "");
        cnpj = cnpj.replaceAll("\\-", "");
        cnpj = cnpj.replaceAll("\\/", "");
        return cnpj;
    }
    
    public static int primeitoDigito(String[] cnpj) {
        int[] aux = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int temp = 0;
        for (int i = 0; i < 12; i++) {
            temp += Integer.valueOf(cnpj[i]) * aux[i];
        }
        int resto = temp % 11;
        int dig1 = 11 - resto;
        if (dig1 >= 10) dig1 = 0;
        return dig1;
    }

    public static int segundoDigito(String[] cnpj) {
        int[] aux = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int temp = 0;
        for (int i = 0; i < 13; i++) {
            temp += Integer.valueOf(cnpj[i]) * aux[i];
        }
        int resto = temp % 11;
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

    public static Boolean validaCnpj(String cnpj) {
        String[] lista_aux = formatacaoCnpj(cnpj).split("");

        // Verificação de 11 dígitos
        if (lista_aux.length != 14)
            return false;
        
        // Verificação de dígitos iguais
        int flag = digitosIguais(lista_aux);
        if (flag == 0) return false;

        // Calculando 1° dígito verificador
        int dig1 = primeitoDigito(lista_aux);
        if (dig1 != Integer.valueOf(lista_aux[12])) return false;
        

        // Calculando 2° dígito verificador
        int dig2 = segundoDigito(lista_aux);
        if (dig2 != Integer.valueOf(lista_aux[13])) return false;

        return true;
    }
    
    @Override
    public String idCliente() {
        return cnpj;
    }

} 