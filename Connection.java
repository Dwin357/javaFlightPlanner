class Connection
/**
 * The node of a linked list which will keep track of the connections for each airport
 */
{
    private String name;
    private int fuelLoad;
    private Connection nextConnection;
    
    public Connection(String _name, int _fuelLoad, Connection _nextConnection){
        this.name = _name;
        this.fuelLoad = _fuelLoad;
        this.nextConnection = _nextConnection;
    }
    
    public String name(){
        return name;
    }
    
    public int fuelLoad(){
        return fuelLoad;
    }
    
    public void setNext(Connection _connection){
        nextConnection = _connection;
    }
    
    public Connection next(){
        return nextConnection;
    }      
    
    public void printConnection(){
        System.out.print("--> " + name + ":" + fuelLoad);
        if (nextConnection != null){
            nextConnection.printConnection();
        }
    }
}