package View;


import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Created by Asus on 02.09.2017.
 */
public class MyFrame implements MouseWheelListener, MouseMotionListener, MouseListener {

    private Dimension d;
    private String title;
    private Controller controller;
    PanelList panelList;

    private JFrame frame = new JFrame();

    JPanel panel = new JPanel();
    JScrollPane jsp;
    String s;


    public MyFrame(String title, Dimension d, Controller controller)
    {
        this.title = title;
        this.d = d;
        this.controller = controller;
        panelList = new PanelList(controller,MyFrame.this);

       // paintPanel = new PaintPanel(MyFrame.this);
       // jsp = new JScrollPane(paintPanel);
    }

    public void init()
    {
        //JFrame frame = new RunKeyboard(700,700,controller);

        frame.setTitle(title);
        frame.setSize(d);
        //frame.setSize(Toolkit.getDefaultToolkit ().getScreenSize ());
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new Paint(controller,MyFrame.this,panelList);

        //panel.setBackground(Color.green);
        panel.setPreferredSize(new Dimension(500,500));

        jsp = new JScrollPane(panel);

        jsp.addMouseWheelListener(this);
        jsp.addMouseMotionListener(this);
        jsp.addMouseListener(this);


        frame.add(jsp,BorderLayout.CENTER);

        frame.add(panelList.getPanel(), BorderLayout.NORTH);

        frame.setVisible(true);


    }

    public JFrame getFrame()
    {
        return frame;
    }

    public PanelList getPanelList()
    {
        return panelList;
    }

   /* public void generation()
    {
        frame.repaint();
    }*/


    public void mouseWheelMoved(MouseWheelEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("click");
        frame.requestFocusInWindow();
    }

    public void mousePressed(MouseEvent e) {


    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}






/*public class MyFrame extends JFrame {

    private Rect rect;

    private static final int STEP = 10;
    private static final String ACTION_UP = "up";
    private static final String ACTION_DOWN = "down";
    private static final String ACTION_LEFT = "left";
    private static final String ACTION_RIGHT = "right";
    private static final String ACTION_C = "c";

    public MyFrame() {
        super("Component Move Test");
        JPanel cp = new JPanel(null);
        rect = new Rect();
        rect.setBackground(Color.red);
        cp.add(rect);
        JRootPane rp = getRootPane();
        // регистрируем обработчики действий
        rp.getActionMap().put(ACTION_LEFT, new LeftMoveAction());
        rp.getActionMap().put(ACTION_RIGHT, new RightMoveAction());
        rp.getActionMap().put(ACTION_UP, new UpMoveAction());
        rp.getActionMap().put(ACTION_C, new cMoveAction());

        rp.getActionMap().put(ACTION_DOWN, new DownMoveAction());
        // получаем InputMap самого верхнего уровня (у root pane)
        // для случая, когда фокус где-то в окне
        InputMap im = rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        // связываем нажатия клавиш с зарегистрированными действиями
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), ACTION_LEFT);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), ACTION_RIGHT);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), ACTION_UP);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), ACTION_C);

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), ACTION_DOWN);
        setContentPane(cp);
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        rect.requestFocus();
    }

    private class UpMoveAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            rect.setLocation(rect.getX(), rect.getY() - STEP);
        }
    }

    private class cMoveAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

            rect.setLocation(rect.getX(), rect.getY() - STEP);
        }
    }

    private class DownMoveAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            rect.setLocation(rect.getX(), rect.getY() + STEP);
        }
    }

    private class LeftMoveAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            rect.setLocation(rect.getX() - STEP, rect.getY());
        }
    }

    private class RightMoveAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            rect.setLocation(rect.getX() + STEP, rect.getY());
        }
    }

    private class Rect extends JPanel {
        public Rect() {
            setSize(100, 100);
            setOpaque(true);
        }
    }
}*/
