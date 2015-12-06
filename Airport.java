
public class Airport
/**
 * The root of a linked list which will keep track of the connections for each airport
 */
{
    private String name;
    private Connection connections;
    
    public Airport(String _name, Connection _connections){
        this.name = _name;
        this.connections = _connections;
    }
    
    public String name(){
        return name;
    }
    
    public void setConnections(Connection _connection){
        connections = _connection;
    }
    
    public Connection getConnections(){
        return connections;
    }
    
    public void addConnection(String destination, int fuelLoad){
        Connection newConnection = new Connection(destination, fuelLoad, this.connections);
        this.connections = newConnection;
    }
    
    public void printAirport(){
        System.out.println(name);
        if(connections != null){
            connections.printConnection();
        }
    }
}