package modal.menu;

import modal.Calculator;
import modal.Station;
import modal.Track;

import java.util.ArrayList;

public class Menus {
    Printer printer = new Printer();
    Calculator calculator = new Calculator();
    ArrayList<Station> AllStations;
    ArrayList<Track> AllTracks;
    public void run(ArrayList<Station> stations, ArrayList<Track> tracks) {

        AllStations = stations;
        AllTracks = tracks;
        boolean running = true;

        System.out.println("Welcome to NS service");

        while (running) {

            int input = printer.mainMenu();

            if (input == 0) {
                running = false;
                System.out.println("\nThanks for coming by, see yu next time");
            } else if (input == 1) {
                routeMenu();
            }
            else if (input == 2) {
               stationMenu();
            }
            else if (input == 3) {
                GPSMenu();
            }

        }
    }

    public void stationMenu(){
        boolean running = true;
        while (running) {
            int input = printer.stationMenu();
            if (input == 0) {
                running = false;
            }
            else if (input == 1) {
                String station = printer.findStation();
                printer.foundStationMenu(station);
            }
            else if (input == 2) {
                System.out.println(AllStations);
            }
        }
    }

    public void routeMenu(){
        String from = "";
        String to = "";
        Boolean running = true;


        while (running) {
            int input = printer.routeMenu(from, to);

            if (input == 0) {
                running = false;
            } else if (input == 1) {
                from = printer.findStation();
            } else if (input == 2) {
                to = printer.findStation();
            } else if (input == 3) {
                calculator.calculateRoute(from, to);
            }

        }
    }

    public void GPSMenu () {
        double Latitude = 12.1;
        double Longitude = 12.3;
        boolean running = true;

        while (running) {
            int input = printer.GPSMenu(Latitude, Longitude);

            if (input == 0) {
                running = false;
            } else if (input == 1) {
                Latitude = printer.enterGPS();
            } else if (input == 2) {
                Longitude = printer.enterGPS();
            } else if (input == 3) {
                calculator.calculateSurroundings(Latitude, Longitude);
            }

        }

    }

}
