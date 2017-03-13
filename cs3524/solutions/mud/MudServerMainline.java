package cs3524.solutions.mud;

import java.rmi.RMISecurityManager;

public class MudServerMainline{
  public static void main(String args[]){
    System.out.println("mainline");
    if (args.length <2){
      System.err.println("Usage: \njava MudServerMainline <registryport> <serverport>");
      return;
    }
    int registryPort = Integer.parseInt(args[0]);
    int serverPort = Integer.parseInt(args[1]);

    try{
      String hostname = (java.net.InetAddress.getLocalHost()).getCanonicalHostName();

      System.setProperty("java.security.policy", "mud.policy");
      System.setSecurityManager(new RMISecurityManager());

      MudServerImpl mudServer = new MudServerImpl();
      MudServerInterface mudServerStub = (MudServerInterface)java.rmi.server.UnicastRemoteObject.exportObject(mudServer, serverPort);
      String regURL = "rmi://" + hostname + ":" + registryPort + "/MudService";
      System.out.println("Registering " + regURL);
      java.rmi.Naming.rebind(regURL, mudServerStub);
    }catch(Exception e){
      System.err.println(e.getMessage());
    }
  }
}
