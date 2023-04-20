import java.util.Random;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;

    Random rand = new Random();

    // Constructor
    public Sinistro(String data, String endereco) {
        this.id = rand.nextInt(1000000);
        this.data = data;
        this.endereco = endereco;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
