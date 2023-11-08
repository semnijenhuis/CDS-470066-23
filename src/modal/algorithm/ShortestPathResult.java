package modal.algorithm;

import java.util.List;

public class ShortestPathResult {
    List<String> path;
    int totalDistance;

    public ShortestPathResult(List<String> path, int totalDistance) {
        this.path = path;
        this.totalDistance = totalDistance;
    }

    public List<String> getPath() {
        return path;
    }

    public int getTotalDistance() {
        return totalDistance;
    }
}
