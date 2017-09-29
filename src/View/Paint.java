package View;

import Controller.Controller;
import Model.OptionsLetter;
import Model.OptionsRectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Asus on 04.09.2017.
 */
public class Paint extends JPanel implements KeyListener {

    private Controller controller;
    private MyFrame frame;
    private boolean isOne = false;
    private boolean isTwo = false;
    private boolean isStart = true;
    private int positionX = 0, zero = 0;
    private PanelList panelList;
    private int coordX = 20, coordY = 70;
    private int startPageX = 20, startPageY = 70, startHeight = 43;
    private int one = 1, two = 2;
    private int nextX = 0, nextY = 0;
    private boolean isShift = false;
    private String s = "";
    private Dimension initialSize = new Dimension(500, 500);
    private FontMetrics fm;

    public Paint(Controller controller, MyFrame frame, PanelList panelList) {
        this.frame = frame;
        this.controller = controller;
        this.panelList = panelList;
        frame.getFrame().addKeyListener(this);
        controller.newListLetter(nextY);
        controller.newListRectangle(nextY);
        panelList.getComboBoxSizeFont().addActionListener(new comboBoxActionListener());
        panelList.getComboBoxFont().addActionListener(new comboBoxActionListener());
        panelList.getComboTypeFont().addActionListener(new comboBoxActionListener());

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);

        Graphics2D gCarriage = (Graphics2D) g.create();
        gCarriage.setColor(controller.getColorCarriage());

        Graphics2D gRectangle = (Graphics2D) g.create();
        gRectangle.setColor(new Color(255,0,0,50));

        if((isStart) || (nextY == zero && controller.getElementArrayList(nextY).size() == zero)) {
            gCarriage.drawLine(startPageX, startPageY, startPageX, startHeight);
        }
        else
        {
            gCarriage.drawLine(zero, zero, zero, zero);
        }

        if(controller.getMaxLength(controller.getListArrayList()).getWidth() >= initialSize.getWidth() ||
                controller.getMaxLength(controller.getListArrayList()).getHeight() > initialSize.getHeight())
        {
            Dimension d = new Dimension(
                    (int)(controller.getMaxLength(controller.getListArrayList()).getWidth()+
                            controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-one).getWidth()+two),
                    (int)(controller.getMaxLength(controller.getListArrayList()).getHeight()+
                            controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-one).getWidth()));
            setPreferredSize(d);
            setSize(d);
        }

        for (int i = 0; i < controller.getListArrayList().size(); i++) {

            ArrayList<OptionsLetter> list = controller.getListArrayList().get(i);

            gRectangle.fillRect(controller.getListRectangle().get(i).getX(),controller.getListRectangle().get(i).getY(),
                    controller.getListRectangle().get(i).getWidth(),controller.getListRectangle().get(i).getHeight());

            for (int j = 0; j < list.size(); j++) {

                g2d.setFont(new Font(list.get(j).getTypeFont(), list.get(j).getFont(), list.get(j).getSize()));

                g2d.drawString(list.get(j).getLetter(), list.get(j).getX(), list.get(j).getY());

                gCarriage.drawLine(controller.getXCarriage(), controller.getYCarriage(), controller.getXCarriage(),
                        controller.getYCarriage() - controller.getHeightList(controller.getListArrayList().get(nextY)));
            }
        }
        frame.getFrame().repaint();
    }

    public void keyPressed(KeyEvent e) {

        int width = 0;
        int height = 0;
        isStart = false;

        Graphics g = getGraphics();

        controller.setGraphics(g);

        g.setFont(new Font(frame.getPanelList().getType(),frame.getPanelList().getFont(),frame.getPanelList().getSize()));

        fm = g.getFontMetrics();

        if(e.getKeyCode()==KeyEvent.VK_SHIFT ) {
            isShift = true;
        }
        else {
           // ArrayList<OptionsLetter> elementArrayList = controller.getElementArrayList(nextY);
            if (e.getKeyCode()==KeyEvent.VK_ENTER)
            {
                nextY++;
                coordX = startPageX;
                coordY+=controller.getHeightLetter();
                controller.setXCarriage(coordX);
                controller.setYCarriage(coordY);

                controller.getListArrayList().add(nextY,controller.newLine(coordX, coordY, nextX, controller.getListArrayList().get(nextY-one),frame));
                controller.getListRectangle().add(nextY,new OptionsRectangle(zero,zero,zero,zero,nextY));

                if(nextX==0) {
                    controller.getListArrayList().get(nextY).add(new OptionsLetter("",coordX,coordY,frame.getPanelList().getType(),
                            frame.getPanelList().getFont(),frame.getPanelList().getSize(),zero,controller.getHeightLetter()));
                }

                if(controller.getElementArrayList(nextY - one).size() == 0)
                {
                    controller.getElementArrayList(nextY - one).add(new OptionsLetter("",coordX,
                            coordY- controller.getElementArrayList(nextY).get(0).getHeight(),frame.getPanelList().getType(),
                            frame.getPanelList().getFont(),frame.getPanelList().getSize(),zero,controller.getHeightLetter()));
                }

                controller.changeParametrs(nextY, controller.getListArrayList());
                coordY = controller.getElementArrayList(nextY-one).get(0).getY()+controller.getMaxHeightList(controller.getElementArrayList(nextY));
                controller.setYCarriage(coordY);
            }

            else {
                //OptionsRectangle optionsRectangle = controller.getListRectangle().get(nextY);
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    nextX++;
                    controller.setXCarriage(coordX);
                    controller.testEmptyLetter(controller.getElementArrayList(nextY));

                    if(controller.getXCarriage()> startPageX && (nextY == zero || nextY != zero)) {
                        coordX -= controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX-one).getWidth();
                    }
                    else if(controller.getXCarriage() == startPageX) {
                        if(nextY==0) {
                            nextX = controller.getElementArrayList(nextY).size();
                        }

                        if((nextX > controller.getElementArrayList(nextY).size() && nextY != zero) ||
                                (nextX == controller.getElementArrayList(nextY).size() && controller.getElementArrayList(nextY).get(0).getLetter() == ""))
                        {
                            nextY--;
                            coordX = controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-one).getX();
                            coordY = controller.getElementArrayList(nextY).get(0).getY();
                            controller.setXCarriage(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-one).getWidth());
                            controller.setYCarriage(coordY);
                            nextX = zero;
                        }
                    }

                    if(e.getKeyCode()==KeyEvent.VK_LEFT && isShift) {

                        isOne = true;

                        if (nextX != zero) {
                            controller.getListRectangle().get(nextY).setY(controller.getElementArrayList(nextY).get(0).getY() -
                                    controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getHeight());
                            controller.getListRectangle().get(nextY).setHeight(controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getHeight());

                            if (positionX >= zero) {
                                controller.getListRectangle().get(nextY).setX(controller.getXCarriage());
                                controller.getListRectangle().get(nextY).setWidth(controller.getListRectangle().get(nextY).getWidth() +
                                        controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getWidth());
                            } else {
                                controller.getListRectangle().get(nextY).setX(controller.getListRectangle().get(nextY).getX());
                                controller.getListRectangle().get(nextY).setWidth(controller.getListRectangle().get(nextY).getWidth() -
                                        controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getWidth());
                            }
                            positionX++;
                        }
                    }

                    else {
                        isOne = false;
                        positionX = 0;
                        controller.getListRectangle().get(nextY).setX(zero);
                        controller.getListRectangle().get(nextY).setY(zero);
                        controller.getListRectangle().get(nextY).setHeight(zero);
                        controller.getListRectangle().get(nextY).setWidth(zero);
                    }
                }

                else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    controller.testEmptyLetter(controller.getElementArrayList(nextY));

                    if (coordX != controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-one).getX()
                            && controller.getXCarriage() >= startPageX) {

                        if(e.getKeyCode()==KeyEvent.VK_RIGHT && isShift) {

                            isTwo = true;
                            controller.getListRectangle().get(nextY).setY(controller.getElementArrayList(nextY).get(0).getY()-
                                    controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getHeight());
                            controller.getListRectangle().get(nextY).setHeight(controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getHeight());

                            if(positionX > zero) {
                                controller.getListRectangle().get(nextY).setX(controller.getListRectangle().get(nextY).getX() +
                                        controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getWidth());
                                controller.getListRectangle().get(nextY).setWidth(controller.getListRectangle().get(nextY).getWidth() -
                                        controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getWidth());
                            }
                            else {
                                controller.getListRectangle().get(nextY).setX(controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getX()- controller.getListRectangle().get(nextY).getWidth());
                                controller.getListRectangle().get(nextY).setWidth(controller.getListRectangle().get(nextY).getWidth()+
                                        controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX).getWidth());
                            }
                            positionX--;
                        }
                        else {
                            isTwo = false;
                            positionX = 0;
                            controller.getListRectangle().get(nextY).setX(zero);
                            controller.getListRectangle().get(nextY).setY(zero);
                            controller.getListRectangle().get(nextY).setHeight(zero);
                            controller.getListRectangle().get(nextY).setWidth(zero);
                        }

                        if(controller.getXCarriage() == startPageX)
                            coordX = startPageX;
                        else {
                            coordX += controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX - one).getWidth();
                        }

                        nextX--;
                        controller.setXCarriage(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX - one).getWidth());
                    }

                    else if(controller.getXCarriage() == 20 && controller.getElementArrayList(nextY).get(0).getLetter() != "")
                    {
                        coordX = startPageX;

                        nextX--;
                        if(controller.getElementArrayList(nextY).size() == one)
                        nextX = zero;
                        controller.setXCarriage(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - nextX - one).getWidth());
                    }

                    else
                    {
                        if(nextY == controller.getListRectangle().size() - one){
                            nextX = zero;
                            coordX = controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-one).getX();
                            controller.setXCarriage(coordX+ controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size()-one).getWidth());
                            coordY = controller.getElementArrayList(nextY).get(0).getY();
                            controller.setYCarriage(coordY);
                        }
                        else {
                            coordX = startPageX;
                            nextY++;
                            coordY = controller.getElementArrayList(nextY).get(0).getY();
                            controller.setXCarriage(coordX);
                            controller.setYCarriage(coordY);

                            if (controller.getElementArrayList(nextY).size() != one)
                                nextX = controller.getElementArrayList(nextY).size();
                            else
                                nextX = zero;
                        }
                    }
                }

                else if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    nextY--;

                    if(nextY >= zero) {

                        nextX = controller.getListArrayList().get(nextY).size() -
                                controller.getListArrayList().get(nextY).indexOf(controller.getPositionXUp(nextY, controller.getXCarriage())) - one;
                        coordX = controller.getPositionXUp(nextY, controller.getXCarriage()).getX();
                        coordY = controller.getListArrayList().get(nextY).get(0).getY();

                        if (coordX == startPageX) {
                            controller.setXCarriage(coordX);

                            if(controller.getElementArrayList(nextY).get(0).getLetter() == "")
                             nextX = zero;
                            else
                                nextX = controller.getElementArrayList(nextY).size();
                        } else
                            controller.setXCarriage(coordX + controller.getPositionXUp(nextY, controller.getXCarriage()).getWidth());

                        controller.setYCarriage(coordY);
                    }
                    else nextY = zero;
                }

                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    nextY++;

                    if(nextY <= controller.getListArrayList().size() - one) {

                        nextX = controller.getListArrayList().get(nextY).size() -
                                controller.getListArrayList().get(nextY).indexOf(controller.getPositionXDown(nextY, controller.getXCarriage())) - one;

                        coordX = controller.getPositionXDown(nextY, controller.getXCarriage()).getX();
                        coordY = controller.getListArrayList().get(nextY).get(0).getY();

                        if (coordX == startPageX) {
                            controller.setXCarriage(coordX);

                            if(controller.getElementArrayList(nextY).get(0).getLetter() == "")
                                nextX = zero;
                            else
                            nextX = controller.getElementArrayList(nextY).size();

                        } else
                            controller.setXCarriage(coordX + controller.getPositionXDown(nextY, controller.getXCarriage()).getWidth());

                        controller.setYCarriage(coordY);
                    }
                    else
                        nextY = controller.getListArrayList().size() - one;
                }

                else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                {
                    controller.test(controller.getElementArrayList(nextY));

                    controller.setXCarriage(coordX);

                    if(controller.getXCarriage() > startPageX && (nextY == zero || nextY != zero)) {
                        controller.deleteElement(nextX, controller.getElementArrayList(nextY));
                        coordX -= controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - one - nextX).getWidth();
                    }
                    else if(controller.getXCarriage() == startPageX) {

                        if (nextY == zero) {
                            if (controller.getElementArrayList(nextY).size() != zero) {
                                if (nextX < controller.getElementArrayList(nextY).size())
                                    controller.deleteElement(nextX, controller.getElementArrayList(nextY));
                            }
                        }

                        if (nextY != zero) {
                            if (controller.getElementArrayList(nextY).size() == nextX) {

                                nextY--;
                                coordX = controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - one).getX();
                                coordY = controller.getElementArrayList(nextY).get(0).getY();

                                controller.setXCarriage(coordX + controller.getElementArrayList(nextY).get(controller.getElementArrayList(nextY).size() - one).getWidth());
                                controller.setYCarriage(coordY);

                                if (nextX != zero)
                                    controller.addLetters(nextX, nextY, controller.getXCarriage(), controller.getYCarriage(), controller.getListArrayList());

                                controller.getListArrayList().remove(controller.getElementArrayList(nextY + one));
                                controller.deleteList(nextY + one, controller.getListArrayList());
                                controller.getListRectangle().remove(controller.getListRectangle().size() - one);

                            } else {

                                controller.getElementArrayList(nextY).add(zero, new OptionsLetter("", startPageX, controller.getElementArrayList(nextY).get(nextX).getY(),
                                            frame.getPanelList().getType(), frame.getPanelList().getFont(), frame.getPanelList().getSize(),
                                            zero, controller.getElementArrayList(nextY).get(nextX).getHeight()));

                                    controller.deleteElement(nextX, controller.getElementArrayList(nextY));
                            }
                        }
                    }

                    if(nextY != zero) {

                        controller.changeParametrs(nextY, controller.getListArrayList());
                        coordY = controller.getElementArrayList(nextY-one).get(0).getY()+controller.getHeightList(controller.getElementArrayList(nextY));
                        controller.setYCarriage(coordY);
                    }
                }
                else{

                    s = e.getKeyText(e.getKeyCode());

                    if(e.getKeyCode()==KeyEvent.VK_SPACE) {
                        s = " ";
                    }
                    else if(e.getKeyCode()==KeyEvent.VK_COMMA) {
                        s = ",";
                    }
                    else if(e.getKeyCode()==KeyEvent.VK_PERIOD) {
                        s = ".";
                    }

                    width = fm.stringWidth(s);
                    height = fm.getHeight();
                    controller.setHeightLetter(height);
                    coordX = controller.getXCarriage();
                    controller.setXCarriage(coordX+width);
                    controller.setYCarriage(coordY);

                    controller.addListLetter(nextX, new OptionsLetter(s,coordX,coordY,frame.getPanelList().getType(),frame.getPanelList().getFont(),frame.getPanelList().getSize(),width,height),
                            controller.getElementArrayList(nextY));

                    controller.testEmptyLetter(controller.getElementArrayList(nextY));

                    if(nextY != zero && ((coordY < controller.getElementArrayList(nextY-one).get(0).getY()+controller.getMaxHeightList(controller.getElementArrayList(nextY)))))
                    {
                        controller.changeParametrs(nextY, controller.getListArrayList());
                        coordY = controller.getElementArrayList(nextY-one).get(0).getY()+controller.getMaxHeightList(controller.getElementArrayList(nextY));
                        controller.setYCarriage(coordY);
                    }



                }
            }
        }
    }

    public  class comboBoxActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(isOne || isTwo) {
                for (int i = 0; i < controller.getListArrayList().size(); i++) {

                    if (controller.getListRectangle().get(i).getX() != zero) {

                        nextX = controller.getN(controller.getListRectangle(), i);

                        if(isOne)
                            coordX = controller.getSelection(controller.getListRectangle(), frame, nextX, i,one);
                        if(isTwo)
                            coordX = controller.getSelection(controller.getListRectangle(), frame, nextX, i,two);

                        if (i != zero) {
                            controller.changeParametrs(i, controller.getListArrayList());

                            coordY = controller.getElementArrayList(i - one).get(0).getY() + controller.getMaxHeightList(controller.getElementArrayList(i));
                            controller.setYCarriage(coordY);
                        }

                        controller.getListRectangle().get(i).setX(controller.getListRectangle().get(i).getX());
                        controller.getListRectangle().get(i).setY(controller.getElementArrayList(i).get(0).getY() -
                                controller.getElementArrayList(i).get(controller.getElementArrayList(i).size() - nextX).getHeight());
                        controller.getListRectangle().get(i).setHeight(controller.getElementArrayList(i).get(controller.getElementArrayList(i).size() - nextX).getHeight());
                        controller.getListRectangle().get(i).setWidth(coordX - controller.getListRectangle().get(i).getX());

                        controller.setXCarriage(coordX);
                        controller.setYCarriage(controller.getElementArrayList(i).get(zero).getY());
                    }
                    positionX = zero;
                }

                frame.getJsp().addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1){

                            isOne = false;
                            isTwo = false;
                            positionX = zero;

                            for(int i = 0; i < controller.getListArrayList().size(); i++) {
                                controller.getListRectangle().get(i).setX(zero);
                                controller.getListRectangle().get(i).setY(zero);
                                controller.getListRectangle().get(i).setHeight(zero);
                                controller.getListRectangle().get(i).setWidth(zero);
                            }

                            if(controller.getElementArrayList(nextY).size() != zero) {
                                controller.setYCarriage(controller.getElementArrayList(nextY).get(0).getY());
                                controller.setXCarriage(startPageX);
                                coordX = startPageX;
                                coordY = controller.getYCarriage();
                                nextX = controller.getElementArrayList(nextY).size();
                            }
                        }
                    }
                });
            }
        }
    }

    public void setSize(Dimension size) {
        super.setSize(size);

        if (initialSize == null) {
            this.initialSize = size;
        }
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);

        if (initialSize == null) {
            this.initialSize = preferredSize;
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