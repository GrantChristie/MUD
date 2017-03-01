package cs3524.solutions.mud;

public interface MudServerInterface extends java.rmi.Remote{
  public String print(String s) throws java.rmi.RemoteException;
}
