package Controller;

import Model.OptionsLetter;

import java.util.List;

/**
 * Created by Asus on 30.09.2017.
 */
public class ButtonRight {

    Controller controller;
    int startPageX = 20;

    public ButtonRight(Controller controller)
    {
        this.controller = controller;
    }

    public void pressButtonRight()
    {
        List<OptionsLetter> list = controller.getElementArrayList(controller.getNextY());
        controller.testEmptyLetter(list);

        if (controller.getCoordX() != list.get(list.size()-1).getX() && controller.getXCarriage() >= startPageX) {

            if(controller.getXCarriage() == startPageX)
            controller.setCoordX(startPageX);
            else {
                controller.setCoordX(controller.getCoordX()+list.get(list.size() - controller.getNextX() - 1).getWidth());
            }

            controller.setNextX(controller.getNextX()-1);
            controller.setXCarriage(controller.getCoordX() + list.get(list.size() - controller.getNextX() - 1).getWidth());
        }
        else if(controller.getXCarriage() == startPageX && list.get(0).getLetter() != "")
        {
            controller.setCoordX(startPageX);

            controller.setNextX(controller.getNextX()-1);
            if(list.size() == 1)
                controller.setNextX(0);

            controller.setXCarriage(controller.getCoordX() + list.get(list.size() - controller.getNextX() - 1).getWidth());
        }
        else
        {
            if(controller.getNextY() == controller.getListRectangle().size() - 1){
                controller.setNextX(0);
                controller.setCoordX(list.get(list.size()-1).getX());
                controller.setXCarriage(controller.getCoordX()+ list.get(list.size()-1).getWidth());
                controller.setCoordY(list.get(0).getY());
                controller.setYCarriage(controller.getCoordY());
            }
            else {
                controller.setCoordX(startPageX);
                controller.setNextY(controller.getNextY()+1);
                controller.setCoordY(controller.getElementArrayList(controller.getNextY()).get(0).getY());
                controller.setXCarriage(controller.getCoordX());
                controller.setYCarriage(controller.getCoordY());

                if (controller.getElementArrayList(controller.getNextY()).size() != 1)
                    controller.setNextX(controller.getElementArrayList(controller.getNextY()).size());
                else
                    controller.setNextX(0);
            }
        }
    }

}
