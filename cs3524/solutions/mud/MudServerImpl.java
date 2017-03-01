package cs3524.solutions.mud;

public class MudServerImpl implements MudServerInterface {
  public String print(String s){
    MUD m = new MUD("mymud.edg","mymud.msg","mymud.thg");
    return m.toString();
  }
}
