package src;

import src.displayable.Dungeon;
import src.displayable.Game;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Test {
    public static void main(String[] args) throws Exception{

	// check if a filename is passed in.  If not, print a usage message.
	// If it is, open the file
        String fileName = null;
        switch (args.length) {
        case 1:
           // note that the relative file path may depend on what IDE you are
	   // using.  This worked for NetBeans.
        fileName = "xmlFiles/" + args[0];
        // fileName = args[0];
           break;
        default:
           System.out.println("java Test <xmlfilename>");
	   return;
        }

	// Create a saxParserFactory, that will allow use to create a parser
	// Use this line unchanged
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

	// We haven't covered exceptions, so just copy the try { } catch {...}
	// exactly, // filling in what needs to be changed between the open and 
	// closed braces.
        try {
	    // just copy this
            SAXParser saxParser = saxParserFactory.newSAXParser();
	    // just copy this
            DungeonXMLHandler handler = new DungeonXMLHandler();
	    // just copy this.  This will parse the xml file given by fileName
            saxParser.parse(new File(fileName), handler);
	    // This will change depending on what kind of XML we are parsing
            Dungeon dungeon = handler.getDungeon();
	    // print out all of the students.  This will change depending on 
	    // what kind of XML we are parsing
            //System.out.println(dungeon.toString());
	// these lines should be copied exactly.
            int height = dungeon.getTopHeight() + dungeon.getBottomHeight() + dungeon.getGameHeight();
            Game game = new Game(dungeon.getWidth(), height);
            game.runGame();
            // Thread gameThread = new Thread(game);

            // game.keyStrokePrinter = new Thread(new KeyStrokePrinter(game.getDisplayGrid()));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(fileName);
            e.printStackTrace(System.out);
        }
    }
}
