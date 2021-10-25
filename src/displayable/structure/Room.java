package src.displayable.structure;


import src.displayable.Displayable;
import src.displayable.creatures.Creature;
import src.displayable.item.Item;

import java.util.*;

public class Room extends Displayable implements PlayerArea{ //Added
    private int room;
    private int visible;
    private int posX; // (x,y) of top left corner of room
    private int posY;
    private int width;
    private int height;
    private Displayable[][] displayableGrid;
    private List<Creature> creatures = new ArrayList<Creature>();
    private List<Item> items = new ArrayList<Item>();
    private List<Passage> passages = new ArrayList<Passage>();
    private List<Integer> xJunctions = new ArrayList<Integer>();
    private List<Integer> yJunctions = new ArrayList<Integer>();

    public Displayable[][] getDisplayableGrid()
    {
        this.generateGrid();
        return displayableGrid;
    }

    public void addPassage(Passage p) {
        passages.add(p);
        if(this.room == p.getRoom1())
        {
            xJunctions.add(p.getFirstX());
            yJunctions.add(p.getFirstY());
        }
        else
        {
            xJunctions.add(p.getLastX());
            yJunctions.add(p.getLastY());
        }
    }

    //public Displayable getGrid(int x, int y){return this.displayableGrid[x][y];}
    private void generateGrid()
    {
        displayableGrid = new Displayable[this.width][this.height];
        int i;
        int j;
        for(i = 0; i < this.width; i++)
        {
            for(j = 0; j < this.height; j++)
            {
                if(j == 0 || j == (this.height - 1) || i == 0 || i == (this.width - 1))
                {
                    displayableGrid[i][j] = new RoomWall();
                }
                else
                {
                    displayableGrid[i][j] = new RoomFloor();
                }
            }
        }
        for(Item itm : this.items)
        {
            i = itm.getPosX() - this.posX;
            j = itm.getPosY() - this.posY;
            displayableGrid[i][j] = itm;
        }
        for(Creature c : this.creatures)
        {
            i = c.getPosX() - this.posX;
            j = c.getPosY() - this.posY;
            displayableGrid[i][j] = c;
        }
    }
    public int getPosX(){return this.posX;}
    public int getPosY(){return this.posY;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
    public int getRoom(){return room;}

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

    public Boolean isValidMove(int x, int y)
    {
        int unable = 0;

        for(Creature c : creatures) {
            int cx = c.getPosX();
            int cy = c.getPosY();
            if(cx == x && cy == y){
                unable = 1;
            }  
        }

        if(posX == x || (posX + width) == x || posY == y || (posY-height) == y || unable == 1){
            return false;
        }
        else
            return true;
    }
}
