package src.displayable.creatures;
import src.action.Action;
import src.displayable.Displayable;
import src.displayable.item.Item;

import java.util.*;

public class Creature extends Displayable{
   private String name;
   private int room;
   private int serial;
   private int visible;
   private int posX;
   private int posY;
   private int hp;
   private int hpMoves;
   private int maxHit;
   private String type;
   private List<Action> creatureActions = new ArrayList<Action>(); 
   private List<Item> creatureItems = new ArrayList<Item>(); // TO DO: Finish this

   public void setName(String _name) {
      name = _name;
   }

   public void setRoom(int _room) {room = _room;}

   public void setSerial(int _serial) {serial = _serial;}

   public void setVisible(int _visible){visible = _visible;}

   public void setPosX(int _posX){posX = _posX;}
   
   public void setPosY(int _posY){posY = _posY;}

   public void setHP(int _hp){hp = _hp;}

   public void setHPMoves (int _hpMoves){hpMoves = _hpMoves;}
   
   public void setMaxHit(int _maxHit){maxHit = _maxHit;}

   public void setType(String _type){type = _type;}

   public void addCreatureAction(Action a) {creatureActions.add(a);}

   public void addItem(Item i) {creatureItems.add(i);}

   @Override
   public String toString()
   {
       String str = "Creature: \n";
       str += "name: " + name + "\n";
       str += "room: " + room + "\n";
       str += "serial: " + serial + "\n";
       str += "visible: " + visible + "\n";
       str += "posX: " + posX + "\n";
       str += "posY: " + posY + "\n";
       str += "hp: " + hp + "\n";
       str += "maxHit: " + maxHit + "\n";
       str += "hpMoves: " + hpMoves + "\n";
       str += "type: " + type + "\n";

       str += "\n";

       for(Action c : creatureActions) {
           str += name + "'s ";
           str += c.toString();
       }


      for(Item i : creatureItems) {
         str += name + "'s ";
         str += i.toString();
      }

       return str;
   }    
}
