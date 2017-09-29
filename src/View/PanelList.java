package View;

import Controller.Controller;

import javax.swing.*;

/**
 * Created by Asus on 04.09.2017.
 */
public class PanelList {

    private JComboBox comboBoxTypeFont = new JComboBox();
    private JComboBox comboBoxSizeFont = new JComboBox();
    private JComboBox comboBoxFont = new JComboBox();
    private Controller controller;
    private MyFrame frame;
    private int zero = 0, one = 1, two = 2;

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
            return zero;
        else if (comboBoxFont.getSelectedItem().toString() == "ITALIC")
            return two;
        else
            return one;
    }

    public JComboBox getComboBoxSizeFont()
    {
        return comboBoxSizeFont;
    }

    public JComboBox getComboBoxFont()
    {
        return comboBoxFont;
    }

    public JComboBox getComboTypeFont()
    {
        return comboBoxTypeFont;
    }

}


