package com.company.interfacel;

public class Filter {
    public String name(){
        return getClass().getSimpleName();
    }

    public static void main(String[] args) {
        new Filter().name();
    }

}
