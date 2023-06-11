import java.util.*;
import java.text.SimpleDateFormat;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static Operacoes op_atual = Operacoes.MENU_INICIAL; // Indicador de operação atual
    private static Operacoes menu_atual = Operacoes.MENU_INICIAL;  // Indica o menu onde o programa está
    private static boolean flag = true; // flag para parada de execução
    private static double id_operacao = 0;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
    private static String menu_inicial = "------ Menu Inicial ----- \n1)Cadastros\n2)Listar\n3)Excluir\n4)Gerar Sinistro\n5)Atualizar Frota\n6)Calcular Receita\n0)Sair\n--------------------------\n";

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
        System.out.println("Qual a seguradora? ");
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
                Cliente c = seguradora_atual.getListaClientes().get(index_cliente);
                if (c.getClass().getCanonicalName().equals("ClientePF")) {
                    ClientePF cliente = (ClientePF) c;
                    cliente.cadastrarVeiculo(veiculo);
                } else if (c.getClass().getCanonicalName().equals("ClientePJ")) {
                    ClientePJ clientePJ = (ClientePJ) c;
                    clientePJ.adicinarVeiculoFrota(veiculo);
                }
                menuInicial();
                break;


            case CADASTRAR_CLIENTE:
                
                seguradora_atual = encontraSeguradora();
                System.out.println("Criar um cliente PF (1) ou PJ (2)?");
                String opcao = scanner.nextLine();
                System.out.println("Nome: ");
                String nome = scanner.nextLine();
                System.out.println("Endereco: ");
                String endereco = scanner.nextLine();
                System.out.println("Email: ");
                String email = scanner.nextLine();
                System.out.println("Telefone: ");
                String telefone = scanner.nextLine();
                if (opcao.equals("1")) {
                    System.out.println("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.println("Genero: ");
                    String genero = scanner.nextLine();
                    System.out.println("Educacao: ");
                    String educacao = scanner.nextLine();
                    System.out.println("Data de nascimento: ");
                    Date data = sdf.parse(scanner.nextLine());
                    ClientePF cliente = new ClientePF(nome, endereco, telefone, email, genero, educacao, data, cpf);
                    seguradora_atual.cadastrarClientes(cliente);
                } else if (opcao.equals("2")) {
                    System.out.println("CNPJ: ");
                    String cnpj = scanner.nextLine();
                    System.out.println("Data de fundação: ");
                    Date dataFund = sdf.parse(scanner.nextLine());
                    System.out.println("Quantidade de funcionários: ");
                    String funcionarios = scanner.nextLine();
                    int qntFunc = Integer.parseInt(funcionarios);
                    ClientePJ clientePJ = new ClientePJ(nome, endereco, telefone, email, cnpj, dataFund, qntFunc);
                    seguradora_atual.cadastrarClientes(clientePJ);
                } else {
                    System.out.println("----- Opção inválida --- cliente não registrado");
                    menuInicial();
                }
                menuInicial();
                break;


            case CADASTRAR_SEGURADORA:
                System.out.println("Nome da seguradora: ");
                nome = scanner.nextLine();
                System.out.println("CNPJ: ");
                String cnpj = scanner.nextLine();
                System.out.println("Telefone: ");
                telefone = scanner.nextLine();
                System.out.println("Email: ");
                email = scanner.nextLine();
                System.out.println("Endereco: ");
                endereco = scanner.nextLine();
                Seguradora seguradora = new Seguradora(cnpj, nome, telefone, email, endereco);
                Seguradora.getListaSeguradoras().add(seguradora);
                menuInicial();
                break;

            case CADASTRAR_FROTA:
                seguradora_atual = encontraSeguradora();
                System.out.println("Qual cliente deseja adicionar a frota?");
                if (!seguradora_atual.listarClientes("ClientePJ")) {
                    menuInicial();
                    break;
                }
                int pos = Integer.parseInt(scanner.nextLine());
                ClientePJ clientePJ = (ClientePJ) seguradora_atual.getListaClientes().get(pos);
                ArrayList<Veiculo> nova = new ArrayList<Veiculo>();
                Frota frota = new Frota(nova);
                clientePJ.cadastrarFrota(frota);
                System.out.println("Frota código " + frota.getCode() + " adicionada com sucesso");
                menuInicial();
                break;

            case AUTORIZAR_CONDUTOR:
                seguradora_atual = encontraSeguradora();
                System.out.println("Cpf/Cnpj do dono do seguro: ");
                id = scanner.nextLine();
                if (!seguradora_atual.clienteExiste(id)) {
                    System.out.println("Cliente não encontrado");
                    menuInicial();
                    break;
                }
                ArrayList<Seguro> lista_seguros = seguradora_atual.getSegurosPorCliente(id);
                System.out.println("Qual seguro deseja atualizar?");
                int i = 0;
                for (Seguro s:lista_seguros) {
                    System.out.println(i + ")" + s.getId() + " - Veículos registrados :" + s.imprimeVeiculos());
                    i++;
                }
                int index = Integer.parseInt(scanner.nextLine());
                Seguro temp = lista_seguros.get(index);
                temp.autoriazarCondutor();

                menuInicial();
                break;

            case CADASTROS:
                menu_atual = Operacoes.CADASTROS;
                System.out.println("--------------------------");
                System.out.println("1)Cadastrar Cliente PF/PJ\n2)Cadastrar Veículo");
                System.out.println("3)Cadastrar Seguradora\n4)Cadastrar Frota");
                System.out.println("5)Autorizar Condutor\n6)Gerar Seguro\n7)Voltar");
                break;

            case GERAR_SEGURO:
                seguradora_atual = encontraSeguradora();
                if (!seguradora_atual.gerarSeguro()) {
                    System.out.println("Seguro não gerado");
                    menuInicial();
                break;
                }
                System.out.println("Seguro gerado com sucesso");
                menuInicial();
                break;

            case CALCULAR:
                menu_atual = Operacoes.CALCULAR;
                System.out.println("--------------------------");
                System.out.println("1)Calcular Receita de Seguradora\n2)Calcular Seguro do Cliente");
                break;

            case CALCULAR_RECEITA:
                
                seguradora_atual = encontraSeguradora();
                System.out.println("A receita da Seguradora " + seguradora_atual.getNome() + ": R$" + seguradora_atual.calcularReceita());
                
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
                lista_seguros = seguradora_atual.getSegurosPorCliente(id);
                double preço = 0.0;
                for (Seguro s:lista_seguros) {
                    s.calcularValor();
                    preço += s.getValorMensal();
                }
                System.out.println("O valor mensal do cliente " + cliente.getNome() + " é: R$" + preço);
                menuInicial();
                break;


            case EXCLUIR:
                menu_atual = Operacoes.EXCLUIR;
                System.out.println("--------------------------");
                System.out.println("1)Excluir Cliente\n2)Excluir Seguro");
                System.out.println("3)Excluir Veículo\n4)Excluir Sinistro");
                System.out.println("5)Desautorizar condutor \n6)Voltar");
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
                lista_seguros = seguradora_atual.getListaSeguros();
                for (Seguro s:lista_seguros) {
                    if (s.idCliente().equals(id)) {
                        lista_seguros.remove(s);
                    }
                }
            seguradora_atual.getListaClientes().remove(cliente);
            menuInicial();
            break;

            case EXCLUIR_SEGURO:
                seguradora_atual = encontraSeguradora();
                System.out.println("Qual seguro deseja excluir?");
                seguradora_atual.imprimirSeguros();
                int op = Integer.parseInt(scanner.nextLine());
                seguradora_atual.getListaSeguros().remove(op);
                System.out.println("Seguro removido com sucesso");
                menuInicial();
                break;

            case DESAUTORIZAR_CONDUTOR:
                seguradora_atual = encontraSeguradora();
                System.out.println("O seguro de qual cliente deseja alterar?");
                System.out.println("CPF/CNPJ do cliente: ");
                id = scanner.nextLine();
                System.out.println("De qual seguro deseja desautorizar? ");
                lista_seguros = seguradora_atual.getSegurosPorCliente(id);
                i = 0;
                for (Seguro s:lista_seguros) {
                    System.out.println(i + ")" + s);
                    i++;
                }
                op = Integer.parseInt(scanner.nextLine());
                Seguro seguro = lista_seguros.get(op);
                System.out.println("Qual seguro deseja desautorizar? ");
                seguro.imprimeCondutores();
                op = Integer.parseInt(scanner.nextLine());
                seguro.getListaCondutores().remove(op);
                System.out.println("Condutor removido com sucesso");

                menuInicial();
                break;
            
            case EXCLUIR_SINISTRO:
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
                pos = seguradora_atual.encontrarCliente(id);
                c = seguradora_atual.getListaClientes().get(pos);
                System.out.println("Qual sinistro deseja remover?");
                ArrayList<Sinistro> lista_sinistros = seguradora_atual.getSinistroPorCliente(id);
                i = 0;
                for (Sinistro s:lista_sinistros) {
                    System.out.println(i + ")" + s);
                    i++;
                }
                pos = Integer.parseInt(scanner.nextLine());
                Sinistro sinistro = lista_sinistros.get(pos);
                sinistro.getSeguro().getListaSinistros().remove(sinistro);

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
                if (cliente.getClass().getCanonicalName().equals("ClientePF")) {
                    System.out.println("Escolha o veículo a ser removido:");
                    ClientePF clientePF = (ClientePF) cliente;
                    i = 0;
                    for (Veiculo v:clientePF.getListaVeiculos()) {
                        System.out.println(i + ")" + v);
                    }
                    pos = Integer.parseInt(scanner.nextLine());
                    veiculo = clientePF.getListaVeiculos().get(pos);
                    clientePF.getListaVeiculos().remove(veiculo);

                } else if (cliente.getClass().getCanonicalName().equals("ClientePJ")) {
                    clientePJ = (ClientePJ) cliente;
                    System.out.println("De qual frota deseja remover o veículo?");
                    i = 0;
                    for (Frota f:clientePJ.getListaFrota()) {
                        System.out.println(i + ")" + f);
                    }
                    pos = Integer.parseInt(scanner.nextLine());
                    Frota f = clientePJ.getListaFrota().get(pos);
                    System.out.println("Qual carro deseja remover");
                    f.listaVeiculos();
                    pos = Integer.parseInt(scanner.nextLine());
                    f.getListaVeiculos().remove(pos);
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
                System.out.println("Qual o seguro relacionado ao sinistro?");
                lista_seguros = seguradora_atual.getSegurosPorCliente(id);
                if (lista_seguros.size() == 0) {
                    System.out.println("Não exitem seguros registrados no nome do cliente");
                    menuInicial();
                    break;
                }
                System.out.println(lista_seguros);
                pos = Integer.parseInt(scanner.nextLine());
                seguro = seguradora_atual.getListaSeguros().get(pos);
                seguro.gerarSinistro();

                menuInicial();
                break;


            case LISTAR:
                menu_atual = Operacoes.LISTAR;
                System.out.println("--------------------------");
                System.out.println("1)Listar Clientes\n2)Listar Seguros");
                System.out.println("3)Listar Sinistros por Cliente\n4)Listar Veículos por Cliente");
                System.out.println("5)Voltar");
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

            case LISTAR_SEGUROS:
                seguradora_atual = encontraSeguradora();
                System.out.println("Cpf/Cnpj do cliente a ser consultado: ");
                id = scanner.nextLine();
                lista_seguros = seguradora_atual.getSegurosPorCliente(id);
                i = 0;
                for (Seguro s:lista_seguros) {
                    System.out.println(i + ")" + s.getId() + " - Veículos registrados :" + s.imprimeVeiculos());
                    i++;
                }
                menuInicial();
                break;

            case LISTAR_SINISTROS_CLIENTE:
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
                System.out.println(seguradora_atual.getSinistroPorCliente(id));
            
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
                pos = seguradora_atual.encontrarCliente(id);
                c = seguradora_atual.getListaClientes().get(pos);
                if (c.getClass().getCanonicalName().equals("ClientePF")) {
                    ClientePF clientePF = (ClientePF) c;
                    System.out.println(clientePF.getListaVeiculos());
                } else if (c.getClass().getCanonicalName().equals("ClientePJ")) {
                    clientePJ = (ClientePJ) c;
                    System.out.println(clientePJ.getListaFrota());
                }

                menuInicial();
                break;

            case ATUALIZAR_FROTA:
                seguradora_atual = encontraSeguradora();
                System.out.print("Qual cnpj do Cliente? ");
                id = scanner.nextLine();
                if (!Validacao.validaCnpj(id)) {
                    System.out.println("--- Núero inválido --- ");
                    
                    menuInicial();
                    break;
                }
                if (!seguradora_atual.clienteExiste(id)) {
                    System.out.println("Cliente não encontrado na Seguradora");
                    
                    menuInicial();
                    break;
                }
                pos = seguradora_atual.encontrarCliente(id);
                c = seguradora_atual.getListaClientes().get(pos);
                clientePJ = (ClientePJ) c;
                clientePJ.atualizarFrota();

                menuInicial();
                break;

            case SAIR:
                System.out.println("----- Menu encerrado -----");
                flag = false;
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
