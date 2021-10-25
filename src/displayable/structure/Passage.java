package src.displayable.structure;

import java.util.*;

public class Passage implements PlayerArea{  // Added implements
    private int room1;
    private int room2;
    private int visible;
    private List<Integer> posXs = new ArrayList<Integer>();
    private List<Integer> posYs = new ArrayList<Integer>();
    private List<Integer> fullPosXs = new ArrayList<Integer>();
    private List<Integer> fullPosYs = new ArrayList<Integer>();
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
        for(int i = 0; i < posYs.size(); i++){ // Looping through each of the elbows, less then so 1 less

            if(posXs.get(i) != posXs.get(i+1)){
                int wlx = (posXs.get(i+1)-posXs.get(i));
                int lx = Math.abs(wlx);
                for(int k=0; k<lx; k++){
                    if(posXs.get(i) < posXs.get(i+1)){ // i+1 is to the right
                       int newx = posXs.get(i) + k;
                       int newy = posYs.get(i);
                       fullPosXs.add((Integer) newx);
                       fullPosYs.add((Integer) newy);
                    }
                    else{
                       int newx = posXs.get(i) - k;
                       int newy = posYs.get(i);
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
                        int newy = posYs.get(i) + k;
                        int newx = posXs.get(i);
                        fullPosYs.add((Integer) newy);
                        fullPosXs.add((Integer) newx);
                    }
                    else{
                        int newy = posYs.get(i) - k;
                        int newx = posXs.get(i);
                        fullPosXs.add((Integer) newx);
                        fullPosYs.add((Integer) newy);
                    }
                        
                }
            }
        }
    }


    // posXs.get(0) (gets 0th X coordinate)
    // posXs.size() (gets number of unique elbows
    public Boolean isValidMove(int x, int y)
    {
        for(int i=0; i<fullPosXs.size(); i++){
            if(x == fullPosXs.get(i) && y == fullPosYs.get(i))
            return true;
        }
        return false;
    }

}
