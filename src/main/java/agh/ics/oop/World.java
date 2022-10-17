package agh.ics.oop;

public class World {
    public static void main(String[] arguments) {
        System.out.println("system wystartował");
        run(arguments);
        System.out.println("system zakończył działanie");
    }
    private static void run(String[] arguments) {
        System.out.println("zwierzak idzie do przodu");
        boolean first = true;
        for (String argument : arguments) {
            if (first) {
                first = false;
            } else {
                System.out.print(", ");
            }
            System.out.print(argument);
        }
        System.out.println();
    }
}
