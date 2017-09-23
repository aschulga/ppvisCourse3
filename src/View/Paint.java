package View;

import Controller.Controller;
import Model.OptionsCarriage;
import Model.OptionsLetter;
import Model.OptionsRectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Asus on 04.09.2017.
 */
public class Paint extends JPanel implements KeyListener {

    private Controller controller;
    String s = "";
    MyFrame frame;
    OptionsCarriage carriage = new OptionsCarriage(20, 70);
    OptionsRectangle rectangle = new OptionsRectangle(0,0,0,0,0);

    int positionX = 0;
    int positionY = 0;

    PanelList panelList;

    int maxHeight = 0;

    int coordX, coordY;

    int nextX = 0, nextY = 0, prev = 0;

    boolean isShift = false;

    boolean t = false;

    private Dimension initialSize = new Dimension(100, 100);

    //int coordX, coordY;

    FontMetrics fm;

    public Paint(Controller controller, MyFrame frame, PanelList panelList) {
        this.frame = frame;
        this.controller = controller;
        this.panelList = panelList;
        coordX = 20;
        coordY = 70;
        frame.getFrame().addKeyListener(this);
        controller.newListLetter(nextY);
        controller.newListRectangle(nextY);

    }

    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        if (initialSize == null) {
            this.initialSize = preferredSize;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.BLACK);


        for (int i = 0; i < controller.getListArrayList().size(); i++) {

            ArrayList<OptionsLetter> list = controller.getListArrayList().get(i);

            g2d.drawRect(controller.getListRectangle().get(i).getX(),controller.getListRectangle().get(i).getY(),
                    controller.getListRectangle().get(i).getWidth(),controller.getListRectangle().get(i).getHeight());


            for (int j = 0; j < list.size(); j++) {

                g2d.setFont(new Font(list.get(j).getTypeFont(), list.get(j).getFont(), list.get(j).getSize()));

                g2d.drawString(list.get(j).getLetter(), list.get(j).getX(), list.get(j).getY());

                g2d.drawLine(carriage.getX(), carriage.getY(), carriage.getX(), carriage.getY() - controller.getHeightLetter());





                frame.getFrame().repaint();
            }
        }

       /* for(int z = 0; z < controller.getListRectangle().size(); z++)
        {
            g2d.drawRect(controller.getListRectangle().get(z).getX(),controller.getListRectangle().get(z).getY(),
                    controller.getListRectangle().get(z).getWidth(),controller.getListRectangle().get(z).getHeight());
        }*/
    }

    public void keyPressed(KeyEvent e) {

        int width = 0;
        int height = 0;


        Graphics g = getGraphics();

        //System.out.println(controller.getListArrayList().get(0));

        g.setFont(new Font(frame.getPanelList().getType(),frame.getPanelList().getFont(),frame.getPanelList().getSize()));

        //g.setFont(new Font("Arial", PLAIN,30));

        fm = g.getFontMetrics();

        controller.setFm(fm);


        if(e.getKeyCode()==KeyEvent.VK_SPACE) {
            s = " ";
        }
        else if(e.getKeyCode()==KeyEvent.VK_COMMA) {
            s = ",";
            // coordX+=textWidth;
        }
        else if(e.getKeyCode()==KeyEvent.VK_PERIOD) {
            s = ".";
        }
        else if(e.getKeyCode()==KeyEvent.VK_SHIFT ) {

            isShift = true;
        }

        else if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
          //  int f = controller.n(nextX,carriage.getX(),controller.getElementArrayList(nextY)).getHeight();



            nextY++;

            //System.out.println("s "+f);

            //controller.setCoordX(20);

            coordX = 20;

            //coordY += controller.getHeightLetter();

            //controller.setCoordY(controller.getCoordY()+controller.getHeightLetter());

            coordY+=controller.getHeightLetter();

            carriage.setX(coordX);
            carriage.setY(coordY);


            controller.getListArrayList().add(nextY,controller.newLine(coordX, coordY, nextX, controller.getListArrayList().get(nextY-1),frame));

            controller.getListRectangle().add(nextY,new OptionsRectangle(0,0,0,0,nextY));



            if(nextX==0) {
                controller.getListArrayList().get(nextY).add(new OptionsLetter("",coordX,coordY,frame.getPanelList().getType(),
                        frame.getPanelList().getFont(),frame.getPanelList().getSize(),0,controller.getHeightLetter()));//27
                // nextX++;
            }


           // System.out.println("size "+controller.getElementArrayList(0).size());

            if(controller.getElementArrayList(nextY - 1).size()==0)
            {
                controller.getElementArrayList(nextY - 1).add(new OptionsLetter("",coordX,coordY-controller.getElementArrayList(nextY).get(0).getHeight(),frame.getPanelList().getType(),
                        frame.getPanelList().getFont(),frame.getPanelList().getSize(),0,controller.getHeightLetter()));
            }

            controller.changeParametrs(nextY, controller.getListArrayList());

            coordY = controller.getElementArrayList(nextY-1).get(0).getY()+controller.getMaxHeightList(controller.getElementArrayList(nextY));

            //controller.setCoordY(controller.getElementArrayList(nextY-1).get(0).getY()+controller.getMaxHeightList(controller.getElementArrayList(nextY)));
            carriage.setY(coordY);

          //  System.out.println("nn "+nextX);

            //    System.out.println(controller.getListArrayList().size());

            maxHeight = 0;


            /*controller.newSize(controller.getMaxHeightList(controller.getElementArrayList(nextY)), nextY, controller.getListArrayList());
            coordY = controller.getElementArrayList(nextY-1).get(0).getY()+controller.getMaxHeightList(controller.getElementArrayList(nextY));
            carriage.setY(coordY);*/

        }


        else if(e.getKeyCode() == KeyEvent.VK_1)
        {
            nextX++;

            carriage.setX(coordX);

            controller.testEmptyLetter(controller.getElementArrayList(nextY));

            if(carriage.getX()> 20 && (nextY == 0 || nextY !=0)) {
                coordX -= controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX-1).getWidth();
                //controller.setCoordX(controller.getCoordX()-controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX-1).getWidth());
            }
            else if(carriage.getX() == 20)
            {
                if(nextY==0)
                {
                    nextX = controller.getElementArrayList(nextY).size();
                }

                if(nextX > controller.getElementArrayList(nextY).size() && nextY!=0)
                {

                    nextY--;
                   // controller.setCoordX(controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-1).getX());
                  //  controller.setCoordY(controller.getElementArrayList(nextY).get(0).getY());

                    coordX = controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-1).getX();
                    coordY = controller.getElementArrayList(nextY).get(0).getY();

                    carriage.setX(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-1).getWidth());
                    carriage.setY(coordY);

                    nextX = 0;

                }

                if(nextX == controller.getElementArrayList(nextY).size() && controller.getElementArrayList(nextY).get(0).getLetter()=="")
                {
                    nextY--;
                    //controller.setCoordX(controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-1).getX());
                   // controller.setCoordY(controller.getElementArrayList(nextY).get(0).getY());

                    coordX = controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-1).getX();
                    coordY = controller.getElementArrayList(nextY).get(0).getY();

                    carriage.setX(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-1).getWidth());
                    carriage.setY(coordY);

                    nextX = 0;
                }
                maxHeight = 0;

                System.out.println(controller.getListRectangle().size());

            }



            if(e.getKeyCode()==KeyEvent.VK_1 && isShift) {

                if (nextX!=0) {

                    controller.getListRectangle().get(nextY).setY(controller.getElementArrayList(nextY).get(0).getY() - controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getHeight());
                    controller.getListRectangle().get(nextY).setHeight(controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getHeight());

                    if (positionX >= 0) {
                        controller.getListRectangle().get(nextY).setX(carriage.getX());
                        controller.getListRectangle().get(nextY).setWidth(controller.getListRectangle().get(nextY).getWidth() + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getWidth());
                    } else {
                        controller.getListRectangle().get(nextY).setX(controller.getListRectangle().get(nextY).getX());
                        controller.getListRectangle().get(nextY).setWidth(controller.getListRectangle().get(nextY).getWidth() - controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getWidth());

                    }


                    positionX++;

                }


            }

            else {

                controller.getSelection(controller.getListRectangle().get(nextY), controller.getElementArrayList(nextY), frame, nextX);

                if(nextY!=0)
                {
                    controller.changeParametrs(nextY, controller.getListArrayList());

                    coordY = controller.getElementArrayList(nextY - 1).get(0).getY() + controller.getMaxHeightList(controller.getElementArrayList(nextY));
                    carriage.setY(coordY);
                }

                controller.getListRectangle().get(nextY).setX(0);
                controller.getListRectangle().get(nextY).setY(0);
                controller.getListRectangle().get(nextY).setHeight(0);
                controller.getListRectangle().get(nextY).setWidth(0);

               // positionX = 0;
            }
        }



        else if(e.getKeyCode() == KeyEvent.VK_2)
        {
            controller.testEmptyLetter(controller.getElementArrayList(nextY));

            if (coordX != controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-1).getX()
                    && carriage.getX()>=20) {

                if(e.getKeyCode()==KeyEvent.VK_2 && isShift) {
                    //  if (rectangle.getWidth() >= 0) {

                    controller.getListRectangle().get(nextY).setY(controller.getElementArrayList(nextY).get(0).getY()-controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getHeight());
                    controller.getListRectangle().get(nextY).setHeight(controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getHeight());

                    if(positionX>0) {
                        controller.getListRectangle().get(nextY).setX(controller.getListRectangle().get(nextY).getX() + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getWidth());
                        controller.getListRectangle().get(nextY).setWidth(controller.getListRectangle().get(nextY).getWidth() - controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getWidth());
                    }else {
                        controller.getListRectangle().get(nextY).setX(controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getX()-controller.getListRectangle().get(nextY).getWidth());
                        controller.getListRectangle().get(nextY).setWidth(controller.getListRectangle().get(nextY).getWidth()+controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getWidth());
                    }
                    /*    } else {
                    rectangle.setX(controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getX()-rectangle.getWidth());
                    rectangle.setY(controller.getElementArrayList(nextY).get(0).getY()-controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getHeight());
                    rectangle.setHeight(controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getHeight());
                    rectangle.setWidth(rectangle.getWidth()+controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getWidth());

                    }*/

                    //System.out.println("x " + rectangle.getX() + " y " + rectangle.getY() + " height " + rectangle.getHeight() + " width " + rectangle.getWidth());

                    positionX--;
                }
                else
                    positionX = 0;

                if(carriage.getX() == 20)
                    coordX = 20;
                else {
                    coordX += controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX - 1).getWidth();

                }

                nextX--;

                carriage.setX(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX - 1).getWidth());
            }

            else if(carriage.getX() == 20 && controller.getElementArrayList(nextY).get(0).getLetter()!="")
            {
                coordX = 20;

                nextX--;

                carriage.setX(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX - 1).getWidth());

            }

            else
            {
                nextY++;

                coordX = 20;

                coordY = controller.getElementArrayList(nextY).get(0).getY();

                carriage.setX(coordX);
                carriage.setY(coordY);

                //nextX=0
                if(controller.getElementArrayList(nextY).size()!=1)
                nextX = controller.getElementArrayList(nextY).size();
                else
                    nextX=0;
            }
           // System.out.println(controller.getListArrayList().get(nextY).size());
        }



        else if(e.getKeyCode() == KeyEvent.VK_3)
        {
            nextY--;

            if(nextY>=0) {

                nextX = controller.getListArrayList().get(nextY).size() -
                        controller.getListArrayList().get(nextY).indexOf(controller.getPositionXUp(nextY, carriage.getX())) - 1;

                coordX = controller.getPositionXUp(nextY, carriage.getX()).getX();
                coordY = controller.getListArrayList().get(nextY).get(0).getY();


                if (coordX == 20) {
                    carriage.setX(coordX);


                    if(controller.getElementArrayList(nextY).get(0).getLetter()=="")
                     nextX = 0;
                    else
                        nextX = controller.getElementArrayList(nextY).size();

                } else
                    carriage.setX(coordX + controller.getPositionXUp(nextY, carriage.getX()).getWidth());//position xCarriage

                carriage.setY(coordY);
            }
            else nextY=0;
            maxHeight = 0;
        }

        else if(e.getKeyCode() == KeyEvent.VK_4)
        {
            nextY++;

            if(nextY <= controller.getListArrayList().size()-1) {

                nextX = controller.getListArrayList().get(nextY).size() -
                        controller.getListArrayList().get(nextY).indexOf(controller.getPositionXDown(nextY, carriage.getX())) - 1;

                coordX = controller.getPositionXDown(nextY, carriage.getX()).getX();
                coordY = controller.getListArrayList().get(nextY).get(0).getY();

                if (coordX == 20) {
                    carriage.setX(coordX);


                    if(controller.getElementArrayList(nextY).get(0).getLetter()=="")
                        nextX = 0;
                    else
                    nextX = controller.getElementArrayList(nextY).size();
                    // nextX = 0;

                } else
                    carriage.setX(coordX + controller.getPositionXDown(nextY, carriage.getX()).getWidth());//position xCarriage

                carriage.setY(coordY);
            }
            else
                nextY = controller.getListArrayList().size()-1;
            maxHeight = 0;

        }

        else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
        {
            controller.test(controller.getElementArrayList(nextY));

            carriage.setX(coordX);

            if(carriage.getX()> 20 && (nextY == 0 || nextY !=0)) {
                controller.deleteElement(nextX,controller.getElementArrayList(nextY));
                coordX -= controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - 1 - nextX).getWidth();
            }
            else if(carriage.getX() == 20) {
                if (nextY == 0) {
                    if (controller.getElementArrayList(nextY).size() != 0) {
                        if (nextX < controller.getElementArrayList(nextY).size())
                            controller.deleteElement(nextX, controller.getElementArrayList(nextY));

                    }
                }

                if (nextY != 0) {
                    if (controller.getElementArrayList(nextY).size() == nextX) {

                        nextY--;

                        coordX = controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - 1).getX();
                        coordY = controller.getElementArrayList(nextY).get(0).getY();

                        carriage.setX(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - 1).getWidth());
                        carriage.setY(coordY);

                        if (nextX != 0)
                            controller.addLetters(nextX, nextY, carriage.getX(), carriage.getY(), controller.getListArrayList());

                        controller.getListArrayList().remove(controller.getElementArrayList(nextY + 1));
                        controller.deleteList(nextY + 1, controller.getListArrayList());

                    } else
                        controller.deleteElement(nextX, controller.getElementArrayList(nextY));

                }

            }


            if(nextY!=0) {

                controller.changeParametrs(nextY, controller.getListArrayList());

                coordY = controller.getElementArrayList(nextY-1).get(0).getY()+controller.getMaxHeightList(controller.getElementArrayList(nextY));
                carriage.setY(coordY);
              /*  controller.newSize(controller.getMaxHeightList(controller.getElementArrayList(nextY)), nextY, controller.getListArrayList());
                coordY = controller.getElementArrayList(nextY - 1).get(0).getY() + controller.getMaxHeightList(controller.getElementArrayList(nextY));
                carriage.setY(coordY);*/
            }


        }
        else{

            s = e.getKeyText(e.getKeyCode());

            width = fm.stringWidth(s);

            height = fm.getHeight();

            if(height >= maxHeight)
                maxHeight = height;

            controller.setHeightLetter(height);

            controller.setMaxHeight(maxHeight);

            coordX = carriage.getX();

            carriage.setX(coordX+width);
            carriage.setY(coordY);

            controller.addListLetter(nextX, new OptionsLetter(s,coordX,coordY,frame.getPanelList().getType(),frame.getPanelList().getFont(),frame.getPanelList().getSize(),width,height),
                    controller.getElementArrayList(nextY));

            controller.testEmptyLetter(controller.getElementArrayList(nextY));

            if(nextY!=0 && ((coordY < controller.getElementArrayList(nextY-1).get(0).getY()+controller.getMaxHeightList(controller.getElementArrayList(nextY)))))
                   // && controller.getElementArrayList(nextY-1).size()!=0) {
            {
               /* controller.newSize(controller.getMaxHeightList(controller.getElementArrayList(nextY)), nextY, controller.getListArrayList());
                coordY = controller.getElementArrayList(nextY-1).get(0).getY()+controller.getMaxHeightList(controller.getElementArrayList(nextY));
                carriage.setY(coordY);*/

                controller.changeParametrs(nextY, controller.getListArrayList());
                coordY = controller.getElementArrayList(nextY-1).get(0).getY()+controller.getMaxHeightList(controller.getElementArrayList(nextY));
                carriage.setY(coordY);
            }



           // System.out.println("maxHeight - "+height);
        }


    }



    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_SHIFT ) {

            isShift = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        frame.getFrame().repaint();
    }
}













/*

else if(e.getKeyCode() == KeyEvent.VK_1)
        {
            next++;

            carriage.setX(coordX);

            coordX -= controller.getListArrayList().get(controller.getListArrayList().size()-1).get(controller.getListLetters().size()-next-1).getWidth();

            //coordX -= controller.getListLetters().get(controller.getListLetters().size()-next-1).getWidth();

          //  System.out.println(next);
        }
        else if(e.getKeyCode() == KeyEvent.VK_2)
        {

            coordX += controller.getListLetters().get(controller.getListLetters().size()-next-1).getWidth();
            next--;
            carriage.setX(coordX+controller.getListLetters().get(controller.getListLetters().size()-next-1).getWidth());

          //  System.out.println(next);
        }

        else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
        {

          //  System.out.println("size " + (controller.getListLetters().size()-1));

            controller.deleteElement(next,controller.getListArrayList().get(controller.getListArrayList().size()-1));

            carriage.setX(coordX);

            coordX -= controller.getListLetters().get(controller.getListLetters().size() - 1 - next).getWidth();
        }


*/

