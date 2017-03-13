package cs3524.solutions.mud;

import java.util.*;

public class MudServerImpl implements MudServerInterface {
  public MUD m = new MUD("mymud.edg","mymud.msg","mymud.thg");

  public MudServerImpl(){ }

  public String getLocation(){
    return m.startLocation();
  }

  public boolean addPlayer(String player) {
    if (m.players.containsKey(player))
      return false;
    if (m.players.size() > 10){
      return false;
    }
    else{
      m.players.put(player, m.startLocation());
      return true;
    }
  }

  public void delPlayer(String player){
    m.players.remove(player);
  }

  public String getPlayers(String location) {
    ArrayList<String> Players = new ArrayList<String>();
    String username;

    StringBuilder sb = new StringBuilder();

    Iterator itter = m.players.keySet().iterator();

    while (itter.hasNext()) {
      username = itter.next().toString();
      if(m.players.get(username).equalsIgnoreCase(location)){
        Players.add(username);
        sb.append(username);
        sb.append(", ");
      }

    }

    sb.setLength(sb.length() - 2);

    return sb.toString();

  }

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
  public String pickup(String location, String thing){
    m.delThing(location, thing);
    return thing;
  }

}
