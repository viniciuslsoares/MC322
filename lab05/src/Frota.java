import java.util.*;

// ------------------ Pronto ----------

public class Frota {
    private String code;
    private ArrayList<Veiculo> listaVeiculos;

    private static ArrayList<String> listaCodes = new ArrayList<String>();
    Random rand = new Random();

    //Construtor
    public Frota(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
        String aux = String.valueOf(rand.nextInt(1000000));
        while (listaCodes.contains(aux)) {
            aux = String.valueOf(rand.nextInt(1000000));
        }
        this.code = aux;
    }

    // Getters e Setters
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public boolean addVeiculo(Veiculo veiculo){
        listaVeiculos.add(veiculo);
        return true;
    }

    @Override
    public String toString(){
        return "Frota código: " + code + ";\nCarros: " + listaVeiculos() + "\n";
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
    
    public boolean removeVeiculo (Veiculo veiculo) {
        if (listaVeiculos.contains(veiculo)) {
            listaVeiculos.remove(veiculo);
            return true;
        } else {
            System.out.println("Veículo" + veiculo.getPlaca() + " não encontrado");
            return false;
        }
    }
}
