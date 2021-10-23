package src.displayable;

import src.displayable.structure.Room;
import src.displayable.structure.Passage;
import src.displayable.creatures.Creature;
import src.displayable.item.Item;

import java.util.*;

public class Dungeon {
    private String name;
    private int width;
    private int topHeight;
    private int gameHeight;
    private int bottomHeight;

    private List<Room> rooms = new ArrayList<Room>();

    private List<Passage> passages = new ArrayList<Passage>();

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