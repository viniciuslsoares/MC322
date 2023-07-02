import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;

public class Main {

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        Date data = sdf.parse("09-05-1980"); // Data auxiliar para as instanciações

        // Instância da Seguradora
        Seguradora seguradora = new Seguradora("12.528.708/0001-07", "Porto Seguro", "(19)99999-9999",
                "portoseguro@gmail.com", "Taquaral");
        Seguradora.adicionaSeguradora(seguradora);
        Seguradora seguradora2 = new Seguradora("00.394.460/0058-87", "Tokyo", "(19)3869-6871", "tokyo@gmail.com",
                "Campinas");
        Seguradora.adicionaSeguradora(seguradora2);

        // Instâncias de Clientes
        ClientePF cliente1 = new ClientePF("Pedro", "Campinas", "(19)99904-7277",
                "pedro@gmail.com", "M", "EM", data, "527.833.628-56");
        seguradora.cadastrarClientes(cliente1);
        ClientePJ cliente2 = new ClientePJ("Ricardo", "Valinhos", "(19)99741-7040",
                "ricardo@gmail.com", "12.528.708/0001-07", data, 3);
        seguradora.cadastrarClientes(cliente2);
        ClientePF cliente3 = new ClientePF("Caio", "Paulínia", "(19)99703-6173",
                "caio@gmail.com", "M", "EM", data, "111.444.777-35");
        seguradora2.cadastrarClientes(cliente3);
        ClientePJ cliente4 = new ClientePJ("Michelle", "São Paulo", "(19)99904-7277",
                "michelle@gmail.com", "13.929.711/0001-97", data, 5);
        seguradora2.cadastrarClientes(cliente4);

        // Instância de Veículos
        Veiculo veiculo1 = new Veiculo("FAB-6086", "Hyundai", "HB20", 2015);
        Veiculo veiculo2 = new Veiculo("FKY-2552", "Honda", "Civic", 2016);
        Veiculo veiculo3 = new Veiculo("ABC-1234", "Ford", "Fusion", 2018);
        Veiculo veiculo4 = new Veiculo("XYZ-6789", "Jeep", "Renegade", 2018);
        Veiculo veiculo5 = new Veiculo("GHI-4567", "Kia", "Picanto", 2014);

        // Instância de Frota
        ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
        lista.add(veiculo3);
        lista.add(veiculo4);
        Frota frota = new Frota("0", lista);

        cliente1.cadastrarVeiculo(veiculo1);
        cliente2.cadastrarFrota();
        System.out.println(" == Adicionar Veículo por frota");
        cliente2.adicinarVeiculoFrota(veiculo2); // Pede qual frota deseja adicionar o veículo
        cliente3.cadastrarVeiculo(veiculo5);
        cliente4.cadastrarFrota(frota);

        /*
         * NOTE
         * Os dois seguros foram instanciados manualmente (fora do método específico)
         * pois esse método pede
         * interação através do terminal. Esses métodos seguem na main.
         * Essa instanciação também é necessária para gerar o sinistro através do método
         * apropriado
         */

        // Instância de seguro
        LocalDate aux = LocalDate.now();
        Date hoje = Date.from(aux.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dataFim = sdf.parse("10-10-2025");

        ArrayList<Sinistro> lista_sinistro01 = new ArrayList<Sinistro>();
        ArrayList<Sinistro> lista_sinistro02 = new ArrayList<Sinistro>();
        ArrayList<Condutor> lista_condutores01 = new ArrayList<Condutor>();
        ArrayList<Condutor> lista_condutores02 = new ArrayList<Condutor>();

        SeguroPF seguro1 = new SeguroPF(hoje, dataFim, seguradora, lista_sinistro01, lista_condutores01, veiculo1,
                cliente1);
        SeguroPJ seguro2 = new SeguroPJ(hoje, dataFim, seguradora, lista_sinistro02, lista_condutores02, frota,
                cliente4);
        seguradora.addSeguro(seguro1);
        seguradora.addSeguro(seguro2);

        // Método para instância do seguro

        System.out.println(" == Gerar Seguro para seguradora " + seguradora.getNome());
        seguradora.gerarSeguro(); // Usar cpf 527.833.628-56
        System.out.println(" == Gerar Seguro para seguradora " + seguradora2.getNome());
        seguradora2.gerarSeguro(); // Usar cnpj 13.929.711/0001-97

        // Método para instância do sinistro
        System.out.println(" == Gerar Sinistro");
        seguro1.gerarSinistro();
        System.out.println(" == Gerar Sinistro");
        seguro2.gerarSinistro();

        // Instância de Condutores
        ArrayList<Sinistro> lista_sinistro03 = new ArrayList<Sinistro>();
        Condutor condutor = new Condutor("52783362856", "João", "(99)99999-9999", "joão@gmail.com",
                "Floripa", data, lista_sinistro03);
        seguro1.adicionarCondutor(condutor);
        System.out.println(" == Autorizar um novo condutor");
        seguro2.autoriazarCondutor(); // Utilizar CPF 406.827.980-29

        // Instâncias dos arquivos
        ArquivoVeiculos arquivoVeiculos = new ArquivoVeiculos();
        ArrayList<Veiculo> importaVeiculos = (ArrayList<Veiculo>) arquivoVeiculos
                .lerArquivo("/home/vinicius_leme/projects/MC322/lab06/files/veiculos.csv");

        ArquivoFrota arquivoFrota = new ArquivoFrota();
        ArrayList<Frota> importaFrotas = (ArrayList<Frota>) arquivoFrota.lerArquivo(
                "/home/vinicius_leme/projects/MC322/lab06/files/frotas.csv",
                importaVeiculos);

        ArquivoClientePF arquivoClientePF = new ArquivoClientePF();
        ArrayList<ClientePF> importaClientePF = (ArrayList<ClientePF>) arquivoClientePF
                .lerArquivo("/home/vinicius_leme/projects/MC322/lab06/files/clientesPF.csv", lista);

        ArquivoClientePJ arquivoClientePJ = new ArquivoClientePJ();
        ArrayList<ClientePJ> importaClientePJ = (ArrayList<ClientePJ>) arquivoClientePJ
                .lerArquivo("/home/vinicius_leme/projects/MC322/lab06/files/clientesPJ.csv", importaFrotas);

        ArquivoCondutor arquivoCondutor = new ArquivoCondutor();
        ArrayList<Condutor> importaCondutores = (ArrayList<Condutor>) arquivoCondutor
                .lerArquivo("/home/vinicius_leme/projects/MC322/lab06/files/condutores.csv");

        for (Cliente cliente : importaClientePF)
            seguradora.cadastrarClientes(cliente);

        for (Cliente cliente : importaClientePJ)
            seguradora.cadastrarClientes(cliente);

        // Menu Interativo através da Classe Estática Menu
        Menu.MenuInterativo();

        ArquivoSeguro arquivoSeguro = new ArquivoSeguro();
        arquivoSeguro.gravarArquivo("/home/vinicius_leme/projects/MC322/lab06/files/seguro.csv", null);
        ArquivoSinistro arquivoSinistro = new ArquivoSinistro();
        arquivoSinistro.gravarArquivo("/home/vinicius_leme/projects/MC322/lab06/files/sinistros.csv", null);
    }
}