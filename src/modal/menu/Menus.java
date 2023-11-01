package modal.menu;

import modal.*;
import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Binary;
import modal.Searching.Linear;
import modal.algorithm.Dijkstra;
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
                Station station = searchStation();

                if (station != null) {
                   foundStationMenu(station);
                }

            } else if (input == 2) {
                System.out.println(AllStations);
            }
        }
    }



    public void routeMenu() {
        Station fromStation = null;
        Station toStation =   null;

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
                inputStation = findRouteMenu();
                fromStation = inputStation;

            } else if (input == 2) {
                inputStation = findRouteMenu();
                toStation = inputStation;

            } else if (input == 3) {
                Dijkstra dijkstra = new Dijkstra();


                if (toStation != null &&  fromStation != null) {
                    dijkstra.shortestPath(AllStations, fromStation, toStation);
                }
                else {
                    System.out.println("Please select a station");
                    continue;
                }



//                calculator.calculateRoute(fromStation, toStation);
            }

        }
    }

    public void foundStationMenu(Station foundStation){
        boolean running = true;
        while (running) {
            int input = printer.foundStationMenu(foundStation);
            if (input == 0) {
                running = false;
            } else if (input == 1) {

                foundStation.printDepartureTracks();

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


        int stationID = printer.findStationBasedOnID();
        Station foundStation;

        boolean running = true;
        while (running) {
            int searchType = printer.searchingTypes();

            if (searchType == 1) {
                Binary binarySearch = new Binary();


                try {
                    foundStation = binarySearch.searchStaton(AllStations, stationID,sorted);
                    return foundStation;
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    return null;
                }




            } else if (searchType == 2) {
                Linear linearSearch = new Linear();
                foundStation = linearSearch.searchStationID(AllStations, stationID);
                return foundStation;
            } else if (searchType == 0) {
                running = false;
            }

        }


        return null;
    }

    public Station findRouteMenu(){

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


}
