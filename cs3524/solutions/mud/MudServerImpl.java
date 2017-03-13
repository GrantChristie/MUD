package cs3524.solutions.mud;

public class MudServerImpl implements MudServerInterface {
  public MUD m = new MUD("mymud.edg","mymud.msg","mymud.thg");

  public MudServerImpl(){ }

  //Adds the user's name to the start location to enable the user to start playing.
  public String setup(String name){
    m.addThing(m.startLocation(), name);
    return m.startLocation();
  }

  public String getLocation(){
    return m.startLocation();
  }

  public boolean addPlayer(String username) {
    if (m.players.size() < 10){
      m.players.put(username, m.startLocation());
      return true;
    }
    else{
      return false;
    }
  }

  //Returns information about the requested location.
  public String status(String location){
    return m.locationInfo(location);
  }

  //Moves the player given a location, direction and thing.
  public String move(String loc, String dir, String thing){
    return m.moveThing(loc, dir, thing);
  }

  //Removes the item being picked up from it's current location.
  public String pickup(String loc, String thing){
    m.delThing(loc, thing);
    return thing;
  }

}
