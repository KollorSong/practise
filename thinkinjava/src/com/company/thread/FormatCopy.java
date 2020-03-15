package com.company.thread;

public class FormatCopy {


    public static void main(String[] args) {
        byte[] aa = new byte[20];
        printHexString("1521Abc45",aa);
    }

    public static void printHexString(String hint, byte[] b) {
        System.out.println(hint);
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase() + " ");
        }
        System.out.println("");
    }




}



