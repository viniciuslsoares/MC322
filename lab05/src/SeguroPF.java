import java.util.*;

// ------------- Pronto! -------------- // 

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora, 
    ArrayList<Sinistro> listaSinistros, ArrayList<Condutor> listaCondutores,
    Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora, listaSinistros, listaCondutores);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Nome do Cliente: " + cliente.getNome() + ";Veiculo: " + 
        veiculo.getPlaca() + "\n" +  super.toString();
    }

    @Override
    public double calcularValor() {
        double valor = 0.0;
        double base = CalcSeguro.VALOR_BASE.getFator();
        double fator = cliente.getFator();
        int qntVeiculos = cliente.getListaVeiculos().size();
        int qntSinistrosCliente = 0;
        int qntSinistrosCondutor = 0;
        for (Sinistro a: this.getListaSinistros()) {
            if (a.getCondutor() == null) {
                qntSinistrosCliente += 1;
            } else {
                qntSinistrosCondutor +=1;
            }
        }
        valor = (base * fator * (1 + 1/(qntVeiculos + 2)) *
        (2 + qntSinistrosCliente/10) * (5 + qntSinistrosCondutor/10));
        this.setValorMensal(valor);

        return valor;
        }

    @Override
    public String nomeCliente() {
        return cliente.getNome();
    }

    @Override
    public String imprimeVeiculos() {
        return veiculo.getPlaca();
    }

    @Override
    public String idCliente() {
        return cliente.getCpf();
    }

}