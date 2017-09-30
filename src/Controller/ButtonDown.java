package Controller;

import Model.OptionsLetter;

import java.util.List;

/**
 * Created by Asus on 30.09.2017.
 */
public class ButtonDown {

    Controller controller;

    public ButtonDown(Controller controller)
    {
        this.controller = controller;
    }

    public void pressDown() {

        controller.setNextY(controller.getNextY()+1);
        List<OptionsLetter> list = controller.getElementArrayList(controller.getNextY());

        if(controller.getNextY() <= controller.getListArrayList().size() - 1) {

            controller.setNextX(list.size() - list.indexOf(controller.getPositionXDown(controller.getNextY(), controller.getXCarriage())) - 1);
            controller.setCoordX(controller.getPositionXDown(controller.getNextY(), controller.getXCarriage()).getX());
            controller.setCoordY(list.get(0).getY());

            if (controller.getCoordX() == 20) {
                controller.setXCarriage(controller.getCoordX());

                if(list.get(0).getLetter() == "")
                    controller.setNextX(0);
                else
                    controller.setNextX(list.size());

            } else
            controller.setXCarriage(controller.getCoordX() + controller.getPositionXDown(controller.getNextY(), controller.getXCarriage()).getWidth());

            controller.setYCarriage(controller.getCoordY());
        }
        else
            controller.setNextY(controller.getListArrayList().size() - 1);

    }
}
