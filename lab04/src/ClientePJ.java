import java.util.*;

// ------------- Pronto! -------------- // 


public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;
    private int qtdeFuncionarios;


    public ClientePJ(String nome, String endereco, String cnpj,
            Date dataFundacao, int qtdeFuncionarios) throws Exception {
        
        super(nome, endereco);
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
        if (Validacao.validaCnpj(cnpj)) {
            this.cnpj = Validacao.formatacaoId(cnpj);
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

    public int getQtdeFuncionarios () {
        return qtdeFuncionarios;
    }


    @Override
    public double calculaScore() {
        double score = CalcSeguro.VALOR_BASE.getFator() * (1 + (double)qtdeFuncionarios/100) * getListaVeiculos().size();
        return score;
    }


    @Override
    public String toString() {
        return super.toString() + "CNPJ: " + cnpj + ";\nData de Fundação: "
        + dataFundacao + ";\nQuantidade de Funcionários: " + qtdeFuncionarios+ ";\n";
    }


    @Override
    public String idCliente() {
        return cnpj;
    }
    
    
} 