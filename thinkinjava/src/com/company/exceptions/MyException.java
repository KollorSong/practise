package com.company.exceptions;


class SimpleException extends Exception{

}

public class MyException {

    public void f() throws SimpleException{
        System.out.println("throw SimpException from f()");
        throw new SimpleException();
    }

    public static void main(String[] args) {
        MyException myException = new MyException();
        try {
            myException.f();
        } catch (SimpleException e) {
            e.printStackTrace();;
        }
    }

}
