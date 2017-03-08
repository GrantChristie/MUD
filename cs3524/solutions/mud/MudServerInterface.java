package cs3524.solutions.mud;

public interface MudServerInterface extends java.rmi.Remote{
   String status(String name) throws java.rmi.RemoteException;
   String setup(String location) throws java.rmi.RemoteException;
   String move(String loc, String dir, String thing) throws java.rmi.RemoteException;
   String pickup(String loc, String thing) throws java.rmi.RemoteException;
}
