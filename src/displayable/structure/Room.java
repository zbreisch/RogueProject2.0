package src.displayable.structure;


import src.displayable.Displayable;
import src.displayable.creatures.Creature;
import src.displayable.item.Item;

import java.util.*;

public class Room extends Displayable{
    private int room;
    private int visible;
    private int posX; // (x,y) of top left corner of room
    private int posY;
    private int width;
    private int height;
    
    private List<Creature> creatures = new ArrayList<Creature>();
    private List<Item> items = new ArrayList<Item>();

    public void addCreature(Creature c) {creatures.add(c);}

    public void addItem(Item i) {items.add(i);}

    public void setRoom(int _room)
    {
        room = _room;
    }

    public void setVisible(int _visible){visible = _visible;}

    public void setPosX(int _posX)
    {
        posX = _posX;
    }
    public void setPosY(int _posY)
    {
        posY = _posY;
    }
    public void setWidth(int _width)
    {
        width = _width;
    }
    public void setHeight(int _height)
    {
        height = _height;
    }

    @Override
    public String toString()
    {
        String str = "Room: \n";
        str += "room: " + room + "\n";
        str += "visible: " + visible + "\n";
        str += "posX: " + posX + "\n";
        str += "posY: " + posY + "\n";
        str += "width: " + width + "\n";
        str += "height: " + height + "\n";

        str += "\n";

        for(Creature c : creatures) {
            str += "Room" + room + "'s ";
            str += c.toString();
        }

        for(Item i : items) {
            str += "Room" + room + "'s ";
            str += i.toString();
        }

        str += "\n";

        return str;
    }    
}
