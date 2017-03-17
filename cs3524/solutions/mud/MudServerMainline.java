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

      System.out.println("\nTo create a new MUD, type 'create <name> <edgesfile> <messagesfile> <thingsfile>' with 1 space between each variable");
      //loop to allow multiple MUDs to be created
      while (true){
        String input = "";
        input = System.console().readLine("\n");

        //if the user requests to create a mud, split the input into an array so each component of the mud can be added
        if (input.toLowerCase().contains("create")){
          String[] components = input.split(" ");

          //if a mud with that name already exists, return a message to the user
          if (mudServer.servers.containsKey(components[1].toLowerCase())){
            System.out.println("Mud with that name aleady exists");
          }
          //if there are less than 5 muds and the user has entered the correct number of components then add the mud
          else if (mudServer.servers.size()<5 && components.length == 5){
            MUD m = new MUD(components[2], components[3], components[4]);
            mudServer.servers.put(components[1].toLowerCase(), m);
            System.out.println("Mud created with name " + components[1].toLowerCase());
          }
          else{
            System.out.println("Mud cannot be created");
          }
        }
        else{
          System.out.println("Not a valid Command");
        }
      }
    }catch(Exception e){
      System.err.println(e.getMessage());
    }
  }
}
