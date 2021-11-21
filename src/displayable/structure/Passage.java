package src.displayable.structure;

import src.displayable.item.*;
import src.displayable.creatures.*;
import java.util.*;

public class Passage implements PlayerArea{  // Added implements
    private int room1;
    private int room2;
    private Room sourceRoom;
    private Room destRoom;
    private int visible;
    private List<Integer> posXs = new ArrayList<Integer>();
    private List<Integer> posYs = new ArrayList<Integer>();
    private List<Integer> fullPosXs = new ArrayList<Integer>();
    private List<Integer> fullPosYs = new ArrayList<Integer>();
    private Creature player;
    private List<Item> passageItems = new ArrayList<Item>();

    public List<Integer> getFullPosXs(){return fullPosXs;}
    public List<Integer> getFullPosYs(){return fullPosYs;}

    public void setSourceRoom(Room r){this.sourceRoom = r;}
    public void setDestRoom(Room r){this.destRoom = r;}

    public void setPlayer(Creature c){player = c;}

    public Integer getFirstX()
    {
        if(posXs.size() > 0){
            return posXs.get(0);
        }
        else
        {
            return Integer.valueOf(-1);
        }
    }
    public Integer getFirstY()
    {
        if(posYs.size() > 0){
            return posYs.get(0);
        }
        else
        {
            return Integer.valueOf(-1);
        }
    }
    public Integer getLastX()
    {
        if(posXs.size() > 1){
            return posXs.get(posXs.size() - 1);
        }
        else
        {
            return Integer.valueOf(-1);
        }
    }
    public Integer getLastY()
    {
        if(posYs.size() > 1){
            return posYs.get(posYs.size() - 1);
        }
        else
        {
            return Integer.valueOf(-1);
        }
    }


    public int getRoom1(){return room1;}
    public int getRoom2(){return room2;}

    public List<Integer> getPosXs(){return posXs;}
    public List<Integer> getPosYs(){return posYs;}
    
    public void addPosX(int posX)
    {
        posXs.add((Integer) posX);
    }

    public void addPosY(int posY)
    {
        posYs.add((Integer) posY);
    }

    public void setVisible(int _visible) {visible = _visible;}
    public void setRoom1(int _room)
    {
        room1 = _room;
    }

    public void setRoom2(int _room)
    {
        room2 = _room;
    }

    @Override
    public String toString()
    {
        String str = "Passage: \n";
        str += "room1: " + room1 + "\n";
        str += "room2: " + room2 + "\n";
        str += "visible: " + visible + "\n";

        if(posYs.size() == posXs.size()) {
            for(int i = 0; i < posYs.size(); i++) {
                str += "(X" + i + ", Y" + i +"): ";
                str += "(" + posXs.get(i) + ", " + posYs.get(i) +")\n";
            }
        }
        else {
            str += "Error in parsing (X,Y) pairs\n";
        }

        str += "\n";

        return str;
    }
    
    public void populateAllCoordinates()
    {
        int newx;
        int newy;
        for(int i = 0; i < posYs.size() - 1; i++){ // Looping through each of the elbows, less then so 1 less
            if(posXs.get(i) != posXs.get(i + 1)){
                int wlx = (posXs.get(i+1)-posXs.get(i));
                int lx = Math.abs(wlx);
                for(int k=0; k<lx; k++){
                    if(posXs.get(i) < posXs.get(i+1)){ // i+1 is to the right
                       newx = posXs.get(i) + k;
                       newy = posYs.get(i);
                       fullPosXs.add((Integer) newx);
                       fullPosYs.add((Integer) newy);
                    }
                    else{
                       newx = posXs.get(i) - k;
                       newy = posYs.get(i);
                       fullPosXs.add((Integer) newx);
                       fullPosYs.add((Integer) newy);
                    }
                }
            }
            if(posYs.get(i) != posYs.get(i+1)){
                int wly = (posYs.get(i+1)-posYs.get(i));
                int ly = Math.abs(wly);
                for(int k=0; k<ly; k++){
                    if(posYs.get(i) < posYs.get(i+1)){ // i+1 is below
                        newy = posYs.get(i) + k;
                        newx = posXs.get(i);
                        fullPosYs.add((Integer) newy);
                        fullPosXs.add((Integer) newx);
                    }
                    else{
                        newy = posYs.get(i) - k;
                        newx = posXs.get(i);
                        fullPosXs.add((Integer) newx);
                        fullPosYs.add((Integer) newy);
                    }
                }
            }
        }
        fullPosXs.add(this.getLastX());
        fullPosYs.add(this.getLastY());
    }

    public void pickUpItem(int x, int y)
    {
        if(passageItems.size() > 0)
        {
            Item toPickUp = null;
            for(Item i : passageItems)
            {
                if(i.getPosX() == x && i.getPosY() == y)
                {
                    toPickUp = i;
                }
            }

            if(toPickUp != null)
            {
                player.addItem(toPickUp);
                this.passageItems.remove(toPickUp);
            }
        }
        return;
    }

    public void addItem(Item i)
    {
        this.passageItems.add(i);
    }
    // posXs.get(0) (gets 0th X coordinate)
    // posXs.size() (gets number of unique elbows
    public Boolean isValidMove(int x, int y)
    {
        for(int i=0; i<fullPosXs.size(); i++){
            if(x == fullPosXs.get(i) && y == fullPosYs.get(i))
            {
                //player.incrementCount(); // BC
                return true;
            }
        }
        if(sourceRoom.isValidMove(x,y))
        {
            Player.setPlayerArea(sourceRoom);
            sourceRoom.addCreature(player);
            this.player = null;
            //player.incrementCount(); // BC
            return true;
        }
        else if(destRoom.isValidMove(x,y))
        {
            destRoom.addCreature(player);
            this.player = null;
            Player.setPlayerArea(destRoom);
            //player.incrementCount(); // BC
            return true;
        }
        else
        {
            return false;
        }
    }

}
