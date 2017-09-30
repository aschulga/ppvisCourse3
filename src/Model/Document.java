package Model;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;


/**
 * Created by Asus on 03.09.2017.
 */
public class Document {

    private List<List<OptionsLetter>> listArrayList = new ArrayList<>();
    private List<OptionsRectangle> listRectangle = new ArrayList<>();
    private int startPageX = 20, startPageY = 70, zero = 0;
    private OptionsCarriage carriage = new OptionsCarriage(startPageX, startPageY, Color.black);

    public List<OptionsRectangle> getListRectangle()
    {
        return listRectangle;
    }

    public List<List<OptionsLetter>> getListArrayList()
    {
        return listArrayList;
    }

    public OptionsCarriage getCarriage()
    {
        return carriage;
    }

    public void newListRectangle(int nextY)
    {
        for(int i = 0; i <= nextY; i++);
        {
            listRectangle.add(new OptionsRectangle(zero,zero,zero,zero,zero));
        }

    }

    public void newListLetter(int nextY)
    {
        listArrayList.add(new ArrayList<>());
    }

}
