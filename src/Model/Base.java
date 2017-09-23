package Model;

import java.util.ArrayList;

/**
 * Created by Asus on 03.09.2017.
 */
public class Base {

    public ArrayList<ArrayList<OptionsLetter>> listArrayList = new ArrayList<ArrayList<OptionsLetter>>();
    public ArrayList<OptionsRectangle> listRectangle = new ArrayList<OptionsRectangle>();

    public ArrayList<OptionsRectangle> getListRectangle()
    {
        return listRectangle;
    }

    public ArrayList<ArrayList<OptionsLetter>> getListArrayList()
    {
        return listArrayList;
    }

    public void newListRectangle(int nextY)
    {
        for(int i = 0; i <= nextY; i++);
        {
            listRectangle.add(new OptionsRectangle(0,0,0,0,0));
        }

    }

    public void newListLetter(int nextY)
    {
        listArrayList.add(new ArrayList<OptionsLetter>());
    }




  /*  public void addArrayList(ArrayList<OptionsLetter> listletter)
    {
        listArrayList.add(listLetter);
    }

    public int sizeArrayList()
    {
        return listArrayList.size();
    }

    public ArrayList<OptionsLetter> getArrayListObject(int i)
    {
        return listArrayList.get(i);
    }

    public int size()
    {
        return listLetter.size();
    }

    public OptionsLetter get(int i)
    {
        return listLetter.get(i);
    }

    */



}
