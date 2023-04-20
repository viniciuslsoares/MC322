import java.util.*;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        Scanner in = new Scanner(System.in);
        ClientePF cliente6 = new ClientePF("Pedro", "Campinas", "527.833.628-56",
        "M", null, "EM", null, "Média");
        ClientePJ cliente5 = new ClientePJ("Ricardo", "Jundiaí", "12.528.708/0001-07", null);
        Seguradora seguradora = new Seguradora("Porto Seguro", "(19)99999-9999", "portoseguro@gmail.com", "Taquaral");
        Veiculo carro1 = new Veiculo("FAB-6080", "Hyundai", "HB20", 2015);
        Veiculo carro2 = new Veiculo("FKY-2552", "Honda", "Civic", 2016);
        cliente6.adicionaVeiculo(carro2);
        cliente5.adicionaVeiculo(carro1);
        seguradora.cadastrarClientes(cliente6);
        seguradora.cadastrarClientes(cliente5);
        
        System.out.print("---------------- Menu Seguradora ----------------\n");

        //Declaração de variáveis auxiliares ao longo do Menu
        String str1, str2, str3, str4, str5, str6;
        String op = "0";
        String data01, data02;
        Date data1, data2;
        int int1, i = 0, j = 0;
        Veiculo carro;
        Cliente cliente_aux;
        
        while (true) {

            System.out.print("\nSelecione sua opção: \n");
            System.out.print("1) Printar dados da Seguradora\n");
            System.out.print("2) Cadastrar um novo Cliente\n");
            System.out.print("3) Adicionar Veículo\n");
            System.out.print("4) Remover Veículo\n");
            System.out.print("5) Imprimir Clientes\n");
            System.out.print("6) Adicionar Sinistro\n");
            System.out.print("7) Remover Cliente\n");
            System.out.print("8) Visualizar Sinistros\n");
            System.out.print("9) Sair\n");

            op = in.nextLine();

            switch (op) {
                case "1": 
                    //Impressão da seguradora

                    System.out.print(seguradora);
                    break;

                case "2": 
                    //Cadastro de pessoas

                    System.out.print("Selecione o tipo de cliente:\n");
                    System.out.print("1) Cliente Pessoa Física\n");
                    System.out.print("2) Cliente Pessoa Jurídica\n");
                    op = in.nextLine();

                    switch (op) {
                        case "1":
                        //Dados pessoa física

                            System.out.print("Nome: ");
                            str1 = in.nextLine();
                            System.out.print("Endereco: ");
                            str2 = in.nextLine();
                            System.out.print("Cpf: ");
                            str3 = in.nextLine();
                            System.out.print("Gênero: ");
                            str4 = in.nextLine();
                            System.out.print("Educação: ");
                            str5 = in.nextLine();
                            System.out.print("Classe Econômica: ");
                            str6 = in.nextLine();
                            System.out.print("Data da Licença (dd-mm-yyy): ");
                            data01 = in.nextLine();
                            data1 = sdf.parse(data01);
                            System.out.print("Data de Nascimento (dd-mm-yyy): ");
                            data02 = in.nextLine();
                            data2 = sdf.parse(data02);
                            ClientePF clientePF = new ClientePF(str1, str2, str3, 
                                        str4, data1, str5, data2, str6);
                            seguradora.cadastrarClientes(clientePF);
                            break;

                        case "2":    
                        //Dados pessoa jurídica
                            
                            System.out.print("Nome: ");
                            str1 = in.nextLine();
                            System.out.print("Endereco: ");
                            str2 = in.nextLine();
                            System.out.print("CNPJ: ");
                            str3 = in.nextLine();
                            System.out.print("Data de Fundalção (dd-mm-yyy): ");
                            data01 = in.nextLine();
                            data1 = sdf.parse(data01);
                            ClientePJ clientePJ = new ClientePJ(str1, str2, str3, data1);
                            seguradora.cadastrarClientes(clientePJ);
                            break;
                    }
                    break;
                
                case "3":
                    System.out.print("Cpf ou Cnpj do Cliente: ");
                    str1 = in.nextLine();
                    str1 = ClientePJ.formatacaoCnpj(str1);
                    if (seguradora.clienteExiste(str1)) {

                        i = seguradora.encontrarCliente(str1);
                        System.out.print("Placa do carro: ");
                        str2 = in.nextLine();
                        System.out.print("Marca: ");
                        str3 = in.nextLine();
                        System.out.print("Modelo: ");
                        str4 = in.nextLine();
                        System.out.print("Ano de Fabricação: ");
                        str5 = in.nextLine();
                        int1 = Integer.parseInt(str5);
                        carro = new Veiculo(str2, str3, str4, int1);
                        seguradora.getListaClientes().get(i).adicionaVeiculo(carro);
                    } else {
                        System.out.println("Cliente não cadastrado");
                    }
                    break;
                
                case "4":
                    System.out.print("Cpf ou CNPJ do proprietário: ");
                    str1 = in.nextLine();
                    if (! seguradora.clienteExiste(str1)) {
                        System.out.println("Cliente não encontrado\n");
                        break;
                    }
                    i = seguradora.encontrarCliente(str1);
                    
                    System.out.print("Placa Veículo: ");
                    str2 = in.nextLine();
                    if (! seguradora.getListaClientes().get(i).existeVeiculo(str2)) {
                        System.out.println("Veículo não encontrado\n");
                        break;
                    }
                    j = seguradora.getListaClientes().get(i).encontrarVeiculo(str2);
                    carro = seguradora.getListaClientes().get(i).getListaVeiculos().get(j);
                    seguradora.getListaClientes().get(i).removeVeiculo(carro);                
                    break;

                case "5":
                    System.out.print("Pessoa Física (1) ou Jurídica (2): ");
                    str1 = in.nextLine();
                    switch (str1) {
                        case "1":
                            System.out.print("Clientes PF\n");
                            seguradora.listarClientes("ClientePF");
                            break;
                        case "2":
                            System.out.print("Clientes PJ\n");
                            seguradora.listarClientes("ClientePJ");
                            break;
                    }
                    break;

                case "6":
                    System.out.print("Cpf ou CNPJ do proprietário: ");
                    str1 = in.nextLine();
                    if (! seguradora.clienteExiste(str1)) {
                        System.out.println("Cliente não encontrado\n");
                        break;
                    }
                    i = seguradora.encontrarCliente(str1);

                    cliente_aux = seguradora.getListaClientes().get(i);
                    System.out.print("Veículos registrados: \n");
                    for (Veiculo veiculo : cliente_aux.getListaVeiculos()) {
                        System.out.print(" -- " + veiculo.getPlaca() + "\n");
                    }
                    System.out.print("Placa Veículo: ");
                    str2 = in.nextLine();
                    if (! seguradora.getListaClientes().get(i).existeVeiculo(str2)) {
                        System.out.println("Veículo não encontrado\n");
                        break;
                    }
                    j = seguradora.getListaClientes().get(i).encontrarVeiculo(str2);
                    carro = seguradora.getListaClientes().get(i).   getListaVeiculos().get(j);
                    System.out.print("Data: ");
                    data01 = in.nextLine();
                    data1 = sdf.parse(data01);
                    System.out.print("Endereço: ");
                    str1 = in.nextLine();
                    seguradora.gerarSinistro(cliente_aux, data1, str1, carro);
                    break;

                case "7":
                    System.out.print("Cpf ou CNPJ do cliente: ");
                    str1 = in.nextLine();
                    if (! seguradora.clienteExiste(str1)) {
                        System.out.println("Cliente não encontrado\n");
                        break;
                    }
                    i = seguradora.encontrarCliente(str1);
                    cliente_aux = seguradora.getListaClientes().get(i);
                    seguradora.getListaClientes().remove(cliente_aux);
                    break;
                
                case "8":
                    System.out.print("Cpf ou CNPJ do cliente: ");
                    str1 = in.nextLine();
                    seguradora.visualizarSinistro(str1);
                    break;

                case "9":
                    in.close();
                    return;
            }
        }
    } 
}
