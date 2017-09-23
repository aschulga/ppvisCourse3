package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Asus on 04.09.2017.
 */
public class PanelList {
    JComboBox comboBoxTypeFont = new JComboBox();

    JComboBox comboBoxSizeFont = new JComboBox();

    JComboBox comboBoxFont = new JComboBox();

    Controller controller;

    MyFrame frame;

    boolean isB = false;

    public PanelList(Controller controller, MyFrame frame)
    {
        this.controller = controller;
        this.frame = frame;
    }

    public JPanel getPanel()
    {
        JPanel panel = new JPanel();

        comboBoxTypeFont.addItem("Serif");
        comboBoxTypeFont.addItem("Dialog");
        comboBoxTypeFont.addItem("Arial");

        comboBoxSizeFont.addItem("20");
        comboBoxSizeFont.addItem("36");
        comboBoxSizeFont.addItem("48");

        comboBoxFont.addItem("BOLD");
        comboBoxFont.addItem("ITALIC");
        comboBoxFont.addItem("PLAIN");

        comboBoxTypeFont.addActionListener(new comboBoxTypeFontActionListener());
        comboBoxSizeFont.addActionListener(new comboBoxSizeFontActionListener());
        comboBoxFont.addActionListener(new comboBoxFontActionListener());

        panel.add(comboBoxTypeFont);
        panel.add(comboBoxSizeFont);
        panel.add(comboBoxFont);

        return panel;
    }

    public String getType()
    {
        return comboBoxTypeFont.getSelectedItem().toString();
    }

    public int getSize()
    {
        return Integer.parseInt(comboBoxSizeFont.getSelectedItem().toString());
    }

    public int getFont() {
        if (comboBoxFont.getSelectedItem().toString() == "PLAIN")
            return 0;
        else if (comboBoxFont.getSelectedItem().toString() == "ITALIC")
            return 2;
        else
            return 1;
    }

    public class comboBoxTypeFontActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //label.setFont(new Font((String) comboBox.getSelectedItem(), Font.PLAIN,DEFAULT_SIZE));
            System.out.println(getType());
            setB(true);
        }
    }

    public class comboBoxSizeFontActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //label.setFont(new Font((String) comboBox.getSelectedItem(), Font.PLAIN,DEFAULT_SIZE));
            System.out.println(getSize());
            setB(true);




            /*if(getB()) {
                controller.getSelection(controller.getListRectangle().get(0), controller.getElementArrayList(0), frame, 2);


                controller.getListRectangle().get(0).setX(0);
                controller.getListRectangle().get(0).setY(0);
                controller.getListRectangle().get(0).setHeight(0);
                controller.getListRectangle().get(0).setWidth(0);
                setB(false);
                System.out.println("t");
            }*/
        }
    }

    public class comboBoxFontActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //label.setFont(new Font((String) comboBox.getSelectedItem(), Font.PLAIN,DEFAULT_SIZE));
            System.out.println(getFont());
            setB(true);
        }
    }

    public boolean getB()
    {
        return isB;
    }

    public void setB(boolean isB)
    {
        this.isB = isB;
    }




}


