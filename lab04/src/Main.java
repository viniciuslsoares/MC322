import java.util.*;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        Date data = sdf.parse("09-05-1980"); // Data auxiliar para as instanciações 

        // Instância da Seguradora
        Seguradora seguradora = new Seguradora("Porto Seguro", "(19)99999-9999", "portoseguro@gmail.com", "Taquaral");
        Seguradora.adicionaSeguradora(seguradora);

        // Instâncias de Clientes
        ClientePF cliente1 = new ClientePF("Pedro", "Campinas", "527.833.628-56",
        "M", null, "EM", data, "Média");
        seguradora.cadastrarClientes(cliente1);
        ClientePJ cliente2 = new ClientePJ("Ricardo", "Jundiaí", "12.528.708/0001-07", null, 2);
        seguradora.cadastrarClientes(cliente2);

        // Instâncias de Veículos
        Veiculo carro1 = new Veiculo("FAB-6080", "Hyundai", "HB20", 2015);
        cliente1.adicionaVeiculo(carro1);
        Veiculo carro2 = new Veiculo("FKY-2552", "Honda", "Civic", 2016);
        cliente2.adicionaVeiculo(carro2);
        
        // Instância de dois sinistros (pelo método gerarSinistro() da seguradora)
        seguradora.gerarSinistro(cliente1, data, "Campinas", carro1);
        seguradora.gerarSinistro(cliente2, data, "Valinhos", carro2);
        
        // Métodos da seguradora
        seguradora.listarClientes("ClientePF");
        seguradora.listarClientes("ClientePJ");
        seguradora.visualizarSinistro(cliente1.idCliente());
        seguradora.listarSinistros();
        System.out.println("Receita da Seguradora: " + seguradora.calculaReceita());
        System.out.println("Preço do Seguro " + cliente1.getNome() + ": " + seguradora.calcularPrecoSeguroCliente(cliente1));
        System.out.println("Preço do Seguro " + cliente2.getNome() + ": " + seguradora.calcularPrecoSeguroCliente(cliente2));

        // Menu Interativo através da Classe Estática Menu
        Menu.MenuInterativo();
    }
}