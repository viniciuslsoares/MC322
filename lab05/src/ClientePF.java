import java.util.*;
import java.time.*;

// ------------- Pronto! -------------- // 

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private String educacao;
    private Date dataNascimento;
    private ArrayList <Veiculo> listaVeiculos;

    ArrayList<Veiculo> lista = new ArrayList<Veiculo>();

    public ClientePF(String nome, String endereco, String telefone, String email,
            String genero, String educacao, Date dataNascimento, String cpf) 
            throws Exception{
        
        super(nome, endereco, telefone, email);
        this.genero = genero;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        if (Validacao.validaCpf(cpf)) {
            this.cpf = Validacao.formatacaoId(cpf);
        } else {
            throw new Exception("CPF inválido");
        }
        this.listaVeiculos = lista;
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

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }


    public boolean removerVeiculo(Veiculo veiculo) {
        if (this.listaVeiculos.contains(veiculo)) {
            this.listaVeiculos.remove(veiculo);
            return true; 
        } else {
            System.out.print("Veículo não encontrado\n");
            return false;
        }
    }

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        this.listaVeiculos.add(veiculo);
        return true;
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

    public void printListaVeiculos() {
        // Imprime a lista de veículos pelas suas placas (com ínidice de posição na lista)
        for (int i = 0; i < listaVeiculos.size(); i++) {
            System.out.println(i + ")" + listaVeiculos.get(i).getPlaca());
        }
    }

    @Override
    public String toString() {
        return super.toString() + "CPF: " + cpf + ";\nGênero: " + genero +  
            ";\nEducação: " + educacao + ";\nData de Nascimento: " 
            + dataNascimento + ";\nVeículos Registrados: " + listaVeiculos();
    }

    @Override
    public String idCliente() {
        return cpf;
    }

    public String listaVeiculos() {
        int i = 0;
        String string = "";
        for (Veiculo v:listaVeiculos) {
            string += i + ") " + v.getMarca() + " " + v.getModelo() + " - " + v.getPlaca() + "\n";
            i++;
        }
        return string;
    }

} 