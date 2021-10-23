package src.displayable;
import src.displayable.structure.Room;
import src.displayable.structure.Passage;
import src.displayable.creatures.Creature;
import src.displayable.item.Item;

import java.util.*;

public class DisplayableGrid {
    private Displayable[][] grid;

    public DisplayableGrid()
    {
        // this.grid =
    }

    public void generateGrid(Dungeon d)
    {
        List<Room> rooms = d.getRooms();
        List<Passage> passages = d.getPassages();
        for(Room r : rooms)
        {
            Displayable[][] roomGrid = r.getDisplayableGrid();
            int x = r.getPosX();
            int y = r.getPosY();
            int h = r.getHeight();
            int w = r.getWidth();
            int i = x;
            int j = y;
            for(i = x; i < (x+w-1); i++)
            {
                for(j = y; j<(y+h-1); j++)
                {
                    this.grid[i][j] = roomGrid[i-x][j-y];
                }
            }
        }

        for(Passage p : passages)
        {
            
        }
    }

}
