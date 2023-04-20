import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

// ---------- Pronto* ---------- //

public class Sinistro {
    private final int id;
    private Date data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    private static ArrayList<Integer> listaIds = new ArrayList<Integer>();
    Random rand = new Random();

    // Constructor
    public Sinistro(Date data, String endereco, Seguradora seguradora, 
    Veiculo veiculo, Cliente cliente) {
        this.data = data;
        this.endereco = endereco;
        this.cliente = cliente;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        int temp = rand.nextInt(1000000);
        while (listaIds.contains(temp)) {
            temp = rand.nextInt(1000000);
        }
        this.id = temp;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Sinistro Id: " + id + ";\nData: " + data + ";\n Seguradora: " + seguradora +
            ";\nEndereço: " + endereco +
            ";\nPlaca do Veículo: " + veiculo.getPlaca() + ";\nNome do Proprietário: " 
            + cliente.getNome() + ";\n";
    }
}

