package src.displayable.creatures;  // Alot of changes here

import src.displayable.item.*;
import src.displayable.*;
import java.util.Queue;
import src.displayable.structure.*;

import java.util.concurrent.ConcurrentLinkedQueue;


public class Player extends Creature{

    private Item playerItem;
    private static String CLASSID = "Player";
    private PlayerArea currentArea;
    private int score;
    private String infoText = "";

    public int getScore(){return score;}
    public String getBottomText()
    {
        String str = "Pack: ";
        for(Item i : this.creatureItems)
        {
            str += i.getName();
        }
        str += "\nInfo: ";
        str += infoText;
        return str;
    }

    public void setPlayerItem(Item _playerItem) {playerItem = _playerItem;}

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
        // if(currentArea.isValidMove(proposedX, proposedY))
        if(true)
        {
            this.posX = proposedX;
            this.posY = proposedY;
        }
    }
}
