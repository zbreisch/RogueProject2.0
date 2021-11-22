package src.displayable;

import src.displayable.structure.Room;
import src.displayable.structure.Passage;
import src.displayable.creatures.*;
import src.displayable.item.Item;

import java.util.*;

// cd src;rm *.class;rm displayable/*.class;rm action/*.class;rm displayable/creatures/*.class;rm displayable/structure/*.class;rm displayable/item/*.class;cd..
// javac src/Rogue.java;java src/Rogue.java testdrawing.xml
// javac src/Rogue.java;java src/Rogue.java droppack.xml
public class Dungeon {
    private String name;
    private int width;
    private int topHeight;
    private int gameHeight;
    private int bottomHeight;
    private Player player;
    private List<Room> rooms = new ArrayList<Room>();
    private List<Passage> passages = new ArrayList<Passage>();

    // Called in in constructor for Game
    public void linkPassagesAndRooms()
    {
        for(Room r : rooms)
        {
            for(Passage p : passages)
            {
                if(p.getRoom1() == r.getRoom())
                {
                    r.addPassage(p);
                }
                else if(p.getRoom2() == r.getRoom())
                {
                    r.addPassage(p);
                }
            }
        }
    }

    public String getTopText()
    {
        String str = "HP: ";
        str += Integer.toString(player.getHP());
        str += " Score: ";
        str += Integer.toString(player.getScore());
        return str;
    }

    public String getBottomText()
    {
        return player.getBottomText();
    }

    public List<Room> getRooms(){return rooms;}
    public List<Passage> getPassages(){return passages;}

    public int getTopHeight(){return this.topHeight;}
    public int getGameHeight(){return this.gameHeight;}
    public int getBottomHeight(){return this.bottomHeight;}
    public int getWidth(){return this.width;}
    public Player getPlayer(){return player;}

    public void setPlayer(Player p){player = p;}

    public void addRoom(Room r)
    {
        rooms.add(r);
    }
        
    public void addPassage(Passage p) {passages.add(p);}

    public void setName(String _name)
    {
        name = _name;
    }

    public void setWidth(int _width)
    {
        width = _width;
    }

    public void setTopHeight(int _topHeight)
    {
        topHeight = _topHeight;
    }

    public void setGameHeight(int _gameHeight)
    {
        gameHeight = _gameHeight;
    }

    public void setBottomHeight(int _bottomHeight)
    {
        bottomHeight = _bottomHeight;
    }

    @Override
    public String toString()
    {
        String str = "Dungeon: \n";
        str += "name: " + name + "\n";
        str += "width: " + width + "\n";
        str += "topHeight: " + topHeight + "\n";
        str += "gameHeight: " + gameHeight + "\n";
        str += "bottomHeight: " + bottomHeight + "\n";

        str += "\n";

        for(Room r : rooms) {
            str += r.toString();
        }

        for(Passage p : passages) {
            str += p.toString();
        }

        return str;
    }    
}