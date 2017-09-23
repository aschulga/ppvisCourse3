package Controller;

import Model.Base;
import Model.OptionsLetter;
import Model.OptionsRectangle;
import View.MyFrame;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Asus on 03.09.2017.
 */
public class Controller {
    private Base base;
    private int height;
    private int max;
    private FontMetrics fm;
    int nextX = 0;
    int nextY = 0;
    //int cd = 0;

    //OptionsCarriage carriage = new OptionsCarriage(20, 70);

    //int coordX=20, coordY=70;


    public Controller(Base base)
    {
        this.base = base;
    }

    public ArrayList<OptionsRectangle> getListRectangle()
    {
        return base.getListRectangle();
    }

    public void newListRectangle(int nextY)
    {
        base.newListRectangle(nextY);
    }

    public ArrayList<ArrayList<OptionsLetter>> getListArrayList()
    {
        return base.getListArrayList();
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
        base.newListLetter(nextY);
    }

    public ArrayList<OptionsLetter> getElementArrayList(int nextY)
    {
        return getListArrayList().get(nextY);
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




   /* public void setCarriageX(int coordX)
    {
        this.carriage.setX(coordX);
    }

    public int getCarriageX()
    {
        return carriage.getX();
    }

    public void setCarriageY(int coordY)
    {
        this.carriage.setY(coordY);
    }

    public int getCarriageY()
    {
        return carriage.getY();
    }*/


    public void addListLetter(int nextX, OptionsLetter letter, ArrayList<OptionsLetter> list)
    {
        for(int i = 0; i < nextX; i++) {

            list.get(list.size() - 1 - i).setX(list.get(list.size() - 1 - i).getX() + letter.getWidth());
        }

        if(list.size()!=0)
            list.add(list.size()-nextX,letter);
        else
            list.add(letter);
    }

  /*  public void gets()
    {



        if(nextY!=0)
        {
            changeParametrs(nextY, getListArrayList());

            coordY = getElementArrayList(nextY - 1).get(0).getY() + getMaxHeightList(getElementArrayList(nextY));
            carriage.setY(coordY);
        }

        getListRectangle().get(nextY).setX(0);
        getListRectangle().get(nextY).setY(0);
        getListRectangle().get(nextY).setHeight(0);
        getListRectangle().get(nextY).setWidth(0);

    }*/

    public void setFm(FontMetrics fm)
    {
        this.fm = fm;
    }

    public FontMetrics getFm()
    {
        return fm;
    }

    public void getSelection(OptionsRectangle listRectangle, ArrayList<OptionsLetter> list, MyFrame frame, int nextX)
    {
        int w = 0, h = 0;
        int coordX = listRectangle.getX();
        int s = 0;
        int t = nextX;


        for(int j = 0; j < list.size(); j++)
        {

            if((list.get(j).getX())>=listRectangle.getX() && (list.get(j).getX())<(listRectangle.getX()+listRectangle.getWidth())) {
                s++;
            }

        }

        for(int i = 0; i < list.size(); i++)
        {

            System.out.println("letter "+list.get(i).getLetter());
            if((list.get(i).getX())>=listRectangle.getX() && s>0) {

                w = getFm().stringWidth(list.get(i).getLetter());
                h = getFm().getHeight();

                if (list.get(i).getX() != 20) {


                    nextX--;

                    addListLetter(nextX, new OptionsLetter(list.get(i).getLetter(), coordX, list.get(i).getY(), frame.getPanelList().getType(), frame.getPanelList().getFont(), frame.getPanelList().getSize(), w, h),
                            list);

                    deleteElement(nextX - 1, list);

                    coordX += w;
                    System.out.println("coordX " + coordX);
                    //  nextX--;
                    s--;

                } else {

                    addListLetter(nextX, new OptionsLetter(list.get(i).getLetter(), coordX, list.get(i).getY(), frame.getPanelList().getType(), frame.getPanelList().getFont(), frame.getPanelList().getSize(), w, h),
                            list);

                    deleteElement(nextX-1, list);

                    coordX += w;
                    s--;

                }
            }
        }
    }




    public void setMaxHeight(int max)
    {
        this.max = max;
    }

    public int getMaxHeight()
    {
        return max;
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

    public int getMinHeightList(ArrayList<OptionsLetter> list)
    {
        int min = list.get(0).getHeight();
        for(int i = 0; i < list.size(); i++)
        {
            if(min < list.get(i).getHeight())
                min = list.get(i).getHeight();
        }

        return min;
    }

    public OptionsLetter getPositionXUp(int nextY, int carriageX)
    {
        int min = carriageX-getListArrayList().get(nextY).get(0).getX();
        OptionsLetter element = getListArrayList().get(nextY).get(0);

        for(int i = 0; i < getListArrayList().get(nextY).size(); i++)
        {
                if((carriageX-getListArrayList().get(nextY).get(i).getX())<min) {

                    min = carriageX - getListArrayList().get(nextY).get(i).getX();
                    if(min>=0 ) {
                        element = getListArrayList().get(nextY).get(i-1);



                        //System.out.println(getListArrayList().get(nextY).get(i-1).getLetter());
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
                if(min<=0 ) {
                    element = getListArrayList().get(nextY).get(i-1);
                }
            }

        }
        return element;
    }

    public int getCoordXList(ArrayList<OptionsLetter> list)
    {
        return list.get(list.size()-1).getX()+list.get(list.size()-1).getWidth();
    }

    public int getCoordYList(ArrayList<OptionsLetter> list)
    {
        return list.get(list.size()-1).getY();
    }


    public void deleteElement(int next,ArrayList<OptionsLetter> listLeters)
    {

        for(int i = 0; i < next; i++)
            listLeters.get(listLeters.size()-1-i).setX(listLeters.get(listLeters.size()-1-i).getX()-listLeters.get(listLeters.size()-1-next).getWidth());

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
        //arrayLists.remove(arrayLists.get(nextY));

    }

    public void addLetters(int nextX, int nextY, int coordX, int coordY, ArrayList<ArrayList<OptionsLetter>> arrayLists)
    {
        ArrayList<OptionsLetter> listLetters = arrayLists.get(nextY+1);
        for(int i = nextX-1; i>=0; i--)
        {
            arrayLists.get(nextY).add(new OptionsLetter(listLetters.get(listLetters.size()-1-i).getLetter(),coordX,coordY,
                    listLetters.get(listLetters.size()-1-i).getTypeFont(),listLetters.get(listLetters.size()-1-i).getFont(),
                    listLetters.get(listLetters.size()-1-i).getSize(),listLetters.get(listLetters.size()-1-i).getWidth(),listLetters.get(listLetters.size()-1-i).getHeight()));
            coordX += arrayLists.get(nextY).get(arrayLists.get(nextY).size()-1).getWidth();
        }


       // listLeters.remove(listLeters.get(listLeters.size()-1-i));


    }

    public ArrayList<OptionsLetter> newLine(int coordX, int coordY, int nextX, ArrayList<OptionsLetter> listLeters, MyFrame frame)
    {

        ArrayList<OptionsLetter> newListLeters = new ArrayList<>();

        /*newListLeters.add(new OptionsLetter("",coordX,coordY,frame.getPanelList().getType(),
                frame.getPanelList().getFont(),frame.getPanelList().getSize(),0,getHeightLetter()));*/

        for(int i = nextX-1; i >= 0; i--)
        {
            newListLeters.add(new OptionsLetter(listLeters.get(listLeters.size()-1-i).getLetter(),coordX,coordY,
                    listLeters.get(listLeters.size()-1-i).getTypeFont(),listLeters.get(listLeters.size()-1-i).getFont(),
                    listLeters.get(listLeters.size()-1-i).getSize(),listLeters.get(listLeters.size()-1-i).getWidth(),listLeters.get(listLeters.size()-1-i).getHeight()));
            coordX += newListLeters.get(newListLeters.size()-1).getWidth();
           // newListLeters.add(listLeters.get(listLeters.size()-1-i));
            listLeters.remove(listLeters.get(listLeters.size()-1-i));
        }

        return newListLeters;
    }

    public void newSize(int height, int nextY, ArrayList<ArrayList<OptionsLetter>> arrayLists)
    {
        int s = 0;
        for(int i = nextY; i < arrayLists.size(); i++) {
            s++;
            ArrayList<OptionsLetter> list = arrayLists.get(i);

            if (s <= 1) {
                for (int j = 0; j < list.size(); j++)
                    list.get(j).setY(getElementArrayList(i - 1).get(0).getY() + height);
            } else
                for (s = 0; s < list.size(); s++)
                    list.get(s).setY(getElementArrayList(i - 1).get(0).getY() + getElementArrayList(i).get(0).getHeight());
        }
    }


    public void changeParametrs(int nextY, ArrayList<ArrayList<OptionsLetter>> arrayLists)
    {
        for(int i = nextY; i < arrayLists.size(); i++)
        {
            ArrayList<OptionsLetter> list = getListArrayList().get(i);


            for(int j = 0; j < list.size(); j++)
            {
                list.get(j).setY(getElementArrayList(i-1).get(0).getY() + getMaxHeightList(getElementArrayList(i)));
                System.out.println("list.get(j).getY2 "+list.get(j).getY());
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


    /*
    public void getSelection(ArrayList<OptionsRectangle> listRectangle, ArrayList<ArrayList<OptionsLetter>> arrayLists, MyFrame frame, FontMetrics fm, int coordX, int nextX, int nextY)
    {
        int w =0, h = 0;

        //coordX+=getElementArrayList(nextY).get(nextX).getWidth();

        for(int i = 0; i < arrayLists.size(); i++)
        {
            ArrayList<OptionsLetter> list = getListArrayList().get(i);

            if(listRectangle.get(i).getX()!=0) {

                for (int j = 0; j < list.size(); j++){


                    if(list.get(j).getX()>=listRectangle.get(i).getX() && (list.get(j).getX()<=(listRectangle.get(i).getX()+listRectangle.get(i).getWidth())))
                    {

                        nextX--;

                        w = fm.stringWidth(list.get(j).getLetter());
                        h = fm.getHeight();

                        System.out.println("coordX "+coordX+" carrageX "+list.get(j).getX()+" nextX "+nextX);

                        addListLetter(nextX, new OptionsLetter(list.get(j).getLetter(),coordX,list.get(j).getY(),frame.getPanelList().getType(),frame.getPanelList().getFont(),frame.getPanelList().getSize(),w,h),
                                getElementArrayList(nextY));
                        getElementArrayList(nextY).remove(list.get(j+1));

                        coordX+=w;



                    }


                }

            }

        }

    }
     */



   /* public OptionsLetter get(int i)
    {
        return base.getList();
    }

    public ArrayList<OptionsLetter> getList()
    {
        return listLetter;
    }

    public ArrayList<OptionsLetter> getArrayList(int i)
    {
        return base.getArrayListObject(i);
    }

    public void addListLetter(int next, OptionsLetter letter)
    {
        base.addListLetter(next, letter);
    }

    public int sizeArrayList()
    {
        return base.sizeArrayList();
    }

    public int size()
    {
        return base.size();
    }

    public OptionsLetter get(int i)
    {
        return base.get(i);
    }

    public ArrayList<OptionsLetter> getList()
    {
        return base.getList();
    }

    public void deleteElement(int next)
    {
        base.deleteElement(next);
    }

    public void addArrayList(ArrayList<OptionsLetter> listletter)
    {
        base.addArrayList(listletter);
    }*/




}
