import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// ------------- Pronto! -------------- // 

public abstract class Seguro {
    private final int id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;
    private double valorMensal;

    private static ArrayList<Integer> listaIds = new ArrayList<Integer>();
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");

    //Construtor
    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora, 
    ArrayList<Sinistro> listaSinistros, ArrayList<Condutor> listaCondutores) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = listaSinistros;
        this.listaCondutores = listaCondutores;
        int temp = rand.nextInt(1000000);
        while (listaIds.contains(temp)) {
            temp = rand.nextInt(1000000);
        }
        this.id = temp;
        this.valorMensal = 0;
    }

    public int getId() {
        return id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public String listaCondutores() {
        String string = "";
        for (Condutor c:listaCondutores) {
            string += c.getNome() + " - cpf:" + c.getCpf() +"\n";
        }
        return string;
    }

    @Override
    public String toString() {
        return "ID: " + id + ";Período: " + dataInicio + " a " + dataFim + ";\nValor: " + valorMensal
        + ";Seguradora: " + seguradora.getNome() + ";\nSinistros: " + listaSinistros + ";\nCondutores: " 
        + listaCondutores();
    }

    public boolean autoriazarCondutor() throws Exception {
        System.out.println("Nome do condutor a ser autorizado: ");
        String nome = scanner.nextLine();
        System.out.println("CPF: ");
        String cpf = scanner.nextLine();
        System.out.println("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.println("Endereco: ");
        String endereco = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Data de nascimento: ");
        Date dataNasc = sdf.parse(scanner.nextLine());
        ArrayList<Sinistro> lista = new ArrayList<Sinistro>();
        Condutor novo = new Condutor(cpf, nome, telefone, email,endereco, dataNasc, lista);
        adicionarCondutor(novo);
        System.out.println("Condutor " + nome + " adicionado com sucesso");
        return true;
    }

    public void adicionarCondutor(Condutor condutor) {
        listaCondutores.add(condutor);
    }

    public boolean desautorizarCondutor() {
        System.out.println("Qual condutor deseja remover?");
        imprimeCondutores();
        int op = Integer.parseInt(scanner.nextLine());
        if (op <= listaCondutores.size()) {
            listaCondutores.remove(op);
            return true;
        } else return false;
    }

    public double calcularValor() {
        return 0;
    }

    public boolean gerarSinistro() throws ParseException {
        System.out.println("Data do sinistro: ");
        Date data = sdf.parse(scanner.nextLine());
        System.out.println("Local do sinistro: ");
        String endereco = scanner.nextLine();
        System.out.println("O responsável foi o dono do Seguro (1) ou um condutor autorizado (2)?");
        String resp = scanner.nextLine();
        if (resp.equals("1")) {
            Sinistro novo = new Sinistro(data, endereco, null, this);
            listaSinistros.add(novo);
        } else if (resp.equals("2")) {
            System.out.println("Qual o condutor envolvido?");
            if (listaCondutores.size() == 0) {
                System.out.println("Nenhum condutor autorizado");
                System.out.println("Sinistro não registrado");
                return false;
            }
            imprimeCondutores();
            int op = Integer.parseInt(scanner.nextLine());
            Condutor temp = listaCondutores.get(op);
            Sinistro novo = new Sinistro(data, endereco, temp, this);
            listaSinistros.add(novo);
        }
        return true;
    }

    public void imprimeCondutores() {
        int i = 0;
        if (listaCondutores.size() == 0) 
            System.out.println("Nenhum condutor cadastrado");
        for (Condutor c:listaCondutores) {
            System.out.println(i + ")" + c.getNome());
        }
    }
    
    public String nomeCliente() {
        return "";
    }

    public String imprimeVeiculos() {
        return "";
    }

    public Cliente getCliente() {
        return null;
    }

    public String idCliente() {
        return "";
    }

}
