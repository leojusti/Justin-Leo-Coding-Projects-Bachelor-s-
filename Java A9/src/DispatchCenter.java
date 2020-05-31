
import java.util.*;

public class DispatchCenter {

    public static String[] AREA_NAMES = {"Downtown", "Airport", "North", "South", "East", "West"};

    private int[][] stats; // You'll need this for the last part of the assignment

    //Taxi Plater Number and the taxi object itself
    Map<Integer, Taxi> taxis;
    Map<String, ArrayList<Taxi>> areas;

    // Constructor
    public DispatchCenter() {
        // You'll need this for the last part of the assignment
        stats = new int[AREA_NAMES.length][AREA_NAMES.length];

        //initializes the taxis HashMap with 50 taxis that have random plate numbers ranging from 100 to 999.
        int randNumb = randomWithinRange(100, 950);
        Taxi newTaxi;
        taxis = new HashMap<Integer, Taxi>();
        for (int i = 0; i < 50; i++) {
            newTaxi = new Taxi(randNumb + i);
            taxis.put(newTaxi.getPlateNumber(), newTaxi);
        }

        //the areas HashMap should be initialized with the 6 fixed areas mentioned earlier.
        //Each taxi should be randomly assigned to one of the 6 areas upon initialization.
        areas = new HashMap<String, ArrayList<Taxi>>();

        for (Taxi taxi : taxis.values()) {
            int randIndex = randomWithinRange(0, 5);
            String randomArea = AREA_NAMES[randIndex];
            this.addTaxi(taxi, randomArea);
        }

    }

    // You'll need this for the last part of the assignment
    public int[][] getStats() {
        return stats;
    }

    // Update the statistics for a taxi going from the pickup location to the dropoff location
    public void updateStats(String pickup, String dropOff) {
        stats[getIndex(pickup)][getIndex(dropOff)] = stats[getIndex(pickup)][getIndex(dropOff)] + 1;
    }

    // Determine the travel times from one area to another
    public static int computeTravelTimeFrom(String pickup, String dropOff) {
        //TODO: Complete this before testing

//                    "Downtown", "Airport", "North", "South", "East", "West"
//        "Downtown",   10            40        20      20        20    20
//        "Airport",    40
//        "North",
//        "South",
//        "East",
//        "West"

        int travelTimes[][] =   {   {10, 40, 20, 20, 20, 20},
                {40, 10, 40, 40, 20, 60},
                {72, 66, 77, 80, 69, 70},
                {50, 60, 55, 67, 67, 70},
                {62, 65, 70, 81, 67, 70},
                {20, 66, 80, 69, 67, 70}
        };

        return travelTimes[getIndex(pickup)][getIndex(dropOff)];

    }

    private static int getIndex(String area) {
        for(int a = 0; a < AREA_NAMES.length; a++) {
            if (AREA_NAMES[a].equals(area))
                return a;
        }
        return -1;
    }

    // Add a taxi to the hashmaps
    public void addTaxi(Taxi taxi, String area) {
        taxis.put(taxi.getPlateNumber(), taxi);

        if (areas.containsKey(area)) {
            List areaTaxiList = areas.get(area);
            areaTaxiList.add(taxi);
        } else {

            ArrayList<Taxi> newAreaList = new ArrayList<Taxi>();

            newAreaList.add(taxi);
            areas.put(area, newAreaList);

        }

    }

    // Return a list of all available taxis within a certain area
    private ArrayList<Taxi> availableTaxisInArea(String area) {
        ArrayList<Taxi> result = new ArrayList<Taxi>();

        for (Taxi taxiInArea : areas.get(area)) {
            if (taxiInArea.getAvailable()) {
                result.add(taxiInArea);
            }
        }

        return result;
    }

    // Return a list of all busy taxis
    public ArrayList<Taxi> getBusyTaxis() {
        ArrayList<Taxi> result = new ArrayList<Taxi>();

        for (Taxi taxi : taxis.values()) {
            if (!taxi.getAvailable()) {
                result.add(taxi);
            }
        }

        return result;
    }

    // Find a taxi to satisfy the given request
    public Taxi sendTaxiForRequest(ClientRequest request) {

        String area = request.getPickupLocation();
        Taxi chosenTaxi = null;
        ArrayList<Taxi> availableTaxis = availableTaxisInArea(area);

        if (null != availableTaxis && !(availableTaxis.isEmpty())) {


            chosenTaxi = availableTaxis.get(0);

            addTaxi(chosenTaxi, area);

            availableTaxis.remove(0);

            chosenTaxi.setAvailable(false);

            chosenTaxi.setEstimatedTimeToDest(computeTravelTimeFrom(
                    request.getPickupLocation(), request.getDropoffLocation()));

            updateStats(request.getPickupLocation(),
                    request.getDropoffLocation());

        } else {

            String otherTaxiArea = getAreaWithAvailableTaxi(area);
            List<Taxi> otherTaxiAvailableAreas = availableTaxisInArea(otherTaxiArea);

            if (null != otherTaxiAvailableAreas && !(otherTaxiAvailableAreas.isEmpty())) {
                chosenTaxi = otherTaxiAvailableAreas.get(0);


                addTaxi(chosenTaxi, area);

                otherTaxiAvailableAreas.remove(0);

                chosenTaxi.setAvailable(false);

                chosenTaxi.setEstimatedTimeToDest(computeTravelTimeFrom(otherTaxiArea,
                        request.getPickupLocation()) +
                        computeTravelTimeFrom(request.getPickupLocation(),
                                request.getDropoffLocation()) );

                updateStats(request.getPickupLocation(), request.getDropoffLocation());

            }

        }

        return chosenTaxi;
    }

    private static int randomWithinRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public Map<String, ArrayList<Taxi>> getAreas() {
        return areas;
    }

    private String getAreaWithAvailableTaxi(String area) {
        List<Taxi> otherAreasAvailableTaxis = new ArrayList<>();
        List<Taxi> thisAreaAvailableTaxis = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Taxi>> entry : areas.entrySet()) {
            if (!entry.getKey().equals(area)) {
                thisAreaAvailableTaxis = this.availableTaxisInArea(entry.getKey());
                if (!thisAreaAvailableTaxis.isEmpty()) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

}