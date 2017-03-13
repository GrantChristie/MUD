package cs3524.solutions.mud;

public interface MudServerInterface extends java.rmi.Remote{
   String status(String location) throws java.rmi.RemoteException;
   String move(String location, String direction) throws java.rmi.RemoteException;
   String pickup(String location, String thing) throws java.rmi.RemoteException;
   boolean addPlayer(String username) throws java.rmi.RemoteException;
   String getLocation() throws java.rmi.RemoteException;
   String getPlayers(String location) throws java.rmi.RemoteException;
   void updatePlayerLocation(String user, String Location) throws java.rmi.RemoteException;
}
