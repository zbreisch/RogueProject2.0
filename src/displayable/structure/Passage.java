package src.displayable.structure;

import java.util.*;

public class Passage implements PlayerArea{  // Added implements
    private int room1;
    private int room2;
    private int visible;
    private List<Integer> posXs = new ArrayList<Integer>();
    private List<Integer> posYs = new ArrayList<Integer>();

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

    public Boolean isValidMove(int x, int y) //Added method
    {
        return false;
    }
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

}
