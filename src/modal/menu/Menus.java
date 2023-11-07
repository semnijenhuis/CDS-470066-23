package modal.menu;

import modal.*;
import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Binary;
import modal.Searching.Linear;
import modal.algorithm.Dijkstra;
import modal.sorting.InsertionSort;
import modal.sorting.Sort;

import java.util.ArrayList;

public class Menus {
    Printer printer = new Printer();
    Calculator calculator = new Calculator();
    ArrayList<Station> AllStations;
    ArrayList<Track> AllTracks;
    boolean sorted = false;

    public void run(ArrayList<Station> stations, ArrayList<Track> tracks) throws Exception {

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
            } else if (input == 2) {
                stationMenu();
            } else if (input == 3) {
                GPSMenu();
            } else if (input == 4) {
                dataMenu();
            }

        }

    }


    public void stationMenu() throws Exception {

        boolean running = true;
        while (running) {
            int input = printer.stationMenu();
            if (input == 0) {
                running = false;
            } else if (input == 1) {
                foundStationMenu(searchStation());
//                searchStation();

            } else if (input == 2) {
                System.out.println(AllStations);
            }
        }
    }


    public void routeMenu() {
        Station fromStation = null;
        Station toStation = null;

        Boolean running = true;


        while (running) {

            Station inputStation = null;

            String from = "";
            String to = "";


            if (fromStation != null) {
                from = fromStation.getName_long();
            }
            if (toStation != null) {
                to = toStation.getName_long();
            }


            int input = printer.routeMenu(from, to);

            if (input == 0) {
                running = false;
            } else if (input == 1) {
                inputStation = findLinearStation();
                fromStation = inputStation;

            } else if (input == 2) {
                inputStation = findLinearStation();
                toStation = inputStation;

            } else if (input == 3) {
                Dijkstra dijkstra = new Dijkstra();


                if (toStation != null && fromStation != null) {
                    dijkstra.shortestPath(AllStations, fromStation, toStation);
                } else {
                    System.out.println("Please select a station");
                    continue;
                }


//                calculator.calculateRoute(fromStation, toStation);
            }

        }
    }

    public void foundStationMenu(Station foundStation) {

        boolean running = true;
        if (foundStation != null) {
            while (running) {
                int input = printer.foundStationMenu(foundStation);
                if (input == 0) {
                    running = false;
                } else if (input == 1) {

                    foundStation.printDepartureTracks();

                }


            }
        }


    }

    public void GPSMenu() {
        double Latitude = 0.0;
        double Longitude = 0.0;
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

    public void dataMenu() {
        Sort sort = new Sort();
        boolean running = true;
        int sortingOption;
        while (running) {
            int input = printer.dataMenu();
            if (input == 0) {
                running = false;
            } else if (input == 1) {
                System.out.println(AllStations);
            } else if (input == 2) {

                sort.stationSorting(AllStations, printer.stationOptionsSorting(), printer.sortingMenu());

            } else if (input == 3) {

                System.out.println(AllTracks);
//                sortingOption = printer.sortingMenu();
//                System.out.println("needs storting completion");

            } else if (input == 4) {
                // start sorting
                System.out.println("needs storting menu");
            }
        }

    }

    public Station searchStation() throws Exception {

        Station searchedStation;
        boolean running = true;

        while (running) {
            int input = printer.searchOptions();

            if (input == 0) {
                running = false;
            }
            if (input == 1) {
                searchedStation = findLinearStation();
                return searchedStation;
            }
            if (input == 2) {
                searchedStation = findBinaryStation();
                return searchedStation;
            }

        }


        return null;
    }

    public Station findLinearStation() {

        Linear linearSearch = new Linear();

        boolean running = true;

        while (running) {

            int input = printer.findStationOn();
            Station searchedStation;

            if (input == 0) {
                running = false;
            }
            if (input == 1) {
                int inputStationID = printer.findStationID();
                searchedStation = linearSearch.searchStationID(AllStations, inputStationID);
                return searchedStation;
            }
            if (input == 2) {
                String inputStationString = printer.findStationCode();
                searchedStation = linearSearch.searchStationCode(AllStations, inputStationString);
                return searchedStation;
            }

            if (input == 3) {
                String inputStationString = printer.findStationCode();
                searchedStation = linearSearch.searchStationString(AllStations, inputStationString);
                return searchedStation;
            }


        }


        return null;
    }

    public Station findBinaryStation() {

        InsertionSort insertionSort = new InsertionSort();
        Binary binarySearch = new Binary();

        boolean running = true;

        while (running) {

            int input = printer.findStationOn();
            Station searchedStation;

            if (input == 0) {
                running = false;
            }
            if (input == 1) {
                AllStations = insertionSort.stationInsertionSort(AllStations, input);
                int inputStationID = printer.findStationID();
                searchedStation = binarySearch.searchStationIDBin(AllStations, inputStationID);
                return searchedStation;
            }
            if (input == 2) {
                AllStations = insertionSort.stationInsertionSort(AllStations, input);
                String inputStationString = printer.findStationCode();
                searchedStation = binarySearch.searchStationCodeBin(AllStations, inputStationString);
                return searchedStation;
            }

            if (input == 3) {
                AllStations = insertionSort.stationInsertionSort(AllStations, input);
                String inputStationString = printer.findStationName();
                searchedStation = binarySearch.searchStationNameBin(AllStations, inputStationString);
                return searchedStation;
            }


        }

        return null;
    }


}
