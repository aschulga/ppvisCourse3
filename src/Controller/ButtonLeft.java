package Controller;

import Model.OptionsLetter;

import java.util.List;

/**
 * Created by Asus on 29.09.2017.
 */
public class ButtonLeft {

    Controller controller;
    int startPageX = 20;

    public ButtonLeft(Controller controller)
    {
        this.controller = controller;
    }

    public void pressLeft()
    {
        List<OptionsLetter> list = controller.getElementArrayList(controller.getNextY());
        controller.setNextX(controller.getNextX()+1);
        controller.setXCarriage(controller.getCoordX());
        controller.testEmptyLetter(list);

        if(controller.getXCarriage()> startPageX && (controller.getNextY() == 0 || controller.getNextY() != 0)) {
            controller.setCoordX(controller.getCoordX() - list.get(list.size() - controller.getNextX()-1).getWidth());
        }
        else if(controller.getXCarriage() == startPageX) {
            if(controller.getNextY()==0) {
                controller.setNextX(list.size());
            }

            if((controller.getNextX() > list.size() && controller.getNextY() != 0) ||
                    (controller.getNextX() == list.size() && list.get(0).getLetter() == ""))
            {
                controller.setNextY(controller.getNextY()-1);

                controller.setCoordX(controller.getElementArrayList(controller.getNextY()).get(
                        controller.getElementArrayList(controller.getNextY()).size()-1).getX());
                controller.setCoordY(controller.getElementArrayList(controller.getNextY()).get(0).getY());
                controller.setXCarriage(controller.getCoordX() + controller.getElementArrayList(
                        controller.getNextY()).get(controller.getElementArrayList(controller.getNextY()).size()-1).getWidth());
                controller.setYCarriage(controller.getCoordY());
                controller.setNextX(0);
            }
        }
    }
}
