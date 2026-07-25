class UndergroundSystem {
    class CheckIn {
        String station;
        int time;

        CheckIn(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    class Trip {
        int totalTime;
        int count;

        Trip(int totalTime, int count) {
            this.totalTime = totalTime;
            this.count = count;
        }
    }

    HashMap<Integer, CheckIn> checkInMap;
    HashMap<String, Trip> travelMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        travelMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn check = checkInMap.get(id);

        String route = check.station + "->" + stationName;
        int time = t - check.time;

        Trip trip = travelMap.getOrDefault(route, new Trip(0, 0));
        trip.totalTime += time;
        trip.count++;

        travelMap.put(route, trip);

        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        Trip trip = travelMap.get(startStation + "->" + endStation);
        return (double) trip.totalTime / trip.count;
    }
}
