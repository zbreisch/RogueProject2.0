package src.displayable;
import java.util.concurrent.ConcurrentLinkedQueue;
import src.displayable.structure.*;
public class Game implements Runnable {

    private static final int DEBUG = 0;
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    private static ObjectDisplayGrid displayGrid = null;
    private Thread keyStrokePrinter;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;
    private DisplayableGrid displayableGrid;
    private Dungeon dungeon;
    private int width;
    private int height;

    public Game(Dungeon d) {
        width = d.getWidth();
        height = d.getTopHeight() + d.getBottomHeight() + d.getGameHeight();
        // height = d.getGameHeight(); 
        displayGrid = new ObjectDisplayGrid(width, height);
        displayableGrid = new DisplayableGrid(d);
        dungeon = d;
        d.linkPassagesAndRooms();

        for(Passage p : d.getPassages()){
            p.populateAllCoordinates();
        }
    }

    @Override
    public void run() {
        displayGrid.fireUp();
        // displayGrid.initializeDisplay();
        // System.out.println(displayableGrid);
        // displayGrid.setObjectGrid(displayableGrid.getGridAsChars());
        
        
        displayGrid.refreshDisplay(displayableGrid.getGridAsChars());
        Char[][] newGrid;
        for(;;)
        {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            displayableGrid.generateGrid(this.dungeon);
            newGrid = displayableGrid.getGridAsChars();
            displayGrid.refreshDisplay(newGrid);
        }


        // for (int step = 1; step < WIDTH / 2; step *= 2) {
        //     for (int i = 0; i < WIDTH; i += step) {
        //         for (int j = 0; j < HEIGHT; j += step) {
        //             displayGrid.addObjectToDisplay(new Char('X'), i, j);
        //         }
        //     }

        //     try {
        //         Thread.sleep(2000);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace(System.err);
        //     }
        //     displayGrid.initializeDisplay();
        // }
    }

    public void runGame() throws Exception{

        // Game game = new Game(WIDTH, HEIGHT);
        Thread testThread = new Thread(this);
        testThread.start();

        this.keyStrokePrinter = new Thread(KeyStrokePrinter.getKeyStrokePrinter(displayGrid, dungeon.getPlayer()));
        this.keyStrokePrinter.start();

        testThread.join();
        this.keyStrokePrinter.join();
    }
}
