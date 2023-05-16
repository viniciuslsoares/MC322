import java.util.*;
import java.text.SimpleDateFormat;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static Operacoes op_atual = Operacoes.MENU_INICIAL; // Indicador de operação atual
    private static Operacoes menu_atual = Operacoes.MENU_INICIAL;  // Indica o menu onde o programa está
    private static boolean flag = true; // flag para parada de execução
    private static double id_operacao = 0;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
    private static String menu_inicial = "------ Menu Inicial ----- \n1)Cadastros\n2)Listar\n3)Excluir\n4)Gerar Sinistro\n5)Transferir Seguro\n6)Calcular Receita\n0)Sair\n--------------------------\n";

    public static void leOperacao() {

        // Caso em que se encontra no Menu Inicial
        if (menu_atual.equals(Operacoes.MENU_INICIAL)) {
            return;

        // Caso não esteja no Menu Inicial
        } else {
            id_operacao = (menu_atual.getOperacoes() + 0.1 * id_operacao);
        }
    }
    
    public static Seguradora encontraSeguradora() {
        System.out.println("Qual a seguradora do cliente? ");
        for (int i = 0; i < Seguradora.getListaSeguradoras().size(); i++) {
            System.out.println(i + ") Seguradora " + Seguradora.getListaSeguradoras().get(i).getNome());
        }
        String index = scanner.nextLine();
        int pos = Integer.parseInt(index);
        Seguradora seguradora = Seguradora.getListaSeguradoras().get(pos);      
        return seguradora;
    }

    public static void menuInicial() {
        // Seta o menu atual como o menu_inicial e imprime as opções
        menu_atual = Operacoes.MENU_INICIAL;
        System.out.print(menu_inicial);
    }
    

    public static void execOperacao(Operacoes operacao) throws Exception {

        switch (operacao) {

            case MENU_INICIAL:
                menuInicial();
                break;


            case CADASTAR_VEICULO:
                
                Seguradora seguradora_atual = encontraSeguradora();
                System.out.print("A qual Cliente o veículo pertence (cpf/cnpj): ");
                String id = scanner.nextLine();
                if (!Validacao.validaNumero(id)) {
                    System.out.println("--- Número inválido ---");
                    
                    menuInicial();
                    break;
                }
                if (!seguradora_atual.clienteExiste(id)) {
                    System.out.println("Cliente não encontrado na Seguradora");
                    
                    menuInicial();
                    break;
                }
                System.out.print("Placa do veículo: ");
                String placa = scanner.nextLine();
                System.out.print("Marca do veículo: ");
                String marca = scanner.nextLine();
                System.out.print("Modelo do veículo: ");
                String modelo = scanner.nextLine();
                System.out.print("Ano do veículo: ");
                String ano = scanner.nextLine();
                if (!Validacao.validaNumero(ano)) {
                    System.out.println("Ano inválido -- Veículo não cadastrado");
                    
                    menuInicial();
                    break;
                }
                int anoFab = Integer.parseInt(ano);
                Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFab);
                int index_cliente = seguradora_atual.encontrarCliente(id);
                seguradora_atual.getListaClientes().get(index_cliente).adicionaVeiculo(veiculo);
            
                menuInicial();
                break;


            case CADASTRAR_CLIENTE:
                
                seguradora_atual = encontraSeguradora();
                System.out.println("Criar um cliente PF (1) ou PJ (2)?");
                String opcao = scanner.nextLine();
                if (opcao.equals("1")) {
                    System.out.println("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("Endereco: ");
                    String endereco = scanner.nextLine();
                    System.out.println("CPF: ");
                    id = scanner.nextLine();
                    System.out.println("Genero: ");
                    String genero = scanner.nextLine();
                    System.out.println("Data da licença: ");
                    String data = scanner.nextLine();
                    Date data1 = sdf.parse(data);
                    System.out.println("Educacao: ");
                    String educacao = scanner.nextLine();
                    System.out.println("Data de nascimento: ");
                    data = scanner.nextLine();
                    Date data2 = sdf.parse(data);
                    System.out.println("Classe Economica: ");
                    String classe = scanner.nextLine();
                    ClientePF cliente = new ClientePF(nome, endereco, id, genero, data1, educacao, data2, classe);
                    seguradora_atual.cadastrarClientes(cliente);
                } else if (opcao.equals("2")) {
                    System.out.println("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("Endereco: ");
                    String endereco = scanner.nextLine();
                    System.out.println("CNPJ: ");
                    id = scanner.nextLine();
                    System.out.println("Data de fundaao: ");
                    String data = scanner.nextLine();
                    Date data1 = sdf.parse(data);
                    System.out.println("Quantidade de funcionários: ");
                    String funcionarios = scanner.nextLine();
                    int qntFunc = Integer.parseInt(funcionarios);
                    ClientePJ cliente = new ClientePJ(nome, endereco, id, data1, qntFunc);
                    seguradora_atual.cadastrarClientes(cliente);
                } else {
                    System.out.println("----- Opção inválida --- cliente não registrado");
                    
                    menuInicial();
                }

                menuInicial();
                break;


            case CADASTRAR_SEGURADORA:
                System.out.println("Nome da seguradora: ");
                String nome = scanner.nextLine();
                System.out.println("Telefone: ");
                String telefone = scanner.nextLine();
                System.out.println("Email: ");
                String email = scanner.nextLine();
                System.out.println("Endereco: ");
                String endereco = scanner.nextLine();
                Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
                Seguradora.getListaSeguradoras().add(seguradora);

                menuInicial();
                break;


            case CADASTROS:
                menu_atual = Operacoes.CADASTROS;
                System.out.println("--------------------------");
                System.out.println("1)Cadastrar Cliente PF/PJ\n2)Cadastrar Veículo");
                System.out.println("3)Cadastrar Seguradora\n4)Voltar");
                break;


            case CALCULAR:
                menu_atual = Operacoes.CALCULAR;
                System.out.println("--------------------------");
                System.out.println("1)Calcular Receita de Seguradora\n2)Calcular Seguro do Cliente");
                break;


            case CALCULAR_RECEITA:
                
                seguradora_atual = encontraSeguradora();
                System.out.println("A receita da Seguradora " + seguradora_atual.getNome() + ": R$" + seguradora_atual.calculaReceita());
                
                menuInicial();
                break;


            case CALCULAR_SEGURO_CLIENTE:
                
                seguradora_atual = encontraSeguradora();
                System.out.print("Qual cpf/cnpj do Cliente? ");
                id = scanner.nextLine();
                if (!Validacao.validaNumero(id)) {
                    System.out.println("--- Número inválido --- Cliente não removido");
                    menuInicial();
                    break;
                }
                if (!seguradora_atual.clienteExiste(id)) {
                    System.out.println("Cliente não encontrado na Seguradora");
                    
                    menuInicial();
                    break;
                }
                index_cliente = seguradora_atual.encontrarCliente(id);
                Cliente cliente = seguradora_atual.getListaClientes().get(index_cliente);
                System.out.println("O preço é: R$" + seguradora_atual.calcularPrecoSeguroCliente(cliente));

                menuInicial();
                break;


            case EXCLUIR:
                menu_atual = Operacoes.EXCLUIR;
                System.out.println("--------------------------");
                System.out.println("1)Excluir Cliente\n2)Excluir Veículo");
                System.out.println("3)Excluir Sinistro\n4)Voltar");
                break;


            case EXCLUIR_CLIENTE:
                
                seguradora_atual = encontraSeguradora();
                System.out.print("Qual cpf/cnpj do Cliente? ");
                id = scanner.nextLine();
                if (!Validacao.validaNumero(id)) {
                    System.out.println("--- Número inválido --- Cliente não removido");
                    
                    menuInicial();
                    break;
                }
                if (!seguradora_atual.clienteExiste(id)) {
                    System.out.println("Cliente não encontrado na Seguradora");
                    
                    menuInicial();
                    break;
                }
                index_cliente = seguradora_atual.encontrarCliente(id);
                cliente = seguradora_atual.getListaClientes().get(index_cliente);
                seguradora_atual.getListaClientes().remove(cliente);
                menuInicial();
            break;


            case EXCLUIR_SINISTRO:
                
                seguradora_atual = encontraSeguradora();
                for (int i = 0; i < seguradora_atual.getListaSinistros().size(); i++) {
                    System.out.println(i + ")" + seguradora_atual.getListaSinistros().get(i));
                }
                opcao = scanner.nextLine();
                Sinistro remocao = seguradora_atual.getListaSinistros().get(Integer.valueOf(opcao));
                ArrayList<String> lista_ = new ArrayList<String>();    
                for (int i = 0; i < seguradora_atual.getListaSinistros().size(); i++) {
                    lista_.add(String.valueOf(i));
                }
                if (!lista_.contains(opcao)) {
                    System.out.println("-----Opção inválida-----");
                    
                    menuInicial();
                    break;
                } else {
                    seguradora_atual.getListaSinistros().remove(remocao);
                }

                menuInicial();
                break;


            case EXCLUIR_VEICULO:
                
                seguradora_atual = encontraSeguradora();
                System.out.print("Qual cpf/cnpj do Cliente? ");
                id = scanner.nextLine();
                if (!Validacao.validaNumero(id)) {
                    System.out.println("--- Número inválido --- Sinistro não gerado");
                    
                    menuInicial();
                    break;
                }
                if (!seguradora_atual.clienteExiste(id)) {
                    System.out.println("Cliente não encontrado na Seguradora");
                    
                    menuInicial();
                    break;
                }
                index_cliente = seguradora_atual.encontrarCliente(id);
                cliente = seguradora_atual.getListaClientes().get(index_cliente);
                System.out.println("Escolha o carro a ser excluído: ");
                for (int i = 0; i < cliente.getListaVeiculos().size(); i++)
                    System.out.println(i + ")" + cliente.getListaVeiculos().get(i).getPlaca());
                ArrayList<String> lista = new ArrayList<String>();    
                opcao = scanner.nextLine();
                Veiculo exclusao = cliente.getListaVeiculos().get(Integer.valueOf(opcao));
                for (int i = 0; i < cliente.getListaVeiculos().size(); i++) {
                    lista.add(String.valueOf(i));
                }
                if (!lista.contains(opcao)) {
                    System.out.println("-----Opção inválida-----");
                    
                    menuInicial();
                    break;
                } else {
                    cliente.getListaVeiculos().remove(exclusao);
                }
                
                menuInicial();
                break;


            case GERAR_SINISTRO:
                
                seguradora_atual = encontraSeguradora();
                System.out.print("Qual cpf/cnpj do Cliente? ");
                id = scanner.nextLine();
                if (!Validacao.validaNumero(id)) {
                    System.out.println("--- Núero inválido --- Sinistro não gerado");
                    
                    menuInicial();
                    break;
                }
                if (!seguradora_atual.clienteExiste(id)) {
                    System.out.println("Cliente não encontrado na Seguradora");
                    
                    menuInicial();
                    break;
                }
                index_cliente = seguradora_atual.encontrarCliente(id);
                cliente = seguradora_atual.getListaClientes().get(index_cliente);
                System.out.println("Qual o carro relacionado ao Sinistro?");
                for (int i = 0; i < cliente.getListaVeiculos().size(); i++) {
                    System.out.println(i + ") " + cliente.getListaVeiculos().get(i).getPlaca());
                }
                String index = scanner.nextLine();
                ArrayList<String> lista_aux = new ArrayList<String>();
                for (int i = 0; i < cliente.getListaVeiculos().size(); i++) {
                    lista_aux.add(String.valueOf(i));
                }
                if (!lista_aux.contains(index)) {
                    System.out.println("----- Opção inválida ----- ");
                    
                    menuInicial();
                    break;
                }
                veiculo = cliente.getListaVeiculos().get(Integer.valueOf(index));
                System.out.println("Data do Sinistro: ");
                String data = scanner.nextLine();
                Date data1 = sdf.parse(data);
                System.out.println("Enereço: ");
                endereco = scanner.nextLine();
                if(!seguradora_atual.gerarSinistro(cliente, data1, endereco, veiculo)) {
                    System.out.println("Sinistro não gerado");
                    
                    menuInicial();
                } else {
                    System.out.println("Sinistro gerado com sucesso");
                }

                menuInicial();
                break;


            case LISTAR:
                menu_atual = Operacoes.LISTAR;
                System.out.println("--------------------------");
                System.out.println("1)Listar Cliente PF/PJ\n2)Listar Sinistros por Seguradora");
                System.out.println("3)Listar Sinistros por Cliente\n4)Listar Veículos por Cliente");
                System.out.println("5)Listar Veículos por Seguradora\n6)Voltar");
                break;


            case LISTAR_CLIENTE:
                
                seguradora_atual = encontraSeguradora();
                System.out.println("Dejesa listar clientes PF (1) ou PJ (2)?");
                opcao = scanner.nextLine();
                if (opcao.equals("1")) {
                    seguradora_atual.listarClientes("ClientePF");
                } else if (opcao.equals("2")) {
                    seguradora_atual.listarClientes("ClientePJ");
                } else {
                    System.out.println("----- Opção inválida -----");
                }
                menuInicial();
                break;


            case LISTAR_SINISTRO_CLIENTE:
                
                seguradora_atual = encontraSeguradora();
                System.out.print("Qual cpf/cnpj do Cliente? ");
                id = scanner.nextLine();
                if (!Validacao.validaNumero(id)) {
                    System.out.println("--- Núero inválido --- ");
                    
                    menuInicial();
                    break;
                }
                if (!seguradora_atual.clienteExiste(id)) {
                    System.out.println("Cliente não encontrado na Seguradora");
                    
                    menuInicial();
                    break;
                }
                index_cliente = seguradora_atual.encontrarCliente(id);
                cliente = seguradora_atual.getListaClientes().get(index_cliente);
                int flag1 = 0;
                for (Sinistro a : seguradora_atual.getListaSinistros()) {
                    if (a.getCliente().idCliente().equals(cliente.idCliente())) {
                        System.out.println(a);
                        flag1 = 1;
                    }
                    if (flag1 == 0) {
                        System.out.println("O cliente não possui nenhum Sinistro nessa Seguradora");
                    }
                }

                menuInicial();
                break;


            case LISTAR_SINISTRO_SEG:
                
                seguradora_atual = encontraSeguradora();
                System.out.println("--Sinistros da Seguradora " + seguradora_atual.getNome() + "--\n");
                for (Sinistro a : seguradora_atual.getListaSinistros()) {
                    System.out.println(a);
                }
                menuInicial();
                break;


            case LISTAR_VEICULO_CLIENTE:
                
                seguradora_atual = encontraSeguradora();
                System.out.print("Qual cpf/cnpj do Cliente? ");
                id = scanner.nextLine();
                if (!Validacao.validaNumero(id)) {
                    System.out.println("--- Núero inválido --- ");
                    
                    menuInicial();
                    break;
                }
                if (!seguradora_atual.clienteExiste(id)) {
                    System.out.println("Cliente não encontrado na Seguradora");
                    
                    menuInicial();
                    break;
                }
                index_cliente = seguradora_atual.encontrarCliente(id);
                cliente = seguradora_atual.getListaClientes().get(index_cliente);
                System.out.println("Veículos do cliente: " + cliente.getNome());
                for (Veiculo a : cliente.getListaVeiculos()) {
                    System.out.println(a);
                }

                menuInicial();
                break;


            case LISTAR_VEICULO_SEGURADORA:
                
                seguradora_atual = encontraSeguradora();
                for (Cliente a : seguradora_atual.getListaClientes()) {
                    System.out.println(a.getListaVeiculos());
                }
                
                menuInicial();
                break;


            case SAIR:
                System.out.println("----- Menu encerrado -----");
                flag = false;
                break;


            case TRANSFERIR_SEGURO:
                
                seguradora_atual = encontraSeguradora();    
                System.out.print("Qual cpf/cnpj do Cliente de origem: ");
                id = scanner.nextLine();
                if (!Validacao.validaNumero(id)) {
                    System.out.println("--- Núero inválido --- ");
                    
                    menuInicial();
                    break;
                }
                if (!seguradora_atual.clienteExiste(id)) {
                    System.out.println("Cliente não encontrado na Seguradora");
                    
                    menuInicial();
                    break;
                }
                index_cliente = seguradora_atual.encontrarCliente(id);
                Cliente cliente_origem = seguradora_atual.getListaClientes().get(index_cliente);
                
                System.out.print("Qual cpf/cnpj do Cliente destinatário: ");
                id = scanner.nextLine();
                if (!Validacao.validaNumero(id)) {
                    System.out.println("--- Núero inválido --- ");
                    
                    menuInicial();
                    break;
                }
                if (!seguradora_atual.clienteExiste(id)) {
                    System.out.println("Cliente não encontrado na Seguradora");
                    
                    menuInicial();
                    break;
                }
                index_cliente = seguradora_atual.encontrarCliente(id);
                Cliente cliente_destino = seguradora_atual.getListaClientes().get(index_cliente);
                for (Veiculo a : cliente_origem.getListaVeiculos()) {
                    cliente_destino.getListaVeiculos().add(a);
                }
                ArrayList<Veiculo> vecs = new ArrayList<Veiculo>();
                cliente_origem.setListaVeiculos(vecs);
                seguradora_atual.calcularPrecoSeguroCliente(cliente_destino);
                seguradora_atual.calcularPrecoSeguroCliente(cliente_origem);
                
                menuInicial();
                break;
                

            default:
                System.out.println("----- Opção inválida -----\nRetornando ao Menu Inicial");
                menuInicial();
                break;
        }
        return;
    }


    public static void MenuInterativo() throws Exception {

        String aux;
        while (true) {
            execOperacao(op_atual);
            if (!flag) return;
            System.out.println("Sua opção:");
            aux = scanner.nextLine();
            id_operacao = Double.parseDouble(aux);
            leOperacao(); // Faz a correção do id_operação dependendo do menu
            op_atual = Operacoes.getOp(id_operacao);
        }
    }


}
