package com.ale.util.freemarker.bean;

public enum MVCEnum {
    ENTITY("entity"), CONTROLLER("controller"),
    SERVICE("service"), SERVICE_IMPL("service/impl"), DAO("dao"),
    MAPPER("mapper"), CONSTANTS("constants");

    private final String name;

    MVCEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toUpperFirstChar(){
        char[] charArray = name.toCharArray();
        charArray[0] -= 32;
        return String.valueOf(charArray);
    }

    public static void main(String[] args) {
        for (MVCEnum myTemplate: MVCEnum.values()) {
            System.out.println(myTemplate.getName());
        }
        System.out.println(MVCEnum.ENTITY.toUpperFirstChar());
    }




}
