package br.com.cooperagri.model.enums;

public enum BancoCode {

    BRADESCO(237),
    BRASIL(1),
    CAIXA(104),
    ITAU(341),
    NORDESTE(4),
    SANTANDER(33),
    NUBANK(260),
    OUTROS(0);

    private final int code;

    private BancoCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static BancoCode valueOf(int code) {
        for (BancoCode value : BancoCode.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new  IllegalArgumentException("Codigo invalido");
    }

    public static BancoCode[] getCodes(){
        return BancoCode.values();
    }

}
