package com.santander.eventos.translator.Transactions;

public enum Mp7e{



    private final String value;
    Mp7e(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}