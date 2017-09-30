package Controller;

import Model.OptionsLetter;
import View.MyFrame;

import java.awt.*;
import java.util.List;

/**
 * Created by Asus on 30.09.2017.
 */
public class ButtonText {
    Controller controller;
    MyFrame frame;
    int width = 0;
    int height = 0;
    FontMetrics fm;

    public ButtonText(Controller controller, MyFrame frame)
    {
        this.controller = controller;
        this.frame = frame;
    }

    public void pressText(String s) {

        List<OptionsLetter> list = controller.getElementArrayList(controller.getNextY());

        controller.setIsStart(false);

        controller.getGraphics().setFont(new Font(frame.getPanelList().getType(),frame.getPanelList().getFont(),frame.getPanelList().getSize()));

        fm = controller.getGraphics().getFontMetrics();

        width = fm.stringWidth(s);
        height = fm.getHeight();
        controller.setHeightLetter(height);
        controller.setCoordX(controller.getXCarriage());
        controller.setXCarriage(controller.getCoordX()+width);
        controller.setYCarriage(controller.getCoordY());

        controller.addListLetter(controller.getNextX(), new OptionsLetter(s,controller.getCoordX(),controller.getCoordY(),
                frame.getPanelList().getType(), frame.getPanelList().getFont(),frame.getPanelList().getSize(),width,height), list);

        controller.testEmptyLetter(list);

        if(controller.getNextY() != 0 && ((controller.getCoordY() < controller.getElementArrayList(controller.getNextY()-1).get(0).getY() +
                controller.getMaxHeightList(list))))
        {
            controller.changeParametrs(controller.getNextY(), controller.getListArrayList());
            controller.setCoordY(controller.getElementArrayList(controller.getNextY()-1).get(0).getY()+controller.getMaxHeightList(list));
            controller.setYCarriage(controller.getCoordY());
        }
    }
}
