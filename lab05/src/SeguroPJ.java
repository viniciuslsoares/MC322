import java.util.*;


public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, 
    ArrayList<Sinistro> listaSinistros, ArrayList<Condutor> listaCondutores,
    Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora, listaSinistros, listaCondutores);
        this.frota = frota;
        this.cliente = cliente;
    }

    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    @Override
    public ClientePJ getCliente() {
        return cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Nome do Cliente: " + cliente.getNome() + ";\nFrota: " + frota + super.toString();
    }

    @Override
    public double calcularValor() {
    double valor = 0.0;
    double base = CalcSeguro.VALOR_BASE.getFator();
    int ano = cliente.getAno();
    int qntVeiculos = frota.getListaVeiculos().size();
    int qntSinistrosCliente = 0;
    int qntSinistrosCondutor = 0;
    for (Sinistro a: this.getListaSinistros()) {
        if (a.getCondutor() == null) {
            qntSinistrosCliente += 1;
        } else {
                qntSinistrosCondutor +=1;
        }
    }
    valor = (base * (10 + cliente.getQtdeFuncionarios()/10) * 
        (1 + 1/(qntVeiculos + 2)) * (1 + 1/(ano + 2)) *
        (2 + qntSinistrosCliente/10) + (5 + qntSinistrosCondutor/10));
    this.setValorMensal(valor);

    return valor;
    }

    @Override
    public String nomeCliente() {
        return cliente.getNome();
    }

    @Override
    public String imprimeVeiculos() {
        String string = "";
        for (Veiculo v:frota.getListaVeiculos()) {
            string += v.getPlaca() + "; ";
        }
        string += "\n";
        return string;
    }

    @Override
    public String idCliente() {
        return cliente.getCnpj();
    }

}