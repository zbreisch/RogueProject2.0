package src.displayable;
import src.displayable.structure.Room;
import src.displayable.structure.*;
import src.displayable.Char;
import src.displayable.creatures.Creature;
import src.displayable.item.Item;

import java.util.*;

public class DisplayableGrid {
    private Displayable[][] grid;
    private int gameHeight;
    private int gameWidth;
    private int topHeight;
    private int bottomHeight;
    private String topText;
    private String bottomText;

    public DisplayableGrid(Dungeon d)
    {
        gameHeight = d.getGameHeight();
        gameWidth = d.getWidth();
        topHeight = d.getTopHeight();
        bottomHeight = d.getBottomHeight();
        grid = new Displayable[gameWidth][gameHeight + topHeight + bottomHeight];
        // System.out.println("W/H 1:" + Integer.toString(gameWidth) + " " + Integer.toString(gameHeight));
        generateGrid(d); 
    }

    public Char[][] getGridAsChars()
    {
        Char[][] cGrid = new Char[gameWidth][gameHeight + topHeight + bottomHeight];
        int i = 0;
        int j = 0;
        char[] ch = topText.toCharArray();
        for(char c : ch)
        {
            if(i < gameWidth && c != '\n')
            {
                if(j < topHeight)
                {
                    cGrid[i][j] = new Char(c);
                }
                i++;
            }
            else
            {
                i = 0;
                j++;
            }
        }

        for(i = 0; i < (gameWidth - 1); i++)
        {
            for(j = topHeight; j < (gameHeight + topHeight - 1); j++)
            {
                if(grid[i][j] == null)
                {
                    cGrid[i][j] = new Char(' ');
                }
                else
                {
                    cGrid[i][j] = new Char(grid[i][j].toChar());
                }
            }
        }
        i = 0;
        j = topHeight + gameHeight;
        ch = bottomText.toCharArray();
        for(char c : ch)
        {
            if(i < gameWidth && c != '\n')
            {
                if(j < topHeight + gameHeight + bottomHeight)
                {
                    cGrid[i][j] = new Char(c);
                }
                i++;
            }
            else
            {
                i = 0;
                j++;
            }
        }
        return cGrid;
    }

    public void generateGrid(Dungeon d)
    {
        // Put HUD in when converting grid to characters
        topText = d.getTopText();
        bottomText = d.getBottomText();
        List<Room> rooms = d.getRooms();
        List<Passage> passages = d.getPassages();
        for(Room r : rooms)
        {
            Displayable[][] roomGrid = r.getDisplayableGrid();
            int x = r.getPosX();
            int y = r.getPosY() + topHeight;
            int h = r.getHeight();
            int w = r.getWidth();
            int i = x;
            int j = y;
            for(i = x; i < (x+w); i++)
            {
                for(j = y; j<(y+h); j++)
                {
                    this.grid[i][j] = roomGrid[i-x][j-y];
                }
            }
        }

        for(Passage p : passages)
        {
            List<Integer> x = p.getPosXs();
            List<Integer> y = p.getPosYs();

            if(x.size() == y.size()) {
                for(int i = 0; i < y.size(); i++) {
                    if(i == 0 || i == (y.size() - 1))
                    {
                        // If we assign passage junctions to rooms, this will need changed to pass
                        this.grid[x.get(i)][y.get(i) + topHeight] = new PassageJunction(); 
                    }
                    else
                    {
                        this.grid[x.get(i)][y.get(i) + topHeight] = new PassageFloor();
                    }
                }
            }
            else
            {
                // Should never get here
            }
        }
    }
    @Override
    public String toString()
    {
        String str = "";
        // for(Char[] cArr : getGridAsChars())
        // {
        //     for(Char c : cArr)
        //     {
        //             str += c.getChar();
        //     }
        // }
        Char [][] cgrd = getGridAsChars();
        for(int i = 0; i < (gameWidth - 1); i++)
        {
            for(int j = 0; j < (gameHeight - 1); j++)
            {
                str += cgrd[i][j].getChar();
                // System.out.println("i" + i +" "+ cgrd[i][j].getChar());
            }
        }
        return str;
    }
}
