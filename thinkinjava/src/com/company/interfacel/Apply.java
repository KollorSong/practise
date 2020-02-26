package com.company.interfacel;


import java.util.Arrays;

class Processor{
    public String name(){
        return getClass().getSimpleName();
    }

    Object process(Object input){
        return input;
    }

}

class Upcase extends Processor{
    String process(Object input){
        return ((String) input).toUpperCase();
    }
}

class Downcase extends Processor{
    String process(Object input){
        return ((String) input).toLowerCase();
    }
}


class Splitter extends Processor{
    String process(Object input){
        return Arrays.toString(((String) input).split(" "));
    }
}






public class Apply {

    public static void process(Processor processor,Object object){
        System.out.println("Use Process Name "+ processor.name());
        System.out.println("Use Process Name "+ processor.getClass());
        System.out.println(processor.process(object));
    }


    public static void main(String[] args) {

        String string = "Disageement with belifs is by definition incor";

        process(new Downcase(),string);

        process(new Downcase(),string);

        process(new Splitter(),string);


    }
}
