import modal.utils.ReadFile;
import modal.Searching.Linear;
import modal.Objects.Station;
import modal.Objects.Track;
import modal.algorithm.AStar;
import modal.algorithm.Dijkstra;
import modal.algorithm.MCST;
import modal.menu.Menus;
import modal.tree.AVLTree;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        ReadFile readFile = new ReadFile();
        AVLTree avlTree = new AVLTree();

        ArrayList<Station> stations = readFile.readStationFile("data/stations.csv");
        ArrayList<Track> tracks = readFile.readTracksFile("data/tracks.csv");
        readFile.addTracksToStations(stations, tracks);
        avlTree = readFile.makeAVLTree(stations, avlTree);

        Menus menu = new Menus();
        menu.run(stations, tracks, avlTree);
    }

}

