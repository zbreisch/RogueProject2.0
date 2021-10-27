package src.displayable.structure;

import src.displayable.creatures.*;
import src.displayable.item.*;

public interface PlayerArea {
    
    // Checks that moving the player to the proposed 
    // (x,y) coordinate is valid
    public Boolean isValidMove(int x, int y);

    public void pickUpItem(int x, int y);

    public void addItem(Item i);
}
