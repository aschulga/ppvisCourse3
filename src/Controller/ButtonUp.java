package Controller;

import Model.OptionsLetter;

import java.util.List;

/**
 * Created by Asus on 30.09.2017.
 */
public class ButtonUp {
    Controller controller;
    int startPageX = 20;

    public ButtonUp(Controller controller)
    {
        this.controller = controller;
    }

    public void pressUp() {

        controller.setNextY(controller.getNextY()-1);

        List<OptionsLetter> list = controller.getElementArrayList(controller.getNextY());

        if(controller.getNextY() >= 0) {

            controller.setNextX(list.size() - list.indexOf(controller.getPositionXUp(controller.getNextY(), controller.getXCarriage())) - 1);
            controller.setCoordX(controller.getPositionXUp(controller.getNextY(), controller.getXCarriage()).getX());
            controller.setCoordY(list.get(0).getY());

            if (controller.getCoordX() == startPageX) {
                controller.setXCarriage(controller.getCoordX());

                if(list.get(0).getLetter() == "")
                    controller.setNextX(0);
                else
                    controller.setNextX(list.size());
            } else
                controller.setXCarriage(controller.getCoordX() + controller.getPositionXUp(controller.getNextY(), controller.getXCarriage()).getWidth());

            controller.setYCarriage(controller.getCoordY());
        }
        else controller.setNextY(0);
    }
}
