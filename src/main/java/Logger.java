package main.java;


public class Logger {
    //FOR FUTURE LOGGER IMPLEMENTATION
    private Logger() {
    }

    public static void info(String message) {

        System.out.println(message);

    }

    public static void warn(String message) {

        System.out.println(message);

    }

    public static void error(String message) {

        System.err.println(message);

    }

    public static void fatal(String message) {

    }

}
