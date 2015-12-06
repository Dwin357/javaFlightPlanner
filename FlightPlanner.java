import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Prompt
 * You have been asked to write a planning program for an airline.
 * this program needs to:
 * When provided with the file, your program needs to read in a directed graph of airliner fuel usage
 * When provided with a series of airports (ie "A-B-C") provide the total fuel usage for the trip, or "NO SUCH TRIP" if it is not possible
 * When provided with two airports (ie "A", "B") provide the fuel usage for the most efficient path between them, or "NO.."
 * When provided with two airports (ie "A", "B") provide the num of layover it takes to connect them by the most direct path
 * When provided with one airport (ie "C") provide the fuel usage for the most efficient loop
 * When provided with one airport (ie "C") provide the num of layovers it takes make a loop by the most direct path
 * When provided with two airports and a number of layovers (ie "A", "C", 3) provide how many unique flight paths there are from A to C with exactly 3 layovers
 * When provided with two airports and a fuel amount (ie "A", "C", 4) provide how many unique flight paths there are from A to C which use less than 4 hundred gallons of fuel
 *      note both AC and ACDC would be considered unique flight paths for this
 *      
 *  Input
 *  An example of file text is: "Graph: AB9, CD3, BC8..." 
 *  where the first letter is the departure airport, the second letter is the arival airport, 
 *  and the number is how many gallons of fuel is needed for the flight (in hundreds of gallons)
 *  
 *  Importantly
 *  -the name of an airport will always begin with a capital letter
 *  -due to travel patterns, a flight from A -> B does not imply a flight from B -> A
 *  -due to wind patterns, a flight from A -> B might use a different amount of fuel than B -> A
 *  -every flight from A -> B will need to be loaded with the same amount of fuel (all edges have a single weight)
 * 
 * 
 */
public class FlightPlanner
{
    // instance variables - replace the example below with your own
    ArrayList<Airport> flightRoutes = new ArrayList<Airport>();

    /**
     * Constructor for objects of class FlightPlanner
     */
    public FlightPlanner(String filePath) throws FileNotFoundException
    {
       // String input = "thisIsMyCamelCase12";

       // this splits the digits from the words; which is ok for a start
       // String[] test = input.split("(?=(\\d.))");

        // this splits capital letters
        //  String[] test = input.split("(?=[A-Z])");

       ArrayList<String> routes = parseRoutesFromFile(filePath);
       for(String route : routes){
           addRouteToFlightRoutes(route);
        }
    }
    
    private ArrayList<String> parseRoutesFromFile(String filePath) throws FileNotFoundException{
        ArrayList<String> routes = new ArrayList<String>();
        Scanner sc = new Scanner(new File(filePath));
        sc.next();  // this gets rid of the "graph" label at the begining
        while (sc.hasNext()){
            routes.add(sc.next());
        }        
        return routes;
    }
    
    private void addRouteToFlightRoutes(String route){
        String[] args = route.split("(?=\\d+)");
        String[] airports = args[0].split("(?=[A-Z])");               
        boolean exists = false;
        Connection nextStop;
        
        // these feel weird; I don't understand why this isn't position 0 & 1... the first cell is empty
        String origin = airports[1];
        String destination = airports[2];
        int fuelLoad;
        Airport departingAirport = new Airport(origin, null);
        
        // chomp trailing comma & chng fuelLoad to int  !!! this is all me fighting w/ the regex splitting, should be a btr way
        String temp = "";
        for (int i=1; i< args.length; i++){
            if(args[i].endsWith(",")){
                temp = temp + args[i].substring(0, 1);
            } else {
                temp = temp + args[i];
            }
        }
        fuelLoad = Integer.parseInt(temp);
        
        // search flightRoutes to see if Airport already exists
        for (Airport leftFrom : flightRoutes){
            if(leftFrom.name() == departingAirport.name()){
                
                exists = true;
                departingAirport = leftFrom;
            }
        }
        // add new airport to flight routes if it doesn't exist
        if(!exists){
            flightRoutes.add(departingAirport);
        }
        // add connection to airport
        departingAirport.addConnection(destination, fuelLoad);
    }
    
    public void printFlightRoutes(){
        System.out.println("flight planner: " + flightRoutes.size());
        for (Airport airport : flightRoutes){
            airport.printAirport();
            System.out.println("\n");
        }
    }
}

