package src.displayable.item;
import src.displayable.Displayable;
import src.action.Action;

import java.util.*;

public class Item extends Displayable{
    private String name;
    private int room;
    private int serial;
    private int visible;
    private int posX;
    private int posY;
    private int itemIntValue;
    private boolean isArmor = false;
    private boolean isSword = false;
    private List<Action> itemActions = new ArrayList<Action>();

    public int getPosX(){return this.posX;}
    public int getPosY(){return this.posY;}
    public String getName(){return this.name;}
    public int getItemIntValue(){return this.itemIntValue;}

    public boolean isArmor() {return this.isArmor;}
    public boolean isSword() {return this.isSword;}

    public void setIsArmor(boolean _isIt) {this.isArmor = _isIt;}

    public void setIsSword(boolean _isIt) {this.isSword = _isIt;}

    public void setName(String _name){name = _name;}

    public void setRoom(int _room){room = _room;}

    public void setSerial(int _serial){serial = _serial;}

    public void setVisible(int _visible){visible = _visible;}

    public void setPosX(int _posX){posX = _posX;}

    public void setPosY(int _posY){posY = _posY;}

    public void setItemIntValue(int _itemIntValue){itemIntValue = _itemIntValue;}

    public void addItemAction(Action a) {itemActions.add(a);}

    @Override
    public String toString()
    {
        String str = "Item: \n";
        str += "name: " + name + "\n";
        str += "room: " + room + "\n";
        str += "serial: " + serial + "\n";
        str += "visible: " + visible + "\n";
        str += "posX: " + posX + "\n";
        str += "posY: " + posY + "\n";
        str += "itemIntValue: " + itemIntValue + "\n";
 
        str += "\n";

        for(Action i : itemActions) {
            str += name + "'s ";
            str += i.toString();
        }
 
        str += "\n";

        return str;
    }    

}
