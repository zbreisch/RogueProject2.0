package src.displayable;

public class Char {

    public static final String CLASSID = "Char";
    private final char displayChar;

    public Char(char c) {
        displayChar = c;
    }
    
    public char getChar( ) {
        return displayChar;
    }

    public static int getIntValue(char ch)
    {
        if(ch == '0')
        {
            return 0;
        }
        else if(ch == '1')
        {
            return 1;
        }
        else if(ch == '2')
        {
            return 2;
        }
        else if(ch == '3')
        {
            return 3;
        }
        else if(ch == '4')
        {
            return 4;
        }
        else if(ch == '5')
        {
            return 5;
        }
        else if(ch == '6')
        {
            return 6;
        }
        else if(ch == '7')
        {
            return 7;
        }
        else if(ch == '8')
        {
            return 8;
        }
        else if(ch == '9')
        {
            return 9;
        }
        else
        {
            return -1;
        }
    }
}
