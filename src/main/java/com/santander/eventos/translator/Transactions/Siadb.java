package com.santander.eventos.translator.Transactions;

public enum Siadb {
    

    private final String value;
    Siadb(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }


}
