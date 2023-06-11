
// ------------- Pronto! --------------- // 

public enum Operacoes {
    CADASTROS(1),
    CADASTRAR_CLIENTE(1.1),
    CADASTAR_VEICULO(1.2),
    CADASTRAR_SEGURADORA(1.3),
    CADASTRAR_FROTA(1.4),
    AUTORIZAR_CONDUTOR(1.5),
    GERAR_SEGURO(1.6),
    LISTAR(2),
    LISTAR_CLIENTE(2.1),
    LISTAR_SEGUROS(2.2),
    LISTAR_SINISTROS_CLIENTE(2.3),
    LISTAR_VEICULO_CLIENTE(2.4),
    EXCLUIR(3),
    EXCLUIR_CLIENTE(3.1),
    EXCLUIR_SEGURO(3.2),
    EXCLUIR_VEICULO(3.3),
    EXCLUIR_SINISTRO(3.4),
    DESAUTORIZAR_CONDUTOR(3.5),
    GERAR_SINISTRO(4),
    ATUALIZAR_FROTA(5),
    CALCULAR(6),
    CALCULAR_RECEITA(6.1),
    CALCULAR_SEGURO_CLIENTE(6.2),
    MENU_INICIAL(9),
    SAIR(0);

    private final double operacoes;

    private Operacoes(double operacoes) {
        this.operacoes = operacoes;
    }

    public double getOperacoes() {
        return this.operacoes;
    }

    public static Operacoes getOp(double operacao) {
        for (Operacoes op : Operacoes.values()) {
            if (op.getOperacoes() == operacao) {
                return op;
            }
        }
        return Operacoes.MENU_INICIAL;
    }
}
