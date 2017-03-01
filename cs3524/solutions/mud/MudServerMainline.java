package cs3524.solutions.mud;

public class MudServerMainline{
  public static void main(String args[]){
    System.out.println("mainline");
    if (args.length <2){
      System.err.println("Usage: \njava MudServerMainline <registryport> <serverport>");
      return;
    }
    try{
      String hostname = (java.net.InetAddress.getLocalHost()).getCanonicalHostName();
      MudServerImpl shoutServer = new MudServerImpl();
      int serverPort = Integer.parseInt(args[1]);
      MudServerInterface shoutServerStub = (MudServerInterface)java.rmi.server.UnicastRemoteObject.exportObject(shoutServer, serverPort);
      String regURL = "rmi://" + hostname + ":" + args[0] + "/MudService";
      System.out.println("Registering " + regURL);
      java.rmi.Naming.rebind(regURL, shoutServerStub);
    }catch(Exception e){
      System.err.println(e.getMessage());
    }
  }
}
