package src.displayable;

import src.displayable.creatures.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class KeyStrokePrinter implements InputObserver, Runnable {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    private Player player;
    private static KeyStrokePrinter keyPrinter = null;

    private KeyStrokePrinter(ObjectDisplayGrid grid, Player p) {
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
        player = p;
    }

    public static KeyStrokePrinter getKeyStrokePrinter(ObjectDisplayGrid grid, Player p)
    {
        if(keyPrinter == null)
        {
            return new KeyStrokePrinter(grid, p);
        }
        else
        {
            return keyPrinter;
        }
    }

    @Override
    public void observerUpdate(char ch) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
        }
        inputQueue.add(ch);
    }

    private void rest() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private boolean processInput() {

        char ch;

        boolean processing = true;
        while (processing) {
            if (inputQueue.peek() == null) {
                // System.out.println("Testing display");
                processing = false;
            } else {
                ch = inputQueue.poll(); // Changed poll to peek
                // if (ch == 'X') {
                //     System.out.println("got an X, ending input checking");
                //     return false;
                // } 
                if(ch == 'h' || ch == 'l' || ch == 'k' || ch == 'j' || ch == 'p' || ch == 'd'
                || ch == 'H' || ch =='E' || ch =='?' || ch =='i' || ch =='r' || ch =='T' || ch == 'y' || ch == 'Y'
                || ch =='w'|| ch =='c' || ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9')
                {
                    player.reactToInput(ch);
                }
                // else if(ch == 'p'){
                //     //pick up 
                // }
                // else if(ch == 'i'){
                //     //display the contents of the pack + identifying number refered  to item
                // }
                // else if(ch == 'c'){
                //     //Change or take off armour
                // }
                // else if(ch == 'd'){
                //     //drop item
                // }
                else {
                    System.out.println("character " + ch + " entered on the keyboard. Not a valid character");
                }

            }
        }
        return true; 
    }

    @Override
    public void run() {
        displayGrid.registerInputObserver(this);
        boolean working = true;
        while (working) {
            rest();
            working = (processInput( ));
        }
    }
}
