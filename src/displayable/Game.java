package src.displayable;

public class Game implements Runnable {

    private static final int DEBUG = 0;
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    private static ObjectDisplayGrid displayGrid = null;
    private Thread keyStrokePrinter;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;

    public Game(int width, int height) {
        displayGrid = new ObjectDisplayGrid(width, height);
    }

    @Override
    public void run() {
        displayGrid.fireUp();
        displayGrid.initializeDisplay();
        return;
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

        Game game = new Game(WIDTH, HEIGHT);
        Thread testThread = new Thread(game);
        testThread.start();

        game.keyStrokePrinter = new Thread(new KeyStrokePrinter(displayGrid));
        game.keyStrokePrinter.start();

        game.keyStrokePrinter.run();

        testThread.join();
        game.keyStrokePrinter.join();
    }
}