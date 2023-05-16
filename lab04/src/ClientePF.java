import java.util.*;
import java.time.*;

// ------------- Pronto! -------------- // 

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
        if (Validacao.validaCpf(cpf)) {
            this.cpf = Validacao.formatacaoId(cpf);
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

    public int getIdade() {
        LocalDate hoje = LocalDate.now();
        LocalDate aux = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period periodo = Period.between(aux, hoje);
        return periodo.getYears();
    }

    public double getFator() {
        int idade = getIdade();
        if (idade < 30) return CalcSeguro.FATOR_18_30.getFator();
        else if (idade < 60) return CalcSeguro.FATOR_30_60.getFator();
        else return CalcSeguro.FATOR_60_90.getFator();
    }

    @Override
    public String toString() {
        return super.toString() + "CPF: " + cpf + ";\nGênero: " + genero +  
            ";\nData da Licença: " + dataLicenca + ";\nEducação: " + educacao + 
            ";\nData de Nascimento: " + dataNascimento + ";\nClasse Economica: " + classeEconomica + ";\n";
    }

    @Override
    public String idCliente() {
        return cpf;
    }

    @Override
    public double calculaScore() {
        double score = CalcSeguro.VALOR_BASE.getFator() * getFator() * getListaVeiculos().size();
        return score;
    }


} 