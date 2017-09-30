package Controller;

import Model.OptionsLetter;
import Model.OptionsRectangle;
import View.MyFrame;

/**
 * Created by Asus on 29.09.2017.
 */
public class ButtonEnter {

    Controller controller;
    MyFrame frame;
    int startPageX = 20;

    public ButtonEnter(Controller controller, MyFrame frame)
    {
        this.controller = controller;
        this.frame = frame;
    }

    public void pressEnter()
    {
        controller.setNextY(controller.getNextY()+1);
        controller.setCoordX(startPageX);
        controller.setCoordY(controller.getCoordY()+controller.getHeightLetter());
        controller.setXCarriage(controller.getCoordX());
        controller.setYCarriage(controller.getCoordY());


        controller.getListArrayList().add(controller.getNextY(),controller.newLine(controller.getCoordX(),
                controller.getCoordY(), controller.getNextX(), controller.getListArrayList().get(controller.getNextY()-1),frame));
        controller.getListRectangle().add(controller.getNextY(),new OptionsRectangle(0,0,0,0,controller.getNextY()));

        if(controller.getNextX()==0) {
            controller.getListArrayList().get(controller.getNextY()).add(new OptionsLetter("",controller.getCoordX(),
                    controller.getCoordY(),frame.getPanelList().getType(),
                    frame.getPanelList().getFont(),frame.getPanelList().getSize(),0,controller.getHeightLetter()));
        }

        if(controller.getElementArrayList(controller.getNextY() - 1).size() == 0)
        {
            controller.getElementArrayList(controller.getNextY() - 1).add(new OptionsLetter("",controller.getCoordX(),
                    controller.getCoordY() - controller.getElementArrayList(controller.getNextY()).get(0).getHeight(),frame.getPanelList().getType(),
                    frame.getPanelList().getFont(),frame.getPanelList().getSize(),0,controller.getHeightLetter()));
        }

        controller.changeParametrs(controller.getNextY(), controller.getListArrayList());
        controller.setCoordY(controller.getElementArrayList(controller.getNextY()-1).get(0).getY()+
                controller.getMaxHeightList(controller.getElementArrayList(controller.getNextY())));
        controller.setYCarriage(controller.getCoordY());
    }

}
