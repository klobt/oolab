package agh.ics.oop;

public class World {
    public static void main(String[] arguments) {
        System.out.println("system wystartował");
        run(arguments);
        System.out.println("system zakończył działanie");
    }
    private static void run(String[] arguments) {
        System.out.println("Start");
        for (String argument : arguments) {
            switch (argument) {
                case "f":
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case "b":
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case "r":
                    System.out.println("Zwierzak skręca w prawo");
                    break;
                case "l":
                    System.out.println("Zwierzak skręca w lewo");
                    break;
                default:
                    // ignoruj
            }
        }
        System.out.println("Stop");
    }
}
