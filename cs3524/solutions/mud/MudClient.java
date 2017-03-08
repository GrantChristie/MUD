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
          input = System.console().readLine("What would you like to do? ");
          currentLocation = server.move(currentLocation, input, user);

        if (input.equals("help")){
          System.out.println("\nTo exit the game, type 'exit'.");
          System.out.println("To move, type the direction you wish to move in. 'north', 'south', 'east', 'west'.");
          System.out.println("To pickup an item, type 'pickup' and the item you wish to pickup.");
        }

        else if(input.contains("pickup")){
          input = input.replace("pickup ", "");
          if (input.equals(user)){
            System.out.println("You cannot pickup yourself");
          }
          else{
            server.pickup(currentLocation, input);
          }
        }

      }
    }catch(Exception e) {
        System.err.println(e.getMessage());
      }
    System.out.println("Game Closed");
  }
}
