package cs3524.solutions.mud;

public class MudClient{
  public static void main(String args[]) throws java.rmi.RemoteException{
    if (args.length <2) {
      System.err.println("Usage: \njava MudClient <host> <port>");
      return;
    }
    String registryURL = "rmi://" + args[0] + ":" + args[1] + "/MudService";
    try{
      MudServerInterface server = (MudServerInterface)java.rmi.Naming.lookup(registryURL);
      System.out.println(server.print(System.console().readLine("Message :")));
    }catch(Exception e) {
        System.err.println(e.getMessage());
      }
  }
}
