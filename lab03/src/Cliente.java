import java.util.ArrayList; 

// -------------- Pronto! -------------- //

public class Cliente {
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;

    // O Cliente inicia com uma lista vazia que pode ser alterada
    ArrayList<Veiculo> lista = new ArrayList<Veiculo>();

    // Constructor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = lista;
    }
    
    // Getter e Setter para lista de veículos

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    // Método para adicionar um veículo

    public void adicionaVeiculo(Veiculo veiculo) {
        this.listaVeiculos.add(veiculo);
        //System.out.print("Veículo " + veiculo.getPlaca() + " adicionado\n");
    }

    // Método para remover um veículo

    public void removeVeiculo(Veiculo veiculo) {
        if (this.listaVeiculos.contains(veiculo)) {
            this.listaVeiculos.remove(veiculo); 
        } else System.out.print("Veículo não encontrado\n");
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getEndereco() {
        return endereco;
    }


    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String idCliente() {
        return "";
    }

    @Override
    public String toString() {
        return "Cliente:\nNome: " + nome + ";\nEndereco: " + 
            endereco + ";\nVeículos registrados:\n" + listaVeiculos + ";\n";
    }

    public boolean existeVeiculo (String placa) {
        boolean flag = false;
        for (Veiculo veiculo_atual : listaVeiculos) {
            if (veiculo_atual.getPlaca().equals(placa)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public int encontrarVeiculo(String placa) throws Exception {
        
        int i = 0;
        for (i = 0; i < listaVeiculos.size(); i++) {
            if (listaVeiculos.get(i).getPlaca().equals(placa))
                return i;
        }
        throw new Exception("Veículo não encontrado");
    }

}
