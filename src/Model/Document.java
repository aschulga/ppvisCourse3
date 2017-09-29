package Model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Asus on 03.09.2017.
 */
public class Document {

    private ArrayList<ArrayList<OptionsLetter>> listArrayList = new ArrayList<ArrayList<OptionsLetter>>();
    private ArrayList<OptionsRectangle> listRectangle = new ArrayList<OptionsRectangle>();
    private int startPageX = 20, startPageY = 70, zero = 0;
    private OptionsCarriage carriage = new OptionsCarriage(startPageX, startPageY, Color.black);

    public ArrayList<OptionsRectangle> getListRectangle()
    {
        return listRectangle;
    }

    public ArrayList<ArrayList<OptionsLetter>> getListArrayList()
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
        listArrayList.add(new ArrayList<OptionsLetter>());
    }

}
