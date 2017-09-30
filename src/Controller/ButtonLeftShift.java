package Controller;

import Model.OptionsLetter;
import Model.OptionsRectangle;

import java.util.List;

/**
 * Created by Asus on 29.09.2017.
 */
public class ButtonLeftShift {

    Controller controller;

    public ButtonLeftShift(Controller controller)
    {
        this.controller = controller;
    }

    public void pressLeftShift()
    {
        OptionsRectangle optionsRectangle = controller.getListRectangle().get(controller.getNextY());
        List<OptionsLetter> list = controller.getElementArrayList(controller.getNextY());

        controller.setIsOne(true);

        if (controller.getNextX() != 0) {
            optionsRectangle.setY(list.get(0).getY() - list.get(list.size() - controller.getNextX()).getHeight());
            optionsRectangle.setHeight(list.get(list.size() - controller.getNextX()).getHeight());

            if (controller.getPositionX() >= 0) {
                optionsRectangle.setX(controller.getXCarriage());
                optionsRectangle.setWidth(optionsRectangle.getWidth() + list.get(list.size() - controller.getNextX()).getWidth());
            } else {
                optionsRectangle.setX(optionsRectangle.getX());
                optionsRectangle.setWidth(optionsRectangle.getWidth() - list.get(list.size() - controller.getNextX()).getWidth());
            }
            controller.setPositionX(controller.getPositionX()+1);
        }
    }

}
