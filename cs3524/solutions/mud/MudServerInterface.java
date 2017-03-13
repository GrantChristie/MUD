package cs3524.solutions.mud;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MudServerInterface extends Remote{
   String status(String location) throws RemoteException;
   String move(String location, String direction) throws RemoteException;
   String pickup(String location, String thing) throws RemoteException;
   boolean addPlayer(String player) throws RemoteException;
   String getLocation() throws RemoteException;
   String getPlayers(String location) throws RemoteException;
   void updatePlayerLocation(String player, String Location) throws RemoteException;
   void delPlayer(String player) throws RemoteException;
   String getServers() throws RemoteException;
   void changeMUD(String mudChoice) throws RemoteException;
}
