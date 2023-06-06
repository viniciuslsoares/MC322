import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;

public class Seguradora {
    private final String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Seguro> listaSeguros;

    // Lista estática que irá armazenar as Seguradoras registradas 
    private static ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
    Scanner scanner = new Scanner(System.in);
    ArrayList<Cliente> aux_1 = new ArrayList<Cliente>();
    ArrayList<Seguro> aux_2 = new ArrayList<Seguro>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");

    // Constructor
    public Seguradora(String cnpj, String nome, String telefone, String email, String endereco) throws Exception {
        this.telefone = telefone;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.listaClientes = aux_1;
        this.listaSeguros = aux_2;
        if (Validacao.validaCnpj(cnpj)) {
            this.cnpj = Validacao.formatacaoId(cnpj);
        } else {
            throw new Exception("CNPJ inválido");
        }
    }

    // Getters e Setters

    public String getCpj() {
        return cnpj;
    }

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

    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }
    

    public static ArrayList<Seguradora> getListaSeguradoras() {
        return listaSeguradoras;
    }

    @Override
    public String toString() {
        return "Seguradora:\nNome: " + nome + ";\nTelefone: " + telefone +
        ";\nEmail: " + email + ";\nEndereço: " + endereco + ";\nLista de Clientes:\n" + 
        stringClientes() + "\nLista de Seguros: " + printSeguros();
    }

    public String stringClientes() {
        String string = "";
        for(Cliente c:listaClientes){
            String tipo = "";
            if (c.getClass().getCanonicalName().equals("ClientePF")) {
                tipo = "CPF";
            } else tipo = "CNPJ";
            string = string + c.getNome() + " - " + tipo + ": " + c.idCliente() + "\n";
        }
        return string;
    }

    public String printSeguros() {
        String string = "";
        for(Seguro a:listaSeguros) {
            string = string + a.getId() + " - Valor: R$" + a.getValorMensal() + "\n";
        }
        return string;
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
        id = Validacao.formatacaoId(id);
        for (i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).idCliente().equals(id))
                return true;
        }
            return false; 
    } 

    public int encontrarCliente(String id) { // retorna a posição do cliente na lista de clientes
            int i = 0;
            id = Validacao.formatacaoId(id);
            for (i = 0; i < listaClientes.size(); i++) {
                if (listaClientes.get(i).idCliente().equals(id))
                    return i;
            }
            System.out.println("Cliente não encontrado");
            return 0;
        }


    public void listarClientes(String tipoCliente) {
        ArrayList<Cliente> lista_tipo_cliente = new ArrayList<Cliente>();
        for (Cliente cliente_atual : listaClientes) {
            if (cliente_atual.getClass().getCanonicalName().equals(tipoCliente)) {
                lista_tipo_cliente.add(cliente_atual);
            }
        }
        System.out.print("----- Lista dos " + tipoCliente + "-----\n");
        System.out.println(lista_tipo_cliente);
    }

    public void addSeguro(Seguro seguro) {
        listaSeguros.add(seguro);
    }

    public void gerarSeguroPF(Date dataInicio, Date dataFim, Veiculo veiculo, ClientePF cliente ) {
        ArrayList<Sinistro> listaSinistros= new ArrayList<Sinistro>();
        ArrayList<Condutor> listaCondutores= new ArrayList<Condutor>();
        SeguroPF seguro = new SeguroPF(dataInicio, dataFim, this, listaSinistros, listaCondutores, veiculo, cliente);
        addSeguro(seguro);
    }

    public void gerarSeguroPJ(Date dataInicio, Date dataFim, Frota frota, ClientePJ cliente) {
        ArrayList<Sinistro> listaSinistros= new ArrayList<Sinistro>();
        ArrayList<Condutor> listaCondutores= new ArrayList<Condutor>();
        SeguroPJ seguro = new SeguroPJ(dataInicio, dataFim, this, listaSinistros, listaCondutores, frota, cliente);
        addSeguro(seguro);
    }

    public boolean gerarSeguro() throws ParseException {
        System.out.println("CPF/CNPJ do cliente: ");
        String id = scanner.nextLine();
        int index = 0;
        if (!clienteExiste(id)){
            System.out.println("Cliente não encontrado na seguradora");
            return false;
        }
        index = encontrarCliente(id);
        LocalDate aux = LocalDate.now();
        Date hoje = Date.from(aux.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("Data de encerramento do seguro: ");
        String dataOut = scanner.nextLine();
        Date data = sdf.parse(dataOut);
        if (listaClientes.get(index).getClass().getCanonicalName().equals("ClientePF")) {
            ClientePF cliente = (ClientePF) listaClientes.get(index);
            System.out.println("Qual carro deseja fazer o seguro?");
            if (cliente.getListaVeiculos().size() == 0) {
                System.out.println("O cliente não tem veículos registrados");
                return false;
            }
            cliente.printListaVeiculos();
            int pos_01 = Integer.parseInt(scanner.nextLine());
            gerarSeguroPF(hoje, data,cliente.getListaVeiculos().get(pos_01) , cliente);
            System.out.println("Seguro Criado com suceso");
            return true;

        } else if (listaClientes.get(index).getClass().getCanonicalName().equals("ClientePJ")) {
            ClientePJ cliente = (ClientePJ) listaClientes.get(index);
            System.out.println("Qual frota deseja fazer o seguro?");
            if (cliente.getListaFrota().size() == 0) {
                System.out.println("O cliente não tem nenhuma frota registrada");
                return false;
            }
            cliente.imprimeFrotas();
            int pos_02 = Integer.parseInt(scanner.nextLine());
            gerarSeguroPJ(hoje, data, cliente.getListaFrota().get(pos_02), cliente);
            System.out.println("Seguro Criado com suceso");
            return true;

        } else {
            System.out.println("Seguro não foi criado");
            return false; 
        }
    }

    public boolean cancelarSeguro() {
        System.out.println("CPF/CNPJ do cliente: ");
        String id = scanner.nextLine();
        if (!clienteExiste(id)) {
            return false;
        }
        ArrayList<Seguro> seguros_cliente = getSegurosPorCliente(id);
        int i = 0;
        for (Seguro s:seguros_cliente) {
            System.out.println(i + ")" + s.getId() + " - " + s.nomeCliente() + "\nVeículos assegurados: " + s.imprimeVeiculos());
            i++;
        }
        i = Integer.parseInt(scanner.nextLine());
        Seguro seguro = listaSeguros.get(i);
        listaSeguros.remove(seguro);
        System.out.println("Seguro removido com sucesso");
        return true;
    }

    public void cadastrarClientePF(String nome, String telefone, String endereco, String email) throws Exception {
        System.out.println("CPF: ");
        String cpf = scanner.nextLine();
        System.out.println("Genero: ");
        String gen = scanner.nextLine();
        System.out.println("Educação: ");
        String edu = scanner.nextLine();
        System.out.println("Data de Nascimento: ");
        Date dataNasc = sdf.parse(scanner.nextLine());
        ClientePF cliente = new ClientePF(nome, endereco, telefone, email, gen, edu, dataNasc, cpf);
        listaClientes.add(cliente);
    }

    public void cadastrarClientePJ(String nome, String telefone, String endereco, String email) throws Exception {
        System.out.println("CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.println("Quantidade de funcionários: ");
        int func = Integer.parseInt(scanner.nextLine());
        System.out.println("Data de fundação: ");
        Date dataFund = sdf.parse(scanner.nextLine());
        ClientePJ cliente = new ClientePJ(nome, endereco, telefone, email, cnpj, dataFund, func);
        listaClientes.add(cliente);
    }

    public boolean cadastrarCliente() throws Exception {
        System.out.println("Cadastrar cliente PF(1) ou PJ(2)? ");
        String op = scanner.nextLine();
        System.out.println("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.println("Telfone: ");
        String telfone = scanner.nextLine();
        System.out.println("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        if (op == "1") {
            cadastrarClientePF(nome, telfone, endereco, email);
            return true;
        } else if (op == "2") { 
            cadastrarClientePJ(nome, telfone, endereco, email);
            return true;
        } else {
            System.out.println("Opção inválida");
            return false;
        }
    }

    public boolean removerCliente() {
        System.out.println("CPF/CNPJ do cliente a ser removido: ");
        String id = scanner.nextLine();
        if (!clienteExiste(id)){ 
            return false;
        }
        int pos = encontrarCliente(id);
        Cliente cliente = listaClientes.get(pos);
        for (Seguro s:listaSeguros) {
            if (s.idCliente().equals(id))
                listaSeguros.remove(s);
        }
        listaClientes.remove(cliente);
        System.out.println("Cliente removido com sucesso");
        return true;
    }
    
    public void imprimirSeguros() {
        int i = 0;
        for (Seguro s:listaSeguros) {
            System.out.println(i + ")" + s.getId() + " - " + s.nomeCliente() + "\nVeículos assegurados: " + s.imprimeVeiculos());
        }
    }

    public static void adicionaSeguradora(Seguradora seguradora) {
        listaSeguradoras.add(seguradora);
    }

    public ArrayList<Seguro> getSegurosPorCliente(String id) {
        ArrayList<Seguro> lista = new ArrayList<Seguro>();
        for (Seguro s:listaSeguros) {
            if (s.getCliente().idCliente().equals(id)) {
                lista.add(s);
            }
        }
        return lista;
    }

    public ArrayList<Sinistro> getSinistroPorCliente(String id) {
        ArrayList<Seguro> seguros = getSegurosPorCliente(id);
        ArrayList<Sinistro> lista = new ArrayList<Sinistro>();
        for (Seguro s:seguros) {
            for (Sinistro sinistro:s.getListaSinistros()) {
                if (sinistro.getCondutor().equals(null))
                    lista.add(sinistro);
            }
        }
        return lista;
    }

    public double calcularReceita() {
        double soma = 0.0;
        for (Seguro s:listaSeguros) {
            s.calcularValor();
            soma = s.getValorMensal();
        }
        return soma;
    }


} 

