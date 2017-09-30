package View;

import Controller.*;
import Model.OptionsLetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Created by Asus on 04.09.2017.
 */
public class Paint extends JPanel implements KeyListener {

    private Controller controller;
    private MyFrame frame;
    private boolean isShift = false;
    private PanelList panelList;
    private int startPageX = 20, startPageY = 70, startHeight = 43;
    private String s = "";
    private Dimension initialSize = new Dimension(500, 500);

    public Paint(Controller controller, MyFrame frame, PanelList panelList) {
        this.frame = frame;
        this.controller = controller;
        this.panelList = panelList;
        frame.getFrame().addKeyListener(this);
        controller.newListLetter(controller.getNextX());
        controller.newListRectangle(controller.getNextY());
        panelList.getComboBoxSizeFont().addActionListener(new comboBoxActionListener());
        panelList.getComboBoxFont().addActionListener(new comboBoxActionListener());
        panelList.getComboTypeFont().addActionListener(new comboBoxActionListener());

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<OptionsLetter> listLetters = controller.getElementArrayList(controller.getNextY());

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);

        Graphics2D gCarriage = (Graphics2D) g.create();
        gCarriage.setColor(controller.getColorCarriage());

        Graphics2D gRectangle = (Graphics2D) g.create();
        gRectangle.setColor(new Color(255,0,0,50));

        if((controller.getIsStart()) || (controller.getNextY() == 0 && listLetters.size() == 0)) {
            gCarriage.drawLine(startPageX, startPageY, startPageX, startHeight);
        }
        else
        {
            gCarriage.drawLine(0, 0, 0, 0);
        }

        if(controller.getMaxLength(controller.getListArrayList()).getWidth() >= initialSize.getWidth() ||
                controller.getMaxLength(controller.getListArrayList()).getHeight() > initialSize.getHeight())
        {
            Dimension d = new Dimension(
                    (int)(controller.getMaxLength(controller.getListArrayList()).getWidth()+ listLetters.get(listLetters.size()-1).getWidth()+2),
                    (int)(controller.getMaxLength(controller.getListArrayList()).getHeight()+ listLetters.get(listLetters.size()-1).getWidth()));
            setPreferredSize(d);
            setSize(d);
        }

        for (int i = 0; i < controller.getListArrayList().size(); i++) {

            List<OptionsLetter> list = controller.getListArrayList().get(i);

            gRectangle.fillRect(controller.getListRectangle().get(i).getX(),controller.getListRectangle().get(i).getY(),
                    controller.getListRectangle().get(i).getWidth(),controller.getListRectangle().get(i).getHeight());

            for (int j = 0; j < list.size(); j++) {

                g2d.setFont(new Font(list.get(j).getTypeFont(), list.get(j).getFont(), list.get(j).getSize()));

                g2d.drawString(list.get(j).getLetter(), list.get(j).getX(), list.get(j).getY());

                gCarriage.drawLine(controller.getXCarriage(), controller.getYCarriage(), controller.getXCarriage(),
                        controller.getYCarriage() - controller.getHeightList(controller.getListArrayList().get(controller.getNextY())));
            }
        }
        frame.getFrame().repaint();
    }

    public void keyPressed(KeyEvent e) {

        Graphics g = getGraphics();
        controller.setGraphics(g);

        if(e.getKeyCode()==KeyEvent.VK_SHIFT ) {
            isShift = true;
        }

        else if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            ButtonEnter buttonEnter = new ButtonEnter(controller, frame);
            buttonEnter.pressEnter();
        }

        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            ButtonLeft buttonLeft = new ButtonLeft(controller);
            buttonLeft.pressLeft();

            if (e.getKeyCode() == KeyEvent.VK_LEFT && isShift) {
                ButtonLeftShift buttonLeftShift = new ButtonLeftShift(controller);
                buttonLeftShift.pressLeftShift();

            } else {
                controller.setIsOne(false);
                controller.setPositionX(0);
                controller.getListRectangle().get(controller.getNextY()).setX(0);
                controller.getListRectangle().get(controller.getNextY()).setY(0);
                controller.getListRectangle().get(controller.getNextY()).setHeight(0);
                controller.getListRectangle().get(controller.getNextY()).setWidth(0);
            }
        }

        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && isShift) {

                ButtonRightShift buttonRightShift = new ButtonRightShift(controller);
                buttonRightShift.pressRightShift();

            } else {
                controller.setIsTwo(false);
                controller.setPositionX(0);
                controller.getListRectangle().get(controller.getNextY()).setX(0);
                controller.getListRectangle().get(controller.getNextY()).setY(0);
                controller.getListRectangle().get(controller.getNextY()).setHeight(0);
                controller.getListRectangle().get(controller.getNextY()).setWidth(0);
            }

            ButtonRight buttonRight = new ButtonRight(controller);
            buttonRight.pressButtonRight();
        }

        else if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            ButtonUp buttonUp = new ButtonUp(controller);
            buttonUp.pressUp();
        }

        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            ButtonDown buttonDown = new ButtonDown(controller);
            buttonDown.pressDown();
        }

        else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
        {
            ButtonBackSpace buttonBackSpace = new ButtonBackSpace(controller, frame);
            buttonBackSpace.pressBackSpace();
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

            ButtonText buttonText = new ButtonText(controller,frame);
            buttonText.pressText(s);

            }
        }

    public  class comboBoxActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Click click = new Click(controller,frame);
            click.pressMouse();
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
