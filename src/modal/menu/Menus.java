package modal.menu;

import modal.*;
import modal.Objects.Station;
import modal.Objects.Track;
import modal.Searching.Binary;
import modal.Searching.Linear;
import modal.algorithm.AStar;
import modal.algorithm.Dijkstra;
import modal.algorithm.MCST;
import modal.sorting.InsertionSort;
import modal.sorting.Sort;
import modal.tree.AVLTree;

import java.util.ArrayList;

public class Menus {
    Printer printer = new Printer();
    ArrayList<Station> AllStations;
    ArrayList<Track> AllTracks;
    AVLTree AvlTree;

    public void run(ArrayList<Station> stations, ArrayList<Track> tracks, AVLTree avlTree) throws Exception {

        AllStations = stations;
        AllTracks = tracks;
        AvlTree = avlTree;

        boolean running = true;

        System.out.println("Welcome to NS service");

        while (running) {

            int input = printer.mainMenu();

            if (input == 0) {
                running = false;
                System.out.println("\nThanks for coming by, see yu next time");
            } else if (input == 1) {
                routeMenu(null, null);
            } else if (input == 2) {
                stationMenu();
            } else if (input == 3) {
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


    public void routeMenu(Station fStation, Station tStation) {
        Station fromStation = fStation;
        Station toStation = tStation;

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
                AStar aStar = new AStar();


                if (toStation != null && fromStation != null) {

                    int dijkstraChoice = printer.dijkstraMenu();

                    if (dijkstraChoice == 1) {
                        dijkstra.shortestPath(AllStations, fromStation, toStation);
                    } else if (dijkstraChoice == 2) {
                        aStar.shortestPath(AllStations, fromStation, toStation);
                    }


                } else {
                    System.out.println("Please select a station");
                    continue;
                }


            } else if (input == 4) {
                MCST mcst = new MCST();

                if (toStation != null && fromStation != null) {
                    int sortInput = printer.whichSort();
                    mcst.mcstPrimFindStations(AllStations, AllTracks, fromStation, toStation, sortInput);
                } else {
                    System.out.println("Please select a station");
                    continue;
                }
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

                } else if (input == 2) {
                    routeMenu(foundStation, null);
                    break;
                } else if (input == 3) {
                    routeMenu(null, foundStation);
                    break;
                }


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
            int avlSearch = printer.findStationAVL();


            Station searchedStation;

            if (input == 0) {
                running = false;
            }
            if (input == 1) {
                AllStations = insertionSort.stationInsertionSort(AllStations, input);
                int inputStationID = printer.findStationID();

                if (avlSearch == 1) {
                    searchedStation = AvlTree.searchID(inputStationID);
                    return searchedStation;
                } else if (avlSearch == 0) {
                    searchedStation = binarySearch.searchStationIDBin(AllStations, inputStationID);
                    return searchedStation;
                }

            }
            if (input == 2) {
                AllStations = insertionSort.stationInsertionSort(AllStations, input);
                String inputStationString = printer.findStationCode();

                searchedStation = binarySearch.searchStationCodeBin(AllStations, inputStationString);

                if (avlSearch == 1) {
                    searchedStation = AvlTree.searchCode(inputStationString);
                    return searchedStation;
                } else if (avlSearch == 0) {
                    searchedStation = binarySearch.searchStationCodeBin(AllStations, inputStationString);
                    return searchedStation;
                }

            }

            if (input == 3) {
                AllStations = insertionSort.stationInsertionSort(AllStations, input);
                String inputStationString = printer.findStationName();

                if (avlSearch == 1) {
                    searchedStation = AvlTree.searchName(inputStationString);
                    return searchedStation;
                } else if (avlSearch == 0) {
                    searchedStation = binarySearch.searchStationNameBin(AllStations, inputStationString);
                    return searchedStation;
                }
            }


        }

        return null;
    }


}
