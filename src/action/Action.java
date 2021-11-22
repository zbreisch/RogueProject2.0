package src.action;

public class Action {
    private String name;
    private String type;
    private String actionMessage;
    private int actionIntValue;
    private char actionCharValue;

    public void setName(String _name) {name = _name;}
    public String getType(){return this.type}

    public void setType(String _type) {type = _type;}

    public void setActionMessage(String _actionMessage) {actionMessage = _actionMessage;}

    public void setActionIntValue(int _actionIntValue) {actionIntValue = _actionIntValue;}

    public void setActionCharValue(char _actionCharValue) {actionCharValue = _actionCharValue;}

    @Override
    public String toString()
    {
        String str = "Action: \n";
        str += "name: " + name + "\n";
        str += "type: " + type + "\n";
        str += "actionMessage: " + actionMessage + "\n";
        str += "actionIntValue: " + actionIntValue + "\n";
        str += "actionCharValue: " + actionCharValue + "\n";

        str += "\n";

        return str;
    }    

}
