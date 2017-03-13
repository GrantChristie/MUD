package cs3524.solutions.mud;

import java.util.*;

public class MudServerImpl implements MudServerInterface {
  private MUD m;
  public Map<String, MUD> servers = new HashMap<String, MUD>();

  //Add two muds to the game
  public MudServerImpl(){
    servers.put("mud1", new MUD("mymud.edg","mymud.msg","mymud.thg"));
    servers.put("mud2", new MUD("mymud.edg","mymud.msg","mymud.thg"));
  }

  //get mud starting location
  public String getLocation() {
    return m.startLocation();
  }

  //Adds player to a mud
  public boolean addPlayer(String player) {
    //If the username already exists return false
    if (m.players.containsKey(player))
      return false;
    //Cap number of players per MUD, if a mud is full return false
    if (m.players.size() > 5){
      return false;
    }
    //add player with a starting location in the mud
    else{
      m.players.put(player, m.startLocation());
      return true;
    }
  }

  //Removes player from their mud
  public void delPlayer(String player){
    m.players.remove(player);
  }

  //Retrieve a list of players to display to the user
  public String getPlayers(String location) {
    ArrayList<String> Players = new ArrayList<String>();
    String player;

    StringBuilder sb = new StringBuilder();

    Iterator itter = m.players.keySet().iterator();

    while (itter.hasNext()) {
      player = itter.next().toString();
      if(m.players.get(player).equalsIgnoreCase(location)){
        Players.add(player);
        sb.append(player);
        sb.append(", ");
      }
    }
    sb.setLength(sb.length() - 2);
    return sb.toString();

  }

  //Update player location when they move
  public void updatePlayerLocation(String player, String location){
    m.players.remove(player);
    m.players.put(player, location);
  }

  //Returns information about the requested location.
  public String status(String location){
    return m.getVertex(location).toString();
  }

  //Moves the player given a location, direction and thing.
  public String move(String location, String direction){
    return m.moveThing(location, direction);
  }

  //Removes the item being picked up from it's current location.
  public String pickup(String location, String thing, String player){
    //if the item does not exist, inform the user
    if (!m.getVertex(location)._things.contains(thing)){
      return thing + " does not exist or cannot be picked up";
    }
    //if it does exist, inform the user they have picked up the item
    else {
      m.delThing(location, thing);
      return player + " picked up " + thing;
    }
  }

  //Retrieve list of mud servers available
  public String getServers() {
    StringBuilder sb = new StringBuilder();

    Iterator itter = servers.keySet().iterator();

    while (itter.hasNext()) {
      sb.append(itter.next().toString());
      sb.append(", ");
      }
    sb.setLength(sb.length() - 2);
    return "Servers: " + sb.toString();
  }

  //change m to the mud the user selected
  public void changeMUD(String mudChoice){
    m = servers.get(mudChoice);
  }
}
