package Controller;

import Model.Document;
import Model.OptionsLetter;
import Model.OptionsRectangle;
import View.MyFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 03.09.2017.
 */
public class Controller {
    private Document document;
    private int height;
    private int max = 0;
    private FontMetrics fm;
    private Graphics g;
    private int startPageX = 20, startPageY = 70, zero = 0;
    private boolean isOne = false;
    private boolean isTwo = false;
    private boolean isStart = false;
    private int positionX = 0;
    private int coordX = 20, coordY = 70;
    private int nextX = 0, nextY = 0;

    public boolean getIsOne()
    {
        return isOne;
    }

    public void setIsOne(boolean isOne)
    {
        this.isOne = isOne;
    }

    public boolean getIsTwo()
    {
        return isTwo;
    }

    public void setIsTwo(boolean isTwo)
    {
        this.isTwo = isTwo;
    }

    public boolean getIsStart()
    {
        return isStart;
    }

    public void setIsStart(boolean isStart)
    {
        this.isStart = isStart;
    }

    public int getPositionX()
    {
        return positionX;
    }

    public void setPositionX(int positionX)
    {
        this.positionX = positionX;
    }

    public int getCoordX()
    {
        return coordX;
    }

    public void setCoordX(int coordX)
    {
        this.coordX = coordX;
    }

    public int getCoordY()
    {
        return coordY;
    }

    public void setCoordY(int coordY)
    {
        this.coordY = coordY;
    }

    public int getNextX()
    {
        return nextX;
    }

    public void setNextX(int nextX)
    {
        this.nextX = nextX;
    }

    public int getNextY()
    {
        return nextY;
    }

    public void setNextY(int nextY)
    {
        this.nextY = nextY;
    }

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

    public List<OptionsRectangle> getListRectangle()
    {
        return document.getListRectangle();
    }

    public void newListRectangle(int nextY)
    {
        document.newListRectangle(nextY);
    }

    public List<List<OptionsLetter>> getListArrayList()
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

    public List<OptionsLetter> getElementArrayList(int nextY)
    {
        return getListArrayList().get(nextY);
    }

    public int getHeightList(List<OptionsLetter> list)
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

    public Graphics getGraphics()
    {
        return g;
    }

    public void setGraphics(Graphics g)
    {
        this.g = g;
    }

    public int getN(List<OptionsRectangle> listRectangle, int nextY)
    {
        int nextX = zero;
        List<OptionsLetter> list = getListArrayList().get(nextY);
        nextX = zero;
        for (int j = 0; j < list.size(); j++) {
            if ((list.get(j).getX()) >= listRectangle.get(nextY).getX()) {
                nextX++;
            }
        }
        return  nextX;
    }

    public void addListLetter(int nextX, OptionsLetter letter, List<OptionsLetter> list)
    {
        for(int i = 0; i < nextX; i++) {

            list.get(list.size() - 1 - i).setX(list.get(list.size() - 1 - i).getX() + letter.getWidth());
        }

        if(list.size() != zero)
            list.add(list.size()-nextX,letter);
        else
            list.add(letter);
    }

    public int getSelection(List<OptionsRectangle> listRectangle, MyFrame frame, int nextX, int nextY, int number) {

        OptionsRectangle rectangle = listRectangle.get(nextY);
        int h = 0, w = 0, s =0;
        int coordX = rectangle.getX();

        g.setFont(new Font(frame.getPanelList().getType(),frame.getPanelList().getFont(),frame.getPanelList().getSize()));

        fm = g.getFontMetrics();

        List<OptionsLetter> list = getListArrayList().get(nextY);

        if (number == 2)
            nextX = zero;

        for (int i = 0; i < list.size(); i++) {

            if ((list.get(i).getX()) >= rectangle.getX() &&
                    (list.get(i).getX()) < (rectangle.getX() + rectangle.getWidth()))
            {
                s++;
            }
            rectangle.setSize(s);

            if(number == 2 && ((list.get(i).getX())>=rectangle.getX())) {
                nextX++;
            }
        }

        for (int i = 0; i < list.size(); i++) {

            if ((list.get(i).getX()) >= rectangle.getX() && rectangle.getSize() > zero) {

                w = fm.stringWidth(list.get(i).getLetter());
                h = fm.getHeight();

                addListLetter(nextX, new OptionsLetter(list.get(i).getLetter(), coordX, list.get(i).getY(),
                                frame.getPanelList().getType(), frame.getPanelList().getFont(), frame.getPanelList().getSize(), w, h), list);

                deleteElement(nextX - 1, list);

                coordX += w;
                nextX--;
                s--;
                rectangle.setSize(s);
            }
        }
        return coordX;
    }

    public int getMaxHeightList(List<OptionsLetter> list)
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
                        element = getListArrayList().get(nextY).get(i - 1);
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
                    element = getListArrayList().get(nextY).get(i - 1);
                }
            }

        }
        return element;
    }

    public void deleteElement(int next,List<OptionsLetter> listLeters)
    {
        for(int i = 0; i < next; i++)
            listLeters.get(listLeters.size()-1-i).setX(listLeters.get(listLeters.size()-1-i).getX()-listLeters.get(listLeters.size()-1-next).getWidth());

        listLeters.remove(listLeters.size()-1-next);
    }

    public void deleteList(int nextY, List<List<OptionsLetter>> arrayLists)
    {
        for(int i = nextY; i < arrayLists.size(); i++)
        {
            List<OptionsLetter> list = getListArrayList().get(i);

            for(int j = 0; j < list.size(); j++)
            {
                list.get(j).setY(getElementArrayList(i - 1).get(0).getY() + getElementArrayList(i).get(0).getHeight());
            }
        }
    }

    public void addLetters(int nextX, int nextY, int coordX, int coordY, List<List<OptionsLetter>> arrayLists)
    {
        List<OptionsLetter> listLetters = arrayLists.get(nextY+1);
        for(int i = nextX-1; i >= 0; i--)
        {
            arrayLists.get(nextY).add(new OptionsLetter(listLetters.get(listLetters.size()-1-i).getLetter(),coordX,coordY,
                    listLetters.get(listLetters.size()-1-i).getTypeFont(),listLetters.get(listLetters.size()-1-i).getFont(),
                    listLetters.get(listLetters.size()-1-i).getSize(),listLetters.get(listLetters.size()-1-i).getWidth(),
                    listLetters.get(listLetters.size()-1-i).getHeight()));
            coordX += arrayLists.get(nextY).get(arrayLists.get(nextY).size()-1).getWidth();
        }
    }

    public Dimension getMaxLength(List<List<OptionsLetter>> arrayLists) {

        Dimension dimension = new Dimension(zero,zero);

        for (int i = 0; i < arrayLists.size(); i++) {
            List<OptionsLetter> list = getListArrayList().get(i);

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

    public List<OptionsLetter> newLine(int coordX, int coordY, int nextX, List<OptionsLetter> listLeters, MyFrame frame)
    {

        List<OptionsLetter> newListLeters = new ArrayList<>();

        for(int i = nextX-1; i >= 0; i--)
        {
            newListLeters.add(new OptionsLetter(listLeters.get(listLeters.size()-1-i).getLetter(),coordX,coordY,
                    listLeters.get(listLeters.size()-1-i).getTypeFont(),listLeters.get(listLeters.size()-1-i).getFont(),
                    listLeters.get(listLeters.size()-1-i).getSize(),listLeters.get(listLeters.size()-1-i).getWidth(),
                    listLeters.get(listLeters.size()-1-i).getHeight()));
            coordX += newListLeters.get(newListLeters.size()-1).getWidth();
            listLeters.remove(listLeters.get(listLeters.size()-1-i));
        }

        return newListLeters;
    }

    public void changeParametrs(int nextY, List<List<OptionsLetter>> arrayLists)
    {
        for(int i = nextY; i < arrayLists.size(); i++)
        {
            List<OptionsLetter> list = getListArrayList().get(i);

            for(int j = 0; j < list.size(); j++)
            {
                list.get(j).setY(getElementArrayList(i - 1).get(0).getY() + getMaxHeightList(getElementArrayList(i)));
            }
        }
    }

    public void testEmptyLetter(List<OptionsLetter> list)
    {
        if(list.size()>1) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLetter() == "")
                list.remove(list.get(i));
            }
        }
    }

    public void test(List<OptionsLetter> list)
    {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLetter() == "")
                list.remove(list.get(i));
        }
    }

}