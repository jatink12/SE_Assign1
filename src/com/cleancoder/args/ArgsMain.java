package com.cleancoder.args;

public class ArgsMain {
    public static void main(String[] args) {
        try {
            ArgumentParser arg = new ArgumentParser("l,p#,d*", args);
            
            
            boolean logging = arg.getBoolean('l');
            int port = arg.getInt('p');
            String directory = arg.getString('d');
            executeApplication(logging, port, directory);
             
        } catch (ArgsException e) {
            System.out.printf("Argument error: %s\n", e.errorMessage());
        }
    }

    private static void executeApplication(boolean logging, int port, String directory) {
        System.out.printf("logging is %s, port:%d, directory:%s\n", logging, port, directory);
    }
}