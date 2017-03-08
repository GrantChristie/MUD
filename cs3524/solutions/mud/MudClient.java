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
      String user = System.console().readLine("Please enter your username: ");
      String currentLocation = server.setup(user);
      String input = "";
      System.out.println("\nTo get instructions, type 'help'.");
      while (!input.equals("exit")){
          System.out.println(server.status(currentLocation));
          input = System.console().readLine("Do Something: ");
          currentLocation = server.move(currentLocation, input, user);
        if (input.equals("help")){
          System.out.println("To exit the game, type 'exit'.");
          System.out.println("To move, type the direction you wish to move in. 'north', 'south', 'east', 'west'.");
        }else if(input.contains("pickup")){
          input = input.replace("pickup ", "");
          server.pickup(currentLocation, input);
        }
      }
    }catch(Exception e) {
        System.err.println(e.getMessage());
      }
    System.out.println("Game Closed");
  }
}
