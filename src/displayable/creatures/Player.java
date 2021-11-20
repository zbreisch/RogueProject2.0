package src.displayable.creatures;  // Alot of changes here

import src.displayable.item.*;
import src.displayable.*;
import java.util.Queue;
import src.displayable.structure.*;

import java.util.concurrent.ConcurrentLinkedQueue;


public class Player extends Creature{

    private static String CLASSID = "Player";
    private static PlayerArea currentArea;
    private int score;
    // private int gridPosX;
    // private int gridPosY;

    public int getScore(){return score;}

    // public void setGridPosX(int x, Room r){gridPosX = x;}
    // public void setGridPosY(int y, Room r){gridPosY = y;}

    public static void setPlayerArea(PlayerArea pa)
    {
        currentArea = pa;
    }

    public String getBottomText()
    {
        String str = "Pack: ";
        for(Item i : this.creatureItems)
        {
            System.out.println(i.getName());
            str += i.getName();
        }
        str += "\nInfo: ";
        str += Creature.infoText;
        return str;
    }

    public Player() {
    }

    public void setCurrentRoom(PlayerArea A){
        this.currentArea = A;
    }

    @Override
    public char toChar()
    {
        return '@';
    }


    public void reactToInput(char ch)
    {
        int proposedX = this.posX;
        int proposedY = this.posY;
        if(ch == 'h'){
            //Move left
            proposedX--;
        }
        else if(ch == 'l'){
            // move right
            proposedX++;
        }
        else if(ch == 'k'){
            //move up
            proposedY--;
        }
        else if(ch == 'j'){
            //move down
            proposedY++;
        }
        else if(ch == 'p')
        {
            System.out.println("ya");
            currentArea.pickUpItem(posX, posY);
        }
        else if(ch == 'd')
        {
            if(this.creatureItems.size() > 0)
            {
                // Note there may be bugs with displaying absolute position of items.  remains untested.
                Item toDrop = this.creatureItems.remove(0);
                toDrop.setPosX(this.posX);
                toDrop.setPosY(this.posY);
                currentArea.addItem(toDrop);
            }
            else
            {
                // do nothing
            }
        }
        if(currentArea.isValidMove(proposedX, proposedY))
        // if(true)
        {
            this.posX = proposedX;
            this.posY = proposedY;
        }
    }
}
