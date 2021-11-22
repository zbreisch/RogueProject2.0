package src.displayable.creatures;
import src.action.Action;
import src.displayable.Displayable;
import src.displayable.item.*;

import java.util.*;

public class Creature extends Displayable{
   protected Armor armor;
   protected Sword weapon;
   private String name;
   private int room;
   private int serial;
   private int visible;
   protected int posX;
   protected int posY;
   private int hp;
   private int hpMoves;
   private int maxHit;
   private String type;
   static protected String infoText = "";
   private List<Action> creatureActions = new ArrayList<Action>(); 
   protected List<Item> creatureItems = new ArrayList<Item>(); // TO DO: Finish this


   public int getPosX(){return this.posX;}
   public int getPosY(){return this.posY;}
   public int getHP(){return this.hp;}
   public String getName(){return this.name;}
   public String getType(){return this.type;}

   public int moveCount; // BC

   public boolean equipArmor(int inventoryIdx)
   {
       if(this.armor == null)
       {
           if(inventoryIdx < creatureItems.size())
           {
                if(creatureItems.get(inventoryIdx).isArmor())
                {
                    this.armor = (Armor) this.creatureItems.remove(inventoryIdx);
                    return true;
                }
            }
       }
       return false;
   }

   public boolean equipWeapon(int inventoryIdx)
   {
       if(this.armor != null)
       {
            unequipWeapon();
       }
       if(inventoryIdx < creatureItems.size() && creatureItems.get(inventoryIdx).isSword())
       {
           this.weapon = (Sword) this.creatureItems.remove(inventoryIdx);
           return true;
       }
       return false;
   }

   public boolean unequipArmor()
   {
       if(this.armor != null)
       {
           this.addItem(this.armor);
           this.armor = null;
           return true;
       }
       return false;
   }

   public boolean unequipWeapon()
   {
       if(this.weapon != null)
       {
           this.addItem(this.weapon);
           this.weapon = null;
           return true;
       }
       return false;
   }

   public void setArmor(Armor _playerItem) {this.armor = _playerItem;}

   public void setWeapon(Sword _playerItem) {this.weapon = _playerItem;}

   public Armor getArmor() {return this.armor;}

   public Sword getWeapon() {return this.weapon;}

   public void initiateCombat(Creature opponent)
   {
      Random rand = new Random();
      int damage = rand.nextInt(this.maxHit);
      Sword oppWeapon = opponent.getWeapon();
      Armor oppArmor = opponent.getArmor();
      if(this.weapon != null)
      {
         damage += this.weapon.getItemIntValue();
      }
      if(oppArmor != null)
      {
         damage -= oppArmor.getItemIntValue();
      }
      Creature.infoText = this.name + " did " + Integer.toString(damage) + " damage to " + opponent.name;
      opponent.setHP(opponent.getHP() - damage);
      damage = rand.nextInt(opponent.maxHit);
      if(oppWeapon != null)
      {
         damage += oppWeapon.getItemIntValue();
      }
      if(this.armor != null)
      {
         damage -= this.armor.getItemIntValue();
      }
      Creature.infoText +=" and took " + Integer.toString(damage) + " damage.";
      this.hp -= damage;
      // Do some logic to see if anyone has died
   }

   @Override
   public char toChar()
   {
      if(name == "Player")
      {
         return '@';
      }
      else
      {
         return this.type.charAt(0);
      }
   }


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

    public void incrementCount(){ // BC
        moveCount++;
    }

    public void resetCount(){
        moveCount = 0;
    }

    public void checkMoves(){
        if(moveCount == hpMoves){
        moveCount = 0;
        hp++;
        }
    }

}
