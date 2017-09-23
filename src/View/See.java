package View;

import java.awt.event.KeyEvent;

/**
 * Created by Asus on 16.09.2017.
 */
public class See {


    public void keyPressed(KeyEvent e) {

    /*    int width = 0;
        int height = 0;

        Graphics g = getGraphics();

        //System.out.println(controller.getListArrayList().get(0));

        g.setFont(new Font(frame.getPanelList().getType(),frame.getPanelList().getFont(),frame.getPanelList().getSize()));

        //g.setFont(new Font("Arial", PLAIN,30));

        fm = g.getFontMetrics();

        isButton = true;

        if(e.getKeyCode()==KeyEvent.VK_SPACE) {
            s = " ";
            coordX+=textWidth;
        }
        else if(e.getKeyCode()==KeyEvent.VK_COMMA) {
            s = ",";
            // coordX+=textWidth;
        }
        else if(e.getKeyCode()==KeyEvent.VK_PERIOD) {
            s = ".";
            coordX+=textWidth;
        }
        else if(e.getKeyCode()==KeyEvent.VK_SHIFT ) {

            isShift = true;
        }

        else if(e.getKeyCode()==KeyEvent.VK_1 && isShift) {

            nextX++;

            carriage.setX(coordX);

            if(carriage.getX()> 20 && (nextY == 0 || nextY !=0)) {
                coordX -= controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX-1).getWidth();
            }

            controller.getSelection(coordX,coordY,frame,nextY,nextX,controller.getListArrayList());

            // System.out.println("nextX "+nextX);
        }

        else if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            nextY++;

            coordX = 20;
            coordY += controller.getHeightList();

            carriage.setX(coordX);
            carriage.setY(coordY);


            controller.getListArrayList().add(nextY,controller.newLine(coordX, coordY, nextX, controller.getListArrayList().get(nextY-1),frame));


            if(controller.getListArrayList().get(nextY).size()==0) {
                controller.getListArrayList().get(nextY).add(new OptionsLetter("",coordX,coordY,frame.getPanelList().getType(),
                        frame.getPanelList().getFont(),frame.getPanelList().getSize(),0,controller.getMaxHeight()));//27
                // nextX++;
            }


            System.out.println("size "+controller.getElementArrayList(0).size());

            if(controller.getElementArrayList(nextY - 1).size()==0)
            {
                controller.getElementArrayList(nextY - 1).add(new OptionsLetter("",coordX,coordY-controller.getElementArrayList(nextY).get(0).getHeight(),frame.getPanelList().getType(),
                        frame.getPanelList().getFont(),frame.getPanelList().getSize(),0,controller.getElementArrayList(nextY).get(0).getHeight()));
            }

            controller.changeParametrs(nextY, controller.getListArrayList());

            //    System.out.println(controller.getListArrayList().size());

            maxHeight = 0;



        }

        else if(e.getKeyCode() == KeyEvent.VK_1)
        {
            nextX++;

            carriage.setX(coordX);

            if(carriage.getX()> 20 && (nextY == 0 || nextY !=0)) {
                coordX -= controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX-1).getWidth();
                //  carriage.setX(coordX);
            }
            else if(carriage.getX() == 20)
            {
                if(nextY==0)
                {
                    nextX = controller.getElementArrayList(nextY).size();
                }

                if(nextX >= controller.getElementArrayList(nextY).size() && nextY!=0)
                {
                    nextY--;
                    coordX = controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-1).getX();
                    coordY = controller.getElementArrayList(nextY).get(0).getY();

                    carriage.setX(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-1).getWidth());
                    carriage.setY(coordY);

                    nextX = 0;

                }
                maxHeight = 0;
            }

            System.out.println(controller.getListArrayList().get(nextY).size());
        }

        else if(e.getKeyCode() == KeyEvent.VK_2)
        {

            if(carriage.getX() == 20) {

                carriage.setX(coordX + controller.getElementArrayList(nextY).get(0).getWidth());
                coordX = controller.getElementArrayList(nextY).get(0).getX();
                nextX = controller.getElementArrayList(nextY).size() - 1;

                if(carriage.getX() == 20 && controller.getElementArrayList(nextY).size() > 1)
                {
                    carriage.setX(coordX + controller.getElementArrayList(nextY).get(1).getWidth());
                    //   coordX = controller.getElementArrayList(nextY).get(1).getX();
                    nextX = controller.getElementArrayList(nextY).size() - 2;
                }
                else if(carriage.getX() == 20 && controller.getElementArrayList(nextY).size() == 1)
                {
                    nextY++;

                    coordX = 20;


                    if(nextY<controller.getListArrayList().size()) {
                        coordY = controller.getElementArrayList(nextY).get(0).getY();

                        carriage.setX(coordX);
                        carriage.setY(coordY);

                        nextX = controller.getElementArrayList(nextY).size();
                        maxHeight = 0;
                    }
                    else
                        nextY--;
                }

                //  }

            }
            else if(nextX <= 0 && nextY!= controller.getListArrayList().size()-1)
            {
                nextY++;

                coordX = 20;

                coordY = controller.getElementArrayList(nextY).get(0).getY();

                carriage.setX(coordX);
                carriage.setY(coordY);

                nextX = controller.getElementArrayList(nextY).size();
                maxHeight = 0;
            }
            else {
                if (coordX != controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-1).getX()) {
                    coordX += controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX - 1).getWidth();

                    nextX--;

                    carriage.setX(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX - 1).getWidth());
                }
            }
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
                    nextX = controller.getElementArrayList(nextY).size();
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

            carriage.setX(coordX);

            if(carriage.getX()> 20 && (nextY == 0 || nextY !=0)) {
                controller.deleteElement(nextX,controller.getElementArrayList(nextY));
                coordX -= controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - 1 - nextX).getWidth();
            }
            else if(carriage.getX() == 20)
            {
                if(nextY == 0)
                {
                    if(controller.getElementArrayList(nextY).size() !=0) {
                        //   System.out.println("n "+nextX);///////////
                        controller.deleteElement(nextX, controller.getElementArrayList(nextY));

                    }
                }

                if(nextY!=0)
                {

                    //   System.out.println("nextX " + nextX);
                    if(controller.getElementArrayList(nextY).size() == 1)
                        nextX = 0;
                    controller.deleteElement(nextX,controller.getElementArrayList(nextY));


                    if(controller.getElementArrayList(nextY).size()==nextX)
                    {

                        if(nextX == 0) {
                            controller.getListArrayList().remove(controller.getElementArrayList(nextY));
                            controller.deleteList(nextY, controller.getListArrayList());
                        }

                        nextY--;

                        coordX = controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-1).getX();
                        coordY = controller.getElementArrayList(nextY).get(0).getY();

                        carriage.setX(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-1).getWidth());
                        carriage.setY(coordY);

                        if(nextX != 0) {

                            controller.addLetters(nextX, nextY, carriage.getX(), carriage.getY(), controller.getListArrayList());

                            controller.getListArrayList().remove(controller.getElementArrayList(nextY + 1));
                            controller.deleteList(nextY + 1, controller.getListArrayList());
                        }
                        // nextX = 0;

                    }


                }

                /*else {
                    nextY--;

                    coordX = controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - 1).getX();
                    coordY = controller.getElementArrayList(nextY).get(0).getY();

                    carriage.setX(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - 1).getWidth());
                    carriage.setY(coordY);

                    //nextX = 0;

                    controller.addLetters(nextX, nextY, carriage.getX(), carriage.getY(), controller.getListArrayList());

                    controller.getListArrayList().remove(controller.getElementArrayList(nextY + 1));
                    controller.deleteList(nextY + 1, controller.getListArrayList());

                    System.out.println(controller.getElementArrayList(nextY).size());


                }*/

      /*      }

            // System.out.println("size " + controller.getElementArrayList(nextY).size());

        }
        else{
            s = e.getKeyText(e.getKeyCode());

            width = fm.stringWidth(s);

            height = fm.getHeight();

            if(height >= maxHeight)
                maxHeight = height;

            controller.setHeightList(height);

            controller.setMaxHeight(maxHeight);

            coordX = carriage.getX();

            carriage.setX(coordX+width);
            carriage.setY(coordY);

            controller.addListLetter(nextX, new OptionsLetter(s,coordX,coordY,frame.getPanelList().getType(),frame.getPanelList().getFont(),frame.getPanelList().getSize(),width,height),
                    controller.getElementArrayList(nextY));

            /*if(nextY!=0 && ((coordY < controller.getElementArrayList(nextY-1).get(0).getY()+maxHeight))
                    && controller.getElementArrayList(nextY-1).size()!=0) {
                controller.newSize(maxHeight, nextY, controller.getListArrayList());
                coordY = controller.getElementArrayList(nextY-1).get(0).getY()+maxHeight;
               // carriage.setY(coordY);
            }*/

     /*       if(nextY!=0 && controller.getMinHeightList(controller.getElementArrayList(nextY)) == controller.getMaxHeightList(controller.getElementArrayList(nextY)));
            System.out.println("qwerty "+controller.getMaxHeightList(controller.getElementArrayList(nextY)));


            // carriage.setY(coordY);




            System.out.println("maxHeight - "+height);
        }


    }


    */

    }
}
