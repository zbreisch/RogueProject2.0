package src.displayable.structure;

public interface PlayerArea {
    
    // Checks that moving the player to the proposed 
    // (x,y) coordinate is valid
    public Boolean isValidMove(int x, int y);
}
