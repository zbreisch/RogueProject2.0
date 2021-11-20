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

    ///bc
    private boolean infoFlag;
    
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
        if(infoFlag == true){
            displayCommandInfo(ch);
        }
        else if(ch == 'h'){
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
        else if(ch == 'H')
        {
            infoFlag = true;
        }
        else if(ch == '?'){
            Creature.infoText = "h, l, k, j, i, ?, H, c, d, p, R, T, w, E, 0-9. H <cmd> for more info"; 
        }
        
        if(currentArea.isValidMove(proposedX, proposedY))
        // if(true)
        {
            this.posX = proposedX;
            this.posY = proposedY;
        }
        checkMoves(); // bc
    }

    private void displayCommandInfo(char ch)
    {
        infoFlag = false;
        if(ch == 'h'){
            Creature.infoText = "This character moves the player to the left by one space";            
        }
        else if(ch == 'l'){
            Creature.infoText = "This character moves the player to the right by one space";            
        }
        else if(ch == 'k'){
            Creature.infoText = "This character moves the player up by one space";            
        }
        else if(ch == 'j'){
            Creature.infoText = "This character moves the player down by one space";            
        }
        else if(ch == 'i'){
            Creature.infoText = "This character shows the contents in the players inventory";            
        }
        else if(ch == 'c'){
            Creature.infoText = "This charactor takes off or changes armour";            
        }
        else if(ch == 'd'){
            Creature.infoText = "This d <item number> drops the item from the players pack";            
        }
        else if(ch == 'p'){
            Creature.infoText = "This character picks up the item under the placyer and puts it in the pack";            
        }
        else if(ch == 'r'){
            Creature.infoText = "This r <item in pack number> reads the scroll";            
        }
        else if(ch == 't'){
            Creature.infoText = "This character takes the weapon from the pack";            
        }
    }
}
