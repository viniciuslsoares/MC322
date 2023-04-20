import java.util.*;
import java.text.SimpleDateFormat;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;

    ArrayList<Sinistro> aux_1 = new ArrayList<Sinistro>();
    ArrayList<Cliente> aux_2 = new ArrayList<Cliente>();
    Scanner in = new Scanner(System.in);
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");

    // Constructor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.telefone = telefone;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.listaSinistros = aux_1;
        this.listaClientes = aux_2;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes){
        this.listaClientes = listaClientes;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros){
        this.listaSinistros = listaSinistros;
    }


    //Cadastro e remoção de Clientes

    public boolean cadastrarClientes(Cliente cliente) {
        try {
            listaClientes.add(cliente);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao adicionar cliente");
            return false;
        }
    }

    public boolean removerClientes(Cliente cliente) {
        try {
            if (listaClientes.contains(cliente)) {
                listaClientes.remove(cliente);
                return true;
            } else {
                System.out.println("Erro ao remover cliente");
                return false;
            }
        } catch (Exception e) {
            System.out.print("Erro ao remover cliente");
            return false;
        } 
    }

    public boolean clienteExiste(String id) {
        int i = 0;
        id = ClientePJ.formatacaoCnpj(id);
        for (i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).idCliente().equals(id))
                return true;
        }
            return false; 
    } 

    public int encontrarCliente(String id) throws Exception {
            int i = 0;
            id = ClientePJ.formatacaoCnpj(id);
            for (i = 0; i < listaClientes.size(); i++) {
                if (listaClientes.get(i).idCliente().equals(id))
                    return i;
            }
            throw new Exception ("Cliente não encontrado");
        }


    public void listarClientes(String tipoCliente) {
        ArrayList<Cliente> lista_tipo_cliente = new ArrayList<Cliente>();
        for (Cliente cliente_atual : listaClientes) {
            if (cliente_atual.getClass().getCanonicalName().equals(tipoCliente)) {
                lista_tipo_cliente.add(cliente_atual);
            }
        }
        System.out.print(lista_tipo_cliente);
    }

    public boolean gerarSinistro(Cliente cliente, Date data, String endereco,
        Veiculo veiculo){
        try {
            if (listaClientes.contains(cliente)) {
                Sinistro novo_sinistro = new Sinistro(data, endereco, 
                this, veiculo, cliente);
                listaSinistros.add(novo_sinistro);
                return true; 
            } else return false;
        } catch (Exception e) {
            System.out.println("Sinistro não gerado");
            return false;
        }
    }

    public boolean visualizarSinistro(String id){
        boolean flag = false;
        for (Sinistro sinistro_atual : listaSinistros) {
            if (sinistro_atual.getCliente().idCliente().equals(ClientePJ.formatacaoCnpj(id))) {
                System.out.println(sinistro_atual);
                flag = true;
            }
        }
        return flag;
    }

    public void listarSinistros(){
        System.out.print(listaSinistros);
    }

    @Override
    public String toString() {
        return "Seguradora:\nNome: " + nome + ";\nTelefone: " + telefone +
        ";\nEmail: " + email + ";\nEndereço: " + endereco + ";\nLista de Sinistros: " +
        listaSinistros + ";\nLista de Clientes:\n " + listaClientes + ";\n";
    }

} 

