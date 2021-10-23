package src.displayable;
import src.displayable.structure.Room;
import src.displayable.structure.Passage;
import src.displayable.creatures.Creature;
import src.displayable.item.Item;

import java.util.*;

public class DisplayableGrid {
    private Stack<Displayable>[][] grid;

    public DisplayableGrid()
    {
        // this.grid =
    }

    public void generateGrid(Dungeon d)
    {
        List<Room> rooms = d.getRooms();
        for(Room r : rooms)
        {
            int x = r.getPosX();
            int y = r.getPosY();
            int h = r.getHeight();
            int w = r.getWidth();
            int i = x;
            int j = y;
            while(j < (y + h - 1))
            {
                while(i < (x + w - 1))
                {
                    
                }
            }
            grid[i][j] = r;
        }
    }

}
