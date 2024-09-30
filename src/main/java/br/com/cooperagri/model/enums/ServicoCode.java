package br.com.cooperagri.model.enums;

public enum ServicoCode {

    FRETE(1),
    DIARIA(2),
    DIARIA_HORA(3),
    TRASPORTE(4);

    private int code;

    private ServicoCode(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static ServicoCode valueOf(int code){
        for(ServicoCode value : ServicoCode.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new  IllegalArgumentException("Codigo invalido");
    }

    public static ServicoCode[] getCodes(){
        return ServicoCode.values();
    }
}
