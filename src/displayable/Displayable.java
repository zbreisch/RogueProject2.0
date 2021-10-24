package src.displayable;

public class Displayable {
    private int visible;
    
    public void setVisible(int _visible){visible = _visible;}

    public char toChar()
    {
        return '~'; // Default character for a displayable is ~.  This should be overwritten
    }

    public void print()
    {
        System.out.println(this.toChar());
    }
}
