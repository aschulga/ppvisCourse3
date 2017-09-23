package Model;

/**
 * Created by Asus on 04.09.2017.
 */
public class OptionsLetter {

    private String letter;
    private int x;
    private int y;
    private String typeFont;
    private int size;
    private int font;
    int width;
    int height;

    public OptionsLetter(String letter, int x, int y, String typeFont, int font, int size,int width, int height)
    {
        this.letter = letter;
        this.x = x;
        this.y = y;
        this.typeFont = typeFont;
        this.font = font;
        this.size = size;
        this.width = width;
        this.height = height;
    }
    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public String getTypeFont()
    {
        return typeFont;
    }

    public void setTypeFont(String typeFont)
    {
        this.typeFont = typeFont;
    }

    public int getFont()
    {
        return font;
    }

    public void setFont(int font)
    {
        this.font = font;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public String getLetter()
    {
        return letter;
    }

    public void setLetter(String letter)
    {
        this.letter = letter;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

}
