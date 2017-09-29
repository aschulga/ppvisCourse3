package View;


import Controller.Controller;
import Controller.MyThreads;

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
    private PanelList panelList;
    private JFrame frame = new JFrame();
    private JScrollPane jsp;

    public MyFrame(String title, Dimension d, Controller controller)
    {
        this.title = title;
        this.d = d;
        this.controller = controller;
        panelList = new PanelList(controller,MyFrame.this);
    }

    public void init()
    {
        frame.setTitle(title);
        frame.setSize(d);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new Paint(controller,MyFrame.this,panelList);
        panel.setPreferredSize(new Dimension(500,500));
        jsp = new JScrollPane(panel);

        jsp.addMouseWheelListener(this);
        jsp.addMouseMotionListener(this);
        jsp.addMouseListener(this);

        frame.add(jsp,BorderLayout.CENTER);
        frame.add(panelList.getPanel(), BorderLayout.NORTH);

        new MyThreads(controller, MyFrame.this);

        frame.setVisible(true);
    }

    public JScrollPane getJsp()
    {
        return jsp;
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public PanelList getPanelList()
    {
        return panelList;
    }


    public void mouseWheelMoved(MouseWheelEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
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
