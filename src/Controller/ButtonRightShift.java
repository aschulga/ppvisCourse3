package Controller;

import Model.OptionsLetter;
import Model.OptionsRectangle;

import java.util.List;

/**
 * Created by Asus on 30.09.2017.
 */
public class ButtonRightShift {
    Controller controller;
    int startPageX = 20;

    public ButtonRightShift(Controller controller)
    {
        this.controller = controller;
    }

    public void pressRightShift() {

        OptionsRectangle optionsRectangle = controller.getListRectangle().get(controller.getNextY());
        List<OptionsLetter> list = controller.getElementArrayList(controller.getNextY());
        controller.testEmptyLetter(list);

        if (controller.getCoordX() != list.get(list.size()-1).getX() && controller.getXCarriage() >= startPageX) {

            controller.setIsTwo(true);
            optionsRectangle.setY(list.get(0).getY()- list.get(list.size() - controller.getNextX()).getHeight());
            optionsRectangle.setHeight(list.get(list.size() - controller.getNextX()).getHeight());

            if(controller.getPositionX() > 0) {
                optionsRectangle.setX(optionsRectangle.getX() + list.get(list.size() - controller.getNextX()).getWidth());
                optionsRectangle.setWidth(optionsRectangle.getWidth() - list.get(list.size() - controller.getNextX()).getWidth());
                }
                else {
                optionsRectangle.setX(list.get(list.size() - controller.getNextX()).getX()- optionsRectangle.getWidth());
                optionsRectangle.setWidth(optionsRectangle.getWidth()+ list.get(list.size() - controller.getNextX()).getWidth());
                }
            controller.setPositionX(controller.getPositionX()-1);
            }
    }
}
