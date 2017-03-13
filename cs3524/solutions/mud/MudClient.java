package cs3524.solutions.mud;

import java.rmi.RMISecurityManager;
import java.rmi.Naming;

public class MudClient{
  static MudServerInterface server;
  private static String player, currentLocation, mudChoice;

  public static void main(String args[]) throws Exception {
    if (args.length < 2) {
      System.err.println("Usage: \njava MudClient <host> <port>");
      return;
    }

    String hostname = args[0];
    int port = Integer.parseInt(args[1]);

    System.setProperty("java.security.policy", "mud.policy");
    System.setSecurityManager(new RMISecurityManager());

    try {
      String registryURL = "rmi://" + hostname + ":" + port + "/MudService";
      server = (MudServerInterface) Naming.lookup(registryURL);
      setup();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  //Sets up the players game
  static void setup() throws Exception{
    //Print list of servers and request a choice from the user
    System.out.println(server.getServers());

    mudChoice = System.console().readLine("Please select a server: ");
    server.changeMUD(mudChoice);

    //Request a username from the player and set their starting location
    player = System.console().readLine("Please enter your username: ");
    currentLocation = server.getLocation();

    //If addPlayer() returns true call StartGame(), otherwise print an error message to the user
    if (server.addPlayer(player)) {
      startGame();
    }else {
      System.out.println("Fail");
    }
  }

  static void startGame() throws Exception{
    try{
      //Print the users location and the command needed to get instructions
      System.out.println(server.status(currentLocation));
      System.out.println("\nTo get instructions, type 'help'.\n");
      String input = "";
        //Main game loop, ends when user inputs "exit"
        while (!input.equalsIgnoreCase("exit")){
          input = System.console().readLine("\nWhat would you like to do? ");
          server.changeMUD(mudChoice);

          //if user inputs help, game instructions are printed
          if (input.equalsIgnoreCase("help")){
            System.out.println("\nTo exit the game, type 'exit'.");
            System.out.println("To move, type the direction you wish to move in. 'north', 'south', 'east', 'west'.");
            System.out.println("To pickup an item, type 'pickup' and the item you wish to pickup.");
            System.out.println("To see players at your location type 'players'");
            System.out.println("To see your current location, type 'where'");
          }
          //if user inputs one of 4 directions, the value of currentLocation is updated.
          else if (input.equalsIgnoreCase("north") || input.equalsIgnoreCase("east") || input.equalsIgnoreCase("south") || input.equalsIgnoreCase("west")){
            String move = server.move(currentLocation, input.toLowerCase());
            //if the attempted move doesnt change the users location, inform the user. otherwise update the users location
            if (move.equals(currentLocation)){
              System.out.println("Cannot move there");
            }else {
              currentLocation = server.move(currentLocation, input.toLowerCase());
              server.updatePlayerLocation(player, currentLocation);
              System.out.println(server.status(currentLocation));
            }
          }
          //if the user inputs pickup and an item, that item is removed from the currentLocation.
          else if(input.toLowerCase().contains("pickup")){
            input = input.toLowerCase().replace("pickup ", "");
            System.out.println(server.pickup(currentLocation, input.toLowerCase(), player));
          }
          //Display the all the players at the users location if requested
          else if(input.equalsIgnoreCase("players")) {
            System.out.println("Players at this Location:");
            System.out.println(server.getPlayers(currentLocation));
          }
          else if(input.equalsIgnoreCase("where")){
            System.out.println(server.status(currentLocation));
          }
          //if any other input is received, message is printed informing the user.
          else if(!input.equalsIgnoreCase("exit")){
            System.out.println("\nInvalid Action");
          }
        }
      //Removes the player from the mud when they exit
      System.out.println("Goodbye " + player);
      server.delPlayer(player);
    }
    catch(Exception e) {
        System.err.println(e.getMessage());
    }
  }
}