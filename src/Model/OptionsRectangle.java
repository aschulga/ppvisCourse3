package Model;

/**
 * Created by Asus on 20.09.2017.
 */
public class OptionsRectangle {
    private int x;
    private int y;
    private int width;
    private int height;
    private int positionY;
    private int size;

    public OptionsRectangle(int x, int y, int width, int height, int positionY)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.positionY = positionY;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public int getSize()
    {
        return size;
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

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
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
