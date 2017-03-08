package cs3524.solutions.mud;

public class MudServerImpl implements MudServerInterface {
  public MUD m = new MUD("mymud.edg","mymud.msg","mymud.thg");

  public MudServerImpl(){ }

  public String setup(String name){
    m.addThing(m.startLocation(), name);
    return m.startLocation();
  }

  public String status(String location){
    return m.locationInfo(location);
  }

  public String move(String loc, String dir, String thing){
    return m.moveThing(loc, dir, thing);
  }

  public String pickup(String loc, String thing){
    m.delThing(loc, thing);
    return thing;
  }

}
