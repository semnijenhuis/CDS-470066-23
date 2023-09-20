package modal.menu;

import java.util.Objects;
import java.util.Scanner;

public class Printer {

    public int mainMenu() {
        System.out.println("");
        System.out.println("---- Main menu ----");
        System.out.println("[1] Find route");
        System.out.println("[2] Find Station");
        System.out.println("[3] Show GPS surroundings");
        System.out.println("[4] Data");
        System.out.println("[0] Exit");
        return inputIntScanner(0,4);
    }

    public int routeMenu(String from, String to) {
        System.out.println("");
        System.out.println("---- Route menu ----");
        System.out.println("[1] Location from   : " + from);
        System.out.println("[2] Location to     : " + to);
        System.out.println("[3] Calculate route");
        System.out.println("[0] Return");
        return inputIntScanner(0,3);
    }
    public int stationMenu() {
        System.out.println("");
        System.out.println("---- Station menu ----");
        System.out.println("[1] Search Station");
        System.out.println("[2] Show all Station");
        System.out.println("[0] Return");
        return inputIntScanner(0,2);
    }
    public int dataMenu() {
        System.out.println("");
        System.out.println("---- Data menu ----");
        System.out.println("[1] Display all station");
        System.out.println("[2] Sort the stations");
        System.out.println("[3] Display all tracks");
        System.out.println("[4] Sort the tracks");
        System.out.println("[0] Return");
        return inputIntScanner(0,4);
    }

    public int sortingMenu() {
        System.out.println("");
        System.out.println("---- Sorting menu, sort with? ----");
        System.out.println("[1] Insertion sort");
        System.out.println("[2] Merge sort");
        System.out.println("[3] Quick sort");
        System.out.println("[4] Selection sort");
        System.out.println("[0] Return");
        return inputIntScanner(0,4);
    }

    public int foundStationMenu(String station) {
        System.out.println("");
        System.out.println("----" + station +" station menu ----");
        System.out.println("[1] Show all connections");
        System.out.println("[2] Go from  " +station +" to somewhere");
        System.out.println("[3] Go back to " +station +" from somewhere");
        System.out.println("[4] Return");
        return inputIntScanner(0,4);
    }

    public int GPSMenu(double Latitude, double Longitude) {
        System.out.println("");
        System.out.println("---- GPS surroundings ----");
        System.out.println("[1] Latitude    : " + Latitude);
        System.out.println("[2] Longitude   : " + Longitude);
        System.out.println("[3] Show surroundings");
        System.out.println("[0] Return");
        return inputIntScanner(0,3);
    }

    public int stationOptionsSorting() {
        System.out.println("");
        System.out.println("---- Based on what would you like to sort it ----");
        System.out.println("[1] ID              [7] Slug");
        System.out.println("[2] Code            [8] Country");
        System.out.println("[3] UIC             [9] Type");
        System.out.println("[4] Name short      [10] Latitude");
        System.out.println("[5] Name medium     [11] Longitude");
        System.out.println("[6] Name long       [0] Return");
        return inputIntScanner(0,11);
    }



    public String findStation() {
        System.out.println("");
        System.out.println("---- Find station ----");
        System.out.println("Station name: ");
        return inputStringScanner();
    }

    public double enterGPS() {
        System.out.println("");
        System.out.println("---- GPS info ----");
        System.out.println("*Dont forget to use komma instead of a dot");
        System.out.println("Coordinates: ");

        return inputDoubleScanner();
    }




    int inputIntScanner(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input:");
            if (scanner.hasNextInt()) { // Check if the next input is an integer
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    System.out.println("");
                    return input;
                } else {
                    System.out.println("Invalid input, try again. It has to be between " + min + " - " + max);
                    System.out.println("");
                }
            } else {
                // Consume the invalid input to avoid an infinite loop
                String invalidInput = scanner.next();
                System.out.println("Invalid input, try again. Please enter a valid integer.");
                System.out.println("");
            }
        }
    }

    double inputDoubleScanner() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input:");
            double input = scanner.nextDouble();
            if (input > 0 ) {
                System.out.println("");
                return input;
            }
            else {
                System.out.println("Invalid input, try again. it has to be between ");
                System.out.println("");
            }
        }

    }
    String inputStringScanner(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input:");
            String input = scanner.nextLine();
            if (!Objects.equals(input, "")) {
                System.out.println("");
                return input;
            }
            else {
                System.out.println("Invalid input, try again. it cant be empty");
                System.out.println("");
            }
        }
    }

}
