package modal.menu;

import modal.Objects.Station;

import java.util.Objects;
import java.util.Scanner;

public class Printer {

    // Prints all the different types of menu with 1 standard input (string or int)

    public int mainMenu() {
        System.out.println("");
        System.out.println("---- Main menu ----");
        System.out.println("[1] Find route");
        System.out.println("[2] Find Station");
        System.out.println("[3] Data");
        System.out.println("[0] Exit");
        return inputIntScanner(0, 3);
    }

    public int routeMenu(String from, String to) {
        System.out.println("");
        System.out.println("---- Route menu ----");
        System.out.println("[1] Location from   : " + from);
        System.out.println("[2] Location to     : " + to);
        System.out.println("[3] Calculate route");
        System.out.println("[4] Display accessible stations");
        System.out.println("[0] Return");
        return inputIntScanner(0, 4);
    }

    public int dijkstraMenu() {
        System.out.println("");
        System.out.println("---- Path calculator menu ----");
        System.out.println("[1] Dijkstra");
        System.out.println("[2] Dijkstra + A*");
        return inputIntScanner(1, 2);
    }


    public int stationMenu() {
        System.out.println("");
        System.out.println("---- Station menu ----");
        System.out.println("[1] Search Station");
        System.out.println("[2] Show all Station");
        System.out.println("[0] Return");
        return inputIntScanner(0, 2);
    }

    public int dataMenu() {
        System.out.println("");
        System.out.println("---- Data menu ----");
        System.out.println("[1] Display all station");
        System.out.println("[2] Sort the stations");
        System.out.println("[3] Display all tracks");
        System.out.println("[0] Return");
        return inputIntScanner(0, 3);
    }

    public int sortingMenu() {
        System.out.println("");
        System.out.println("---- Sorting menu, sort with? ----");
        System.out.println("[1] Insertion sort");
        System.out.println("[2] Merge sort");
        System.out.println("[3] Quick sort");
        System.out.println("[4] Selection sort");
        System.out.println("[0] Return");
        return inputIntScanner(0, 4);
    }

    public int foundStationMenu(Station station) {
        System.out.println("");
        System.out.println("---- " + station.getName_long() + " station menu ----");
        System.out.println("[1] Show all connections");
        System.out.println("[2] Go from  " + station.getName_long() + " to somewhere");
        System.out.println("[3] Go back to " + station.getName_long() + " from somewhere");
        System.out.println("[0] Return");
        return inputIntScanner(0, 3);
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
        return inputIntScanner(0, 11);
    }

    public int findStationID() {
        System.out.println("");
        System.out.println("---- Find station ----");
        System.out.println("ID: ");
        return inputIntScanner(0, 1000);
    }

    public String findStationName() {
        System.out.println("");
        System.out.println("---- Find station ----");
        System.out.println("Station name: ");
        return inputStringScanner();
    }

    public String findStationCode() {
        System.out.println("");
        System.out.println("---- Find station ----");
        System.out.println("Code: ");
        return inputStringScanner();
    }

    public int findStationOn() {
        System.out.println("");
        System.out.println("---- Search based on ----");
        System.out.println("[1] ID");
        System.out.println("[2] CODE");
        System.out.println("[3] Name (long)");
        System.out.println("[0] Return");
        return inputIntScanner(0, 3);
    }

    public int findStationAVL() {
        System.out.println("");
        System.out.println("---- Use AVl Tree? ----");
        System.out.println("[1] Yes");
        System.out.println("[0] No");
        return inputIntScanner(0, 1);
    }

    public int searchOptions() {
        System.out.println("");
        System.out.println("---- searching Options ----");
        System.out.println("[1] Linear search");
        System.out.println("[2] Binary search");
        System.out.println("[0] Return");
        return inputIntScanner(0, 2);
    }

    public int whichSort() {
        System.out.println("");
        System.out.println("---- Search options ----");
        System.out.println("[1] Insertion sort");
        System.out.println("[2] Merge sort");
        return inputIntScanner(1, 2);
    }


    // Creates standard menu with minimum and maximum input
    public int inputIntScanner(int minNumb, int maxNumb) {

        int min = minNumb;
        int max = maxNumb;
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
                System.out.println("Invalid input, try again. Please enter a valid integer.");
                String invalidInput = scanner.next();

                System.out.println("");
            }
        }
    }


    // Creates standard menu with string requirements
    public String inputStringScanner() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input:");
            String input = scanner.nextLine();
            if (!Objects.equals(input, "")) {
                System.out.println("");
                return input;
            } else {
                System.out.println("Invalid input, try again. it cant be empty");
                System.out.println("");
            }
        }
    }



}
