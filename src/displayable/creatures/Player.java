package src.displayable.creatures;  // Alot of changes here

import src.displayable.item.*;
import src.displayable.*;
import java.util.Queue;
import src.displayable.structure.*;

import java.util.concurrent.ConcurrentLinkedQueue;


public class Player extends Creature implements InputObserver, Runnable{

    private Item playerItem;
    private static int DEBUG = 1;
    private static String CLASSID = "Player";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    private PlayerArea currentArea;
    public void setPlayerItem(Item _playerItem) {playerItem = _playerItem;}

    public Player(ObjectDisplayGrid grid) {
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
    }

    public void setCurrentRoom(PlayerArea A){
        this.currentArea = A;
    }

    

    @Override
    public void observerUpdate(char ch) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
        }
        inputQueue.add(ch);
    }

    private boolean processInput() {

                char ch;
        
                boolean processing = true;
                while (processing) {
                    if (inputQueue.peek() == null) {
                        System.out.println("Testing display");
                        processing = false;
                    } else {
                        ch = inputQueue.peek(); // Changed poll to peek
                        if (DEBUG > 1) {
                            System.out.println(CLASSID + ".processInput peek is " + ch);
                        }
                        if (ch == 'X') {
                            System.out.println("got an X, ending input checking");
                            return false;
                        } 
                        if(ch == 'h' || ch == 'l' || ch == 'k' || ch == 'l')
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
                            if(currentArea.isValidMove(proposedX, proposedY))
                            {
                                this.posX = proposedX;
                                this.posY = proposedY;
                            }
                        }
                        else if(ch == 'p'){
                            //pick up 
                        }
                        else if(ch == 'i'){
                            //display the contents of the pack + identifying number refered  to item
                        }
                        else if(ch == 'c'){
                            //Change or take off armour
                        }
                        else if(ch == 'd'){
                            //drop item
                        }
                        else {
                            System.out.println("character " + ch + " entered on the keyboard. Not a valid character");
                        }
        
                    }
                }
                return true; 
            }

    public void run() {
        displayGrid.registerInputObserver(this);
        boolean working = true;
        while (working) {
            working = (processInput( ));
        }
    }

}

// package src.displayable;

// import java.util.Queue;
// import java.util.concurrent.ConcurrentLinkedQueue;

// public class KeyStrokePrinter implements InputObserver, Runnable {

//     private static int DEBUG = 1;
//     private static String CLASSID = "KeyStrokePrinter";
//     private static Queue<Character> inputQueue = null;
//     private ObjectDisplayGrid displayGrid;

//     public KeyStrokePrinter(ObjectDisplayGrid grid) {
//         inputQueue = new ConcurrentLinkedQueue<>();
//         displayGrid = grid;
//     }

//     @Override
//     public void observerUpdate(char ch) {
//         if (DEBUG > 0) {
//             System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
//         }
//         inputQueue.add(ch);
//     }

//     public char getInput(){
//         char character;
//         character = imputQueue.poll;
//         return character;
//         }
//     }

//     private void rest() {
//         try {
//             Thread.sleep(20);
//         } catch (InterruptedException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//     }

//     private boolean processInput() {

//         char ch;

//         boolean processing = true;
//         while (processing) {
//             if (inputQueue.peek() == null) {
//                 System.out.println("Testing display");
//                 processing = false;
//             } else {
//                 ch = inputQueue.peek(); // Changed poll to peek
//                 if (DEBUG > 1) {
//                     System.out.println(CLASSID + ".processInput peek is " + ch);
//                 }
//                 if (ch == 'X') {
//                     System.out.println("got an X, ending input checking");
//                     return false;
//                 } else {
//                     System.out.println("character " + ch + " entered on the keyboard");
//                 }

//             }
//         }
//         return true; 
//     }

//     @Override
//     public void run() {
//         displayGrid.registerInputObserver(this);
//         boolean working = true;
//         while (working) {
//             rest();
//             working = (processInput( ));
//         }
//     }
// }
