
// ------------- Pronto! --------------- // 

public enum CalcSeguro {
    VALOR_BASE (10),
    FATOR_18_30 (1.25),
    FATOR_30_60 (1.0),
    FATOR_60_90 (1.5);

    private final double fator;

    CalcSeguro(double fator) {
        this.fator = fator;
    }

    public double getFator() {
        return fator;
    }

}
