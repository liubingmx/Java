package com.bing.dp.strategy;

public enum HandlerTypeEnum {

    ADD(1, "add"),
    SUB(2, "sub"),
    MULTIPLY(3, "multiply");

    private int type;
    private String des;

    HandlerTypeEnum(int type) {
        this.type = type;
    }

    HandlerTypeEnum(int type, String des) {
        this.type = type;
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
