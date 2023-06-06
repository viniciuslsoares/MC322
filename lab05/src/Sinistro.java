import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

// ------------------- Pronto --------

public class Sinistro {
    private final int id;
    private Date data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;

    private static ArrayList<Integer> listaIds = new ArrayList<Integer>();
    Random rand = new Random();

    // Constructor
    public Sinistro(Date data, String endereco, Condutor condutor, Seguro seguro) {
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
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

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    @Override
    public String toString() {
        return "Sinistro Id: " + id + "; Data: " + data + "; Local: " + endereco +
            ";\nNome do Condutor: " + condutor.getNome() + ";\n";
    }
}

