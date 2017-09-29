package Controller;

import Model.Document;
import Model.OptionsLetter;
import Model.OptionsRectangle;
import View.MyFrame;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Asus on 03.09.2017.
 */
public class Controller {
    private Document document;
    private int height;
    private int max = 0;
    private FontMetrics fm;
    private Graphics g;
    private int startPageX = 20, startPageY = 70, zero = 0, one = 1, two = 2;

    public Controller(Document document)
    {
        this.document = document;
    }

    public Color getColorCarriage()
    {
        return document.getCarriage().getColor();
    }

    public void setColorCarriage(Color color)
    {
        document.getCarriage().setColor(color);
    }

    public int getXCarriage()
    {
        return document.getCarriage().getX();
    }

    public int getYCarriage()
    {
        return document.getCarriage().getY();
    }

    public void setXCarriage(int x)
    {
        document.getCarriage().setX(x);
    }

    public void setYCarriage(int y)
    {
        document.getCarriage().setY(y);
    }

    public ArrayList<OptionsRectangle> getListRectangle()
    {
        return document.getListRectangle();
    }

    public void newListRectangle(int nextY)
    {
        document.newListRectangle(nextY);
    }

    public ArrayList<ArrayList<OptionsLetter>> getListArrayList()
    {
        return document.getListArrayList();
    }

    public void setHeightLetter(int height)
    {
        this.height = height;
    }

    public int getHeightLetter()
    {
        return height;
    }

    public void newListLetter(int nextY)
    {
        document.newListLetter(nextY);
    }

    public ArrayList<OptionsLetter> getElementArrayList(int nextY)
    {
        return getListArrayList().get(nextY);
    }

    public int getHeightList(ArrayList<OptionsLetter> list)
    {
        max = zero;

        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getHeight()>max)
            {
                max = list.get(i).getHeight();
            }
        }
        return max;
    }

    public void setGraphics(Graphics g)
    {
        this.g = g;
    }

    public int getN(ArrayList<OptionsRectangle> listRectangle, int nextY)
    {
        int nextX = zero;
        ArrayList<OptionsLetter> list = getListArrayList().get(nextY);
        nextX = zero;
        for (int j = 0; j < list.size(); j++) {
            if ((list.get(j).getX()) >= listRectangle.get(nextY).getX()) {
                nextX++;
            }
        }
        return  nextX;
    }

    public void addListLetter(int nextX, OptionsLetter letter, ArrayList<OptionsLetter> list)
    {
        for(int i = 0; i < nextX; i++) {

            list.get(list.size() - one - i).setX(list.get(list.size() - one - i).getX() + letter.getWidth());
        }

        if(list.size() != zero)
            list.add(list.size()-nextX,letter);
        else
            list.add(letter);
    }

    public int getSelection(ArrayList<OptionsRectangle> listRectangle, MyFrame frame, int nextX, int nextY, int number) {

        int h = 0, w = 0, s =0;
        int coordX = listRectangle.get(nextY).getX();

        g.setFont(new Font(frame.getPanelList().getType(),frame.getPanelList().getFont(),frame.getPanelList().getSize()));

        fm = g.getFontMetrics();

        ArrayList<OptionsLetter> list = getListArrayList().get(nextY);

        if (number == two)
            nextX = zero;

        for (int i = 0; i < list.size(); i++) {

            if ((list.get(i).getX()) >= listRectangle.get(nextY).getX() && (list.get(i).getX()) < (listRectangle.get(nextY).getX() + listRectangle.get(nextY).getWidth())) {
                s++;
            }
            listRectangle.get(nextY).setSize(s);

            if(number == two && ((list.get(i).getX())>=listRectangle.get(nextY).getX())) {
                nextX++;
            }
        }

        for (int i = 0; i < list.size(); i++) {

            if ((list.get(i).getX()) >= listRectangle.get(nextY).getX() && listRectangle.get(nextY).getSize() > zero) {

                w = fm.stringWidth(list.get(i).getLetter());
                h = fm.getHeight();

                addListLetter(nextX, new OptionsLetter(list.get(i).getLetter(), coordX, list.get(i).getY(),
                                frame.getPanelList().getType(), frame.getPanelList().getFont(), frame.getPanelList().getSize(), w, h),
                        list);

                deleteElement(nextX - one, list);

                coordX += w;
                nextX--;
                s--;
                listRectangle.get(nextY).setSize(s);
            }
        }
        return coordX;
    }

    public int getMaxHeightList(ArrayList<OptionsLetter> list)
    {
        int max = list.get(0).getHeight();
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getHeight() > max)
                max = list.get(i).getHeight();
        }

        return max;
    }

    public OptionsLetter getPositionXUp(int nextY, int carriageX)
    {
        int min = carriageX-getListArrayList().get(nextY).get(0).getX();
        OptionsLetter element = getListArrayList().get(nextY).get(0);

        for(int i = 0; i < getListArrayList().get(nextY).size(); i++)
        {
                if((carriageX-getListArrayList().get(nextY).get(i).getX())<min) {

                    min = carriageX - getListArrayList().get(nextY).get(i).getX();
                    if(min >= zero) {
                        element = getListArrayList().get(nextY).get(i - one);
                    }
                }
        }
        return element;
    }

    public OptionsLetter getPositionXDown(int nextY, int carriageX)
    {
        int min = getListArrayList().get(nextY).get(0).getX() - carriageX;
        OptionsLetter element = getListArrayList().get(nextY).get(0);

        for(int i = 0; i < getListArrayList().get(nextY).size(); i++)
        {
            if((getListArrayList().get(nextY).get(i).getX()-carriageX)>min) {

                min = getListArrayList().get(nextY).get(i).getX() - carriageX;
                if(min <= zero ) {
                    element = getListArrayList().get(nextY).get(i - one);
                }
            }

        }
        return element;
    }

    public void deleteElement(int next,ArrayList<OptionsLetter> listLeters)
    {
        for(int i = 0; i < next; i++)
            listLeters.get(listLeters.size()-1-i).setX(listLeters.get(listLeters.size()-one-i).getX()-listLeters.get(listLeters.size()-one-next).getWidth());

        listLeters.remove(listLeters.size()-1-next);
    }

    public void deleteList(int nextY, ArrayList<ArrayList<OptionsLetter>> arrayLists)
    {
        for(int i = nextY; i < arrayLists.size(); i++)
        {
            ArrayList<OptionsLetter> list = getListArrayList().get(i);

            for(int j = 0; j < list.size(); j++)
            {
                list.get(j).setY(getElementArrayList(i - 1).get(0).getY() + getElementArrayList(i).get(0).getHeight());
            }
        }
    }

    public void addLetters(int nextX, int nextY, int coordX, int coordY, ArrayList<ArrayList<OptionsLetter>> arrayLists)
    {
        ArrayList<OptionsLetter> listLetters = arrayLists.get(nextY+one);
        for(int i = nextX-1; i >= 0; i--)
        {
            arrayLists.get(nextY).add(new OptionsLetter(listLetters.get(listLetters.size()-1-i).getLetter(),coordX,coordY,
                    listLetters.get(listLetters.size()-one-i).getTypeFont(),listLetters.get(listLetters.size()-one-i).getFont(),
                    listLetters.get(listLetters.size()-one-i).getSize(),listLetters.get(listLetters.size()-one-i).getWidth(),listLetters.get(listLetters.size()-one-i).getHeight()));
            coordX += arrayLists.get(nextY).get(arrayLists.get(nextY).size()-one).getWidth();
        }
    }

    public Dimension getMaxLength(ArrayList<ArrayList<OptionsLetter>> arrayLists) {

        Dimension dimension = new Dimension(zero,zero);

        for (int i = 0; i < arrayLists.size(); i++) {
            ArrayList<OptionsLetter> list = getListArrayList().get(i);

            int maxX = zero;
            int maxY = zero;

            for (int j = 0; j < list.size(); j++) {
                maxX = list.get(j).getX();
                maxY = list.get(j).getY();
            }
            if (maxX > dimension.getWidth())
                dimension.setSize(maxX,dimension.getHeight());
            if (maxY > dimension.getHeight())
                dimension.setSize(dimension.getWidth(),maxY);
        }
        return dimension;
    }

    public ArrayList<OptionsLetter> newLine(int coordX, int coordY, int nextX, ArrayList<OptionsLetter> listLeters, MyFrame frame)
    {

        ArrayList<OptionsLetter> newListLeters = new ArrayList<>();

        for(int i = nextX-1; i >= 0; i--)
        {
            newListLeters.add(new OptionsLetter(listLeters.get(listLeters.size()-one-i).getLetter(),coordX,coordY,
                    listLeters.get(listLeters.size()-one-i).getTypeFont(),listLeters.get(listLeters.size()-one-i).getFont(),
                    listLeters.get(listLeters.size()-one-i).getSize(),listLeters.get(listLeters.size()-one-i).getWidth(),listLeters.get(listLeters.size()-one-i).getHeight()));
            coordX += newListLeters.get(newListLeters.size()-one).getWidth();
            listLeters.remove(listLeters.get(listLeters.size()-one-i));
        }

        return newListLeters;
    }

    public void changeParametrs(int nextY, ArrayList<ArrayList<OptionsLetter>> arrayLists)
    {
        for(int i = nextY; i < arrayLists.size(); i++)
        {
            ArrayList<OptionsLetter> list = getListArrayList().get(i);

            for(int j = 0; j < list.size(); j++)
            {
                list.get(j).setY(getElementArrayList(i - one).get(0).getY() + getMaxHeightList(getElementArrayList(i)));
            }
        }
    }

    public void testEmptyLetter(ArrayList<OptionsLetter> list)
    {
        if(list.size()>1) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLetter() == "")
                list.remove(list.get(i));
            }
        }
    }

    public void test(ArrayList<OptionsLetter> list)
    {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLetter() == "")
                list.remove(list.get(i));
        }
    }

}
