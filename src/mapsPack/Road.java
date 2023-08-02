package mapsPack;

public class Road {
    private final int distance;
    private final int speedLimit;
    private boolean isClosed;
    private boolean inMaintenance;

    public Road(int distance, int speedLimit, boolean isClosed, boolean inMaintenance) {
        this.speedLimit = speedLimit;
        this.isClosed = isClosed;
        this.inMaintenance = inMaintenance;
        this.distance = distance;
    }

    public int getTime(){
        int minutes = (distance/speedLimit) * 60 + ((distance%speedLimit * 60) / speedLimit);
        if (inMaintenance){
            return (minutes*2);
        }
        return minutes;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Road{" +
                "distance=" + distance + "km" +
                ", speedLimit=" + speedLimit + "km/h" +
                ", isClosed=" + isClosed +
                ", inMaintenance=" + inMaintenance +
                '}';
    }
}
