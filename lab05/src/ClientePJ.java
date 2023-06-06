import java.util.*;
import java.time.*;

// -------------- Atualizar Frota ---------------

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;
    private int qtdeFuncionarios;
    private ArrayList<Frota> listaFrota;

    ArrayList<Frota> lista_01 = new ArrayList<Frota>();
    Scanner scanner = new Scanner(System.in);


    public ClientePJ(String nome, String endereco, String telefone, 
            String email, String cnpj, Date dataFundacao, 
            int qtdeFuncionarios) throws Exception {
        
        super(nome, endereco, telefone, email);
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
        this.listaFrota = lista_01;
        if (Validacao.validaCnpj(cnpj)) {
            this.cnpj = Validacao.formatacaoId(cnpj);
        } else {
            throw new Exception("CNPJ inválido");
        }
    }

    public String getCnpj() {
        return cnpj;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    public void setListaFrota(ArrayList<Frota> frota) {
        this.listaFrota = frota;
    }

    public ArrayList<Frota> getListaFrota() {
        return listaFrota;
    }

    @Override
    public String toString() {
        return super.toString() + "CNPJ: " + cnpj + " - Data de Fundação: "
        + dataFundacao + "; Funcionários: " + qtdeFuncionarios+ ";\n"
        + listaFrota + ";\n";
    }

    public boolean cadastrarFrota() {
        ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
        Frota nova = new Frota(lista);
        listaFrota.add(nova);
        return true;
    }

    public boolean cadastrarFrota(Frota frota) {
        listaFrota.add(frota);
        return true;
    }

    public void adicionarFrota() {
        ArrayList<Veiculo> lista_aux = new ArrayList<Veiculo>();
        Frota frota = new Frota(lista_aux);
        listaFrota.add(frota);
    }

    public void listarFrotas() {
        int i = 0;
        for (Frota f:listaFrota) {
            System.out.println(i + ") " + f);
            i++;
        }
    }

    public boolean adicinarVeiculoFrota (Veiculo veiculo) {
        System.out.println("Escolha a qual frota adicionar o veículo:");
        listarFrotas();
        String index = scanner.nextLine();
        listaFrota.get(Integer.parseInt(index)).addVeiculo(veiculo);
        return true;
    }

    public boolean removerVeiculo() {
        listarFrotas();
        String index = scanner.nextLine();
        Frota frota = listaFrota.get(Integer.parseInt(index));
        frota.listaVeiculos();
        index = scanner.nextLine();
        Veiculo veiculo = frota.getListaVeiculos().get(Integer.parseInt(index));
        if (frota.removeVeiculo(veiculo))
            return true;
        else return false;
    }

    public boolean removerVeiculoFrota(Veiculo veiculo, String code) {
        for (Frota a:listaFrota) {
            if (a.getCode() == code) {
                if (a.removeVeiculo(veiculo))
                    return true;
                    else return false;
            } else {
                System.out.println("Frota não encontrada");
                return false;
            }
        }
        return false;
    }

    public boolean removeFrota(String code) {
        for (Frota a:listaFrota) {
            if (a.getCode() == code) {
                listaFrota.remove(a);
            } else {
                System.out.println("Frota não encontrada");
                return false;
            }
            }
        return false;
    }

    public boolean atualizarFrota() {
        boolean flag = true;
        System.out.println("(1) Adicionar veículo em uma frota\n(2) Remover veículo de uma frota\n(3) Remover uma frota");
        String op = scanner.nextLine();
        switch (op) {

            case("1"):
            System.out.println("Escolha a frota:");
            listarFrotas();
            int index = Integer.parseInt(scanner.nextLine());
            System.out.println("Placa do carro: ");
            String placa = scanner.nextLine();
            System.out.println("Marca do carro: ");
            String marca = scanner.nextLine();
            System.out.println("Modelo do carro: ");
            String modelo = scanner.nextLine();
            System.out.println("Ano de fabricação: ");
            int ano = Integer.parseInt(scanner.nextLine());
            Frota frota = listaFrota.get(index);
            Veiculo aux = new Veiculo(placa, marca, modelo, ano);
            frota.addVeiculo(aux);
            System.out.println("Veículo " + aux.getPlaca() + " adicionado com sucesos");
            break;

            case("2"):
            System.out.println("Qual a frota do veículo?");
            listarFrotas();
            index = Integer.parseInt(scanner.nextLine());
            frota = listaFrota.get(index);
            System.out.println("Qual veículo deseja remover?");
            frota.listaVeiculos();
            index = Integer.parseInt(scanner.nextLine());
            frota.removeVeiculo(frota.getListaVeiculos().get(index));
            System.out.println("Veículo removido com sucesos");
            break;

            case("3"):
            System.out.println("Qual frota deseja remover?");
            listarFrotas();
            index = Integer.parseInt(scanner.nextLine());
            listaFrota.remove(index);
            System.out.println("Frota removida com sucesso?");
            break;

            default:
            System.out.println("Opção inválida");
            flag = false;
            break;
        }

        return flag;
    }

    public boolean getVeiculosPorFrota(String code) {
        Frota aux = new Frota(null);
        for (Frota a:listaFrota){
            if (a.getCode() == code) {
                aux = a;
            } else {
                System.out.println("Frota não encontrada");
                return false;
            }
        }
        for (Veiculo v:aux.getListaVeiculos()) {
            System.out.println(v);
        }
        return true;
    }

    public void imprimeFrotas() {
        int i = 0;
        for (Frota f:listaFrota) {
            System.out.println(i + ") Frota código: " + f.getCode());
            i++;
        }
    }
        
    @Override
    public String idCliente() {
        return cnpj;
    }
    
    public int getAno() {
        LocalDate hoje = LocalDate.now();
        LocalDate aux = dataFundacao.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period periodo = Period.between(aux, hoje);
        return periodo.getYears();
    }
    


} 