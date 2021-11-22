package src;

// import src.action.creatureAction.PlayerAction.*;
// import src.action.creatureAction.*;
// import src.action.itemAction.*;
import src.action.*;
import src.action.creatureAction.PlayerAction.ChangeDisplayType;
import src.action.creatureAction.PlayerAction.DropPack;
import src.action.creatureAction.PlayerAction.EmptyPack;
import src.action.creatureAction.PlayerAction.EndGame;
import src.action.creatureAction.PlayerAction.Remove;
import src.action.creatureAction.PlayerAction.Teleport;
import src.action.creatureAction.PlayerAction.UpdateDisplay;
import src.action.creatureAction.PlayerAction.YouWin;
import src.displayable.creatures.*;
import src.displayable.item.*;
import src.displayable.structure.*;
import src.displayable.Dungeon;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class DungeonXMLHandler extends DefaultHandler {

    // the two lines that follow declare a DEBUG flag to control
    // debug print statements and to allow the class to be easily
    // printed out.  These are not necessary for the parser.
    private static final int DEBUG = 1;
    private static final String CLASSID = "DungeonXMLHandler";

    // data can be called anything, but it is the variables that
    // contains information found while parsing the xml file
    private StringBuilder data = null;

    // When the parser parses the file it will add references to
    // Student objects to this array so that it has a list of 
    // all specified students.  Had we covered containers at the
    // time I put this file on the web page I would have made this
    // an ArrayList of Students (ArrayList<Student>) and not needed
    // to keep tract of the length and maxStudents.  You should use
    // an ArrayList in your project.

    // private ArrayList<Room> rooms;
    private Dungeon dungeon = new Dungeon();

    // The XML file contains a list of Students, and within each 
    // Student a list of activities (clubs and classes) that the
    // student participates in.  When the XML file initially
    // defines a student, many of the fields of the object have
    // not been filled in.  Additional lines in the XML file 
    // give the values of the fields.  Having access to the 
    // current Student and Activity allows setters on those 
    // objects to be called to initialize those fields.
    private Room roomBeingParsed = null;
    private Creature creatureBeingParsed = null;
    private Action actionBeingParsed = null;
    private Item itemBeingParsed = null;
    private Passage passageBeingParsed = null;

    // The bX fields here indicate that at corresponding field is
    // having a value defined in the XML file.  In particular, a
    // line in the xml file might be:
    // <instructor>Brook Parke</instructor> 
    // The startElement method (below) is called when <instructor>
    // is seen, and there we would set bInstructor.  The endElement
    // method (below) is called when </instructor> is found, and
    // in that code we check if bInstructor is set.  If it is,
    // we can extract a string representing the instructor name 
    // from the data variable above.

    private boolean bVisible = false;
    private boolean bPosX = false;
    private boolean bPosY = false;
    private boolean bWidth = false;
    private boolean bHeight = false;
    private boolean bType = false;
    private boolean bHP = false;
    private boolean bHPMoves = false;
    private boolean bMaxHit = false;
    private boolean bAction = false;
    private boolean bItemIntValue = false;
    private boolean bActionMessage = false;
    private boolean bActionIntValue = false;
    private boolean bActionCharValue = false;
    private boolean bRoom = false;
    private boolean bCreature = false;
    private boolean bItem = false;
    private boolean bPassage = false;

    // Used by code outside the class to get the list of Student objects
    // that have been constructed.

    public Dungeon getDungeon() {
        return dungeon;
    }

    // A constructor for this class.  It makes an implicit call to the
    // DefaultHandler zero arg constructor, which does the real work
    // DefaultHandler is defined in org.xml.sax.helpers.DefaultHandler;
    // imported above, and we don't need to write it.  We get its 
    // functionality by deriving from it!
    public DungeonXMLHandler() {
    }

    // startElement is called when a <some element> is called as part of 
    // <some element> ... </some element> start and end tags.
    // Rather than explain everything, look at the xml file in one screen
    // and the code below in another, and see how the different xml elements
    // are handled.
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }
        if (qName.equalsIgnoreCase("Dungeon")) {
            dungeon.setName(attributes.getValue("name"));
            dungeon.setWidth(Integer.parseInt(attributes.getValue("width")));
            dungeon.setTopHeight(Integer.parseInt(attributes.getValue("topHeight")));
            dungeon.setGameHeight(Integer.parseInt(attributes.getValue("gameHeight")));
            dungeon.setBottomHeight(Integer.parseInt(attributes.getValue("bottomHeight")));
        } else if (qName.equalsIgnoreCase("Rooms") || qName.equalsIgnoreCase("Passages")){
            // Do nothing.  These tags serve no purpose
        } else if (qName.equalsIgnoreCase("Room")) {
            roomBeingParsed = new Room();
            roomBeingParsed.setRoom(Integer.parseInt(attributes.getValue("room")));
            bRoom = true;
            dungeon.addRoom(roomBeingParsed);
        } else if (qName.equalsIgnoreCase("Passage")) {
            passageBeingParsed = new Passage();
            passageBeingParsed.setRoom1(Integer.parseInt(attributes.getValue("room1")));
            passageBeingParsed.setRoom2(Integer.parseInt(attributes.getValue("room2")));
            bPassage = true;
        } else if (qName.equalsIgnoreCase("Monster")) {
            creatureBeingParsed = new Creature();
            creatureBeingParsed.setName(attributes.getValue("name"));
            creatureBeingParsed.setRoom(Integer.parseInt(attributes.getValue("room")));
            creatureBeingParsed.setSerial(Integer.parseInt(attributes.getValue("serial")));
            if(attributes.getValue("name") == "Troll"){creatureBeingParsed.setType("T");}
            else if(attributes.getValue("name") == "Hobgoblin"){creatureBeingParsed.setType("H");}
            else if(attributes.getValue("name") == "Snake"){creatureBeingParsed.setType("S");}
            else {creatureBeingParsed.setType("!");} // Unknown monster type
            bCreature = true;
            roomBeingParsed.addCreature(creatureBeingParsed);
        } else if (qName.equalsIgnoreCase("Player")) {
            //creatureBeingParsed = new Creature();
            Player player = new Player();
            Player.setPlayerArea(roomBeingParsed);
            dungeon.setPlayer(player);
            creatureBeingParsed = player;
            
            creatureBeingParsed.setName(attributes.getValue("name"));
            creatureBeingParsed.setRoom(Integer.parseInt(attributes.getValue("room")));
            creatureBeingParsed.setSerial(Integer.parseInt(attributes.getValue("serial")));
            creatureBeingParsed.setType("@");
            bCreature = true;
            roomBeingParsed.addCreature(creatureBeingParsed);
        
        } else if (qName.equalsIgnoreCase("CreatureAction")) {
            String actionName = attributes.getValue("name");
            if(actionName == "ChangeDisplayType"){actionBeingParsed = new ChangeDisplayType();}
            else if(actionName == "Remove"){actionBeingParsed = new Remove();}
            else if(actionName == "Teleport"){actionBeingParsed = new Teleport();}
            else if(actionName == "UpdateDisplay"){actionBeingParsed = new UpdateDisplay();}
            else if(actionName == "YouWin"){actionBeingParsed = new YouWin();}
            else if(actionName == "DropPack"){actionBeingParsed = new DropPack();}
            else if(actionName == "EmptyPack"){actionBeingParsed = new EmptyPack();}
            else if(actionName == "EndGame"){actionBeingParsed = new EndGame();}
            else{actionBeingParsed = new Action();}
            actionBeingParsed.setName(actionName);
            actionBeingParsed.setType(attributes.getValue("type"));
            creatureBeingParsed.addCreatureAction(actionBeingParsed);
            bAction = true;
        } else if(qName.equalsIgnoreCase("ItemAction")) {
            actionBeingParsed = new Action();
            actionBeingParsed.setName(attributes.getValue("name"));
            actionBeingParsed.setType(attributes.getValue("type"));
            itemBeingParsed.addItemAction(actionBeingParsed);
            bAction = true;
        } else if (qName.equalsIgnoreCase("Armor")) {
            itemBeingParsed = new Armor();
            itemBeingParsed.setIsArmor(true);
            itemBeingParsed.setName(attributes.getValue("name"));
            itemBeingParsed.setRoom(Integer.parseInt(attributes.getValue("room")));
            itemBeingParsed.setSerial(Integer.parseInt(attributes.getValue("serial")));

            if(bCreature){
                if(creatureBeingParsed != null)
                {
                    creatureBeingParsed.setArmor((Armor)itemBeingParsed);
                }
                else{
                    creatureBeingParsed.addItem(itemBeingParsed);
                }
            }
            else {roomBeingParsed.addItem(itemBeingParsed);}
            bItem = true;
        } else if (qName.equalsIgnoreCase("Sword")) {
            itemBeingParsed = new Sword();
            itemBeingParsed.setIsSword(true);

            itemBeingParsed.setName(attributes.getValue("name"));
            itemBeingParsed.setRoom(Integer.parseInt(attributes.getValue("room")));
            itemBeingParsed.setSerial(Integer.parseInt(attributes.getValue("serial")));
            bItem = true;

            if(bCreature){
                if(creatureBeingParsed != null)
                {
                    creatureBeingParsed.setWeapon((Sword)itemBeingParsed);
                }
                else
                {
                    creatureBeingParsed.addItem(itemBeingParsed);
                }
            }
            else {roomBeingParsed.addItem(itemBeingParsed);}
        } else if (qName.equalsIgnoreCase("Scroll")) {
            itemBeingParsed = new Scroll();
            itemBeingParsed.setName(attributes.getValue("name"));
            itemBeingParsed.setRoom(Integer.parseInt(attributes.getValue("room")));
            itemBeingParsed.setSerial(Integer.parseInt(attributes.getValue("serial")));
            bItem = true;
            if(bCreature){creatureBeingParsed.addItem(itemBeingParsed);}
            else {roomBeingParsed.addItem(itemBeingParsed);}
        } else if (qName.equalsIgnoreCase("visible")) {
            bVisible = true;
        } else if (qName.equalsIgnoreCase("posX")) {
            bPosX = true;
        } else if (qName.equalsIgnoreCase("posY")) {
            bPosY = true;
        } else if (qName.equalsIgnoreCase("width")) { 
            bWidth = true;
        } else if (qName.equalsIgnoreCase("height")) {
            bHeight = true;
        } else if (qName.equalsIgnoreCase("type")) {
            bType = true;
        } else if (qName.equalsIgnoreCase("hp")) {
            bHP = true;
        } else if (qName.equalsIgnoreCase("hpMoves")) {
            bHPMoves = true;
        } else if (qName.equalsIgnoreCase("maxhit")) {
            bMaxHit = true;
        } else if (qName.equalsIgnoreCase("ItemIntValue")) {
            bItemIntValue = true;        
        } else if (qName.equalsIgnoreCase("actionMessage")) {
            bActionMessage = true;
        } else if (qName.equalsIgnoreCase("actionIntValue")) {
            bActionIntValue = true;
        } else if (qName.equalsIgnoreCase("actionCharValue")) {
            bActionCharValue = true;
        } else {
            System.out.println("Unknown qname: " + qName);
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bVisible) {
            if (bItem) {itemBeingParsed.setVisible(Integer.parseInt(data.toString()));}
            else if (bCreature) {creatureBeingParsed.setVisible(Integer.parseInt(data.toString()));}
            else if (bRoom) {roomBeingParsed.setVisible(Integer.parseInt(data.toString()));}
            else if (bPassage) {passageBeingParsed.setVisible(Integer.parseInt(data.toString()));}
            else {
                // Should never get here
            }
            bVisible = false;
        } else if (bPosX) {
            if (bItem) {
                if(bCreature){
                // itemBeingParsed.setPosX(Integer.parseInt(data.toString()));
                    itemBeingParsed.setPosX(creatureBeingParsed.getPosX());
                }
                else if (bRoom)
                {
                    itemBeingParsed.setPosX(Integer.parseInt(data.toString()) + roomBeingParsed.getPosX());
                }
                else
                {
                    itemBeingParsed.setPosX(Integer.parseInt(data.toString()));
                }
            }
            else if (bCreature) {creatureBeingParsed.setPosX(Integer.parseInt(data.toString()) + roomBeingParsed.getPosX());}
            else if (bRoom) {roomBeingParsed.setPosX(Integer.parseInt(data.toString()));}
            else if (bPassage) {passageBeingParsed.addPosX(Integer.parseInt(data.toString()));}
            else {
                // Should never get here
            }
            bPosX = false;
        } else if (bPosY) {
            if (bItem) {                
                if(bCreature){
                // itemBeingParsed.setPosX(Integer.parseInt(data.toString()));
                    itemBeingParsed.setPosY(creatureBeingParsed.getPosY());
                }
                else if (bRoom)
                {
                    itemBeingParsed.setPosY(Integer.parseInt(data.toString()) + roomBeingParsed.getPosY());
                }
                else
                {
                    itemBeingParsed.setPosY(Integer.parseInt(data.toString()));
                }
            }
            else if (bCreature) {creatureBeingParsed.setPosY(Integer.parseInt(data.toString()) + roomBeingParsed.getPosY());}
            else if (bRoom) {roomBeingParsed.setPosY(Integer.parseInt(data.toString()));}
            else if (bPassage) {passageBeingParsed.addPosY(Integer.parseInt(data.toString()));}
            else {
                // Should never get here
            }
            bPosY = false;
        } else if (bWidth) {
            if (bRoom) {roomBeingParsed.setWidth(Integer.parseInt(data.toString()));}
            else {
                // Should never get here
            }
            bWidth = false;
        } else if (bHeight) {
            if (bRoom) {roomBeingParsed.setHeight(Integer.parseInt(data.toString()));}
            else {
                // Should never get here
            }
            bHeight = false;
        } else if (bType) {
            if (bCreature) {creatureBeingParsed.setType(data.toString());}
            else {
                // Should never get here
            }
            bType = false;
        } else if (bHP) {
            if (bCreature) {creatureBeingParsed.setHP(Integer.parseInt(data.toString()));}
            else {
                // Should never get here
            }
            bHP = false;
        } else if (bHPMoves) {
            if (bCreature) {creatureBeingParsed.setHPMoves(Integer.parseInt(data.toString()));}
            else {
                // Should never get here
            }
            bHPMoves = false;
        } else if (bMaxHit) {
            if (bCreature) {creatureBeingParsed.setMaxHit(Integer.parseInt(data.toString()));}
            else {
                // Should never get here
            }
            bMaxHit = false;
        } else if (bActionMessage) { 
            actionBeingParsed.setActionMessage(data.toString());
            bActionMessage = false;
        } else if (bActionIntValue) {
            actionBeingParsed.setActionIntValue(Integer.parseInt(data.toString()));
            bActionIntValue = false;
        } else if (bActionCharValue) {
            actionBeingParsed.setActionCharValue(data.toString().charAt(0));
            bActionCharValue = false;
        } else if (bItemIntValue) {
            if (bItem) {
                itemBeingParsed.setItemIntValue(Integer.parseInt(data.toString()));}
            else {
                // Should never get here
            }
            bItemIntValue = false;
        } else if (bAction) {
            bAction = false;
        } else if (bItem) {
            bItem = false;
        } else if (bCreature) {
            bCreature = false;
        } else if (bRoom) {
            bRoom = false;
        }
        else if (bPassage) {
            dungeon.addPassage(passageBeingParsed);
            bPassage = false;
        }

        if (qName.equalsIgnoreCase("Room")) {
            roomBeingParsed = null;
        } else if (qName.equalsIgnoreCase("Creature")) {
            creatureBeingParsed = null;
        } else if(qName.equalsIgnoreCase("CreatureAction")) {
            actionBeingParsed = null;
        }else if (qName.equalsIgnoreCase("Item")) {
            itemBeingParsed = null;
        }
    }

    // private void addDungeon(Dungeon dungeon) {
    //     dungeons[dungeonCount++] = dungeon;
    // }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".characters: " + new String(ch, start, length));
            System.out.flush();
        }
    }

    @Override
    public String toString() {
        String str = "DungeonXMLHandler\n";
        // str += "   maxStudents: " + maxStudents + "\n";
        // str += "   studentCount: " + studentCount + "\n";
        // for (Student student : students) {
        //     str += student.toString() + "\n";
        // }

        str += "Dungeon: " + dungeon.toString() + "\n";

        // str += "   roomBeingParsed: " + roomBeingParsed.toString() + "\n";
        // str += "   creatureBeingParsed: " + creatureBeingParsed.toString() + "\n";
        // str += "   creatureActionBeingParsed: " + actionBeingParsed.toString() + "\n";
        // str += "   itemBeingParsed: " + itemBeingParsed.toString() + "\n";
        // str += "   bVisible: " + bVisible + "\n";
        // str += "   bPosX: " + bPosX + "\n";
        // str += "   bPosY: " + bPosY + "\n";
        // str += "   bWidth: " + bWidth + "\n";
        // str += "   bHeight: " + bHeight + "\n";
        // str += "   bType: " + bType + "\n";
        // str += "   bMebHPetingDay: " + bHP + "\n";
        // str += "   bMeetinbMaxHitDay: " + bMaxHit + "\n";
        // str += "   bActionMessage: " + bActionMessage + "\n";
        // str += "   bActionIntValue: " + bActionIntValue + "\n";
        // str += "   bActionCharValue: " + bActionCharValue + "\n";
        // str += "   bRoom: " + bRoom + "\n";
        // str += "   bCreature: " + bCreature + "\n";
        // str += "   bItem: " + bItem + "\n";

        return str;
    }
}
