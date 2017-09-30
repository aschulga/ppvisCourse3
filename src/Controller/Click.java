package Controller;

import Model.OptionsLetter;
import Model.OptionsRectangle;
import View.MyFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by Asus on 30.09.2017.
 */
public class Click {

    Controller controller;
    MyFrame frame;
    int left = 1, right = 2;
    int startPageX = 20;

    public Click(Controller controller, MyFrame frame)
    {
        this.controller = controller;
        this.frame = frame;
    }

    public void pressMouse() {
        if(controller.getIsOne() || controller.getIsTwo()) {
            for (int i = 0; i < controller.getListArrayList().size(); i++) {

                List<OptionsLetter> list = controller.getElementArrayList(i);
                OptionsRectangle optionsRectangle = controller.getListRectangle().get(i);

                if (controller.getListRectangle().get(i).getX() != 0) {

                    controller.setNextX(controller.getN(controller.getListRectangle(), i));

                    if(controller.getIsOne())
                        controller.setCoordX(controller.getSelection(controller.getListRectangle(), frame, controller.getNextX(), i,left));
                    if(controller.getIsTwo())
                        controller.setCoordX(controller.getSelection(controller.getListRectangle(), frame, controller.getNextX(), i,right));

                    if (i != 0) {
                        controller.changeParametrs(i, controller.getListArrayList());

                        controller.setCoordY(controller.getElementArrayList(i - 1).get(0).getY() + controller.getMaxHeightList(list));
                        controller.setYCarriage(controller.getCoordY());
                    }

                    optionsRectangle.setX(optionsRectangle.getX());
                    optionsRectangle.setY(list.get(0).getY() - list.get(list.size() - controller.getNextX()).getHeight());
                    optionsRectangle.setHeight(list.get(list.size() - controller.getNextX()).getHeight());
                    optionsRectangle.setWidth(controller.getCoordX() - optionsRectangle.getX());

                    controller.setXCarriage(controller.getCoordX());
                    controller.setYCarriage(list.get(0).getY());
                }
                controller.setPositionX(0);
            }

            frame.getJsp().addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1){

                        controller.setIsOne(false);
                        controller.setIsTwo(false);
                        controller.setPositionX(0);

                        for(int i = 0; i < controller.getListArrayList().size(); i++) {
                            controller.getListRectangle().get(i).setX(0);
                            controller.getListRectangle().get(i).setY(0);
                            controller.getListRectangle().get(i).setHeight(0);
                            controller.getListRectangle().get(i).setWidth(0);
                        }

                        if(controller.getElementArrayList(controller.getNextY()).size() != 0) {
                            controller.setYCarriage(controller.getElementArrayList(controller.getNextY()).get(0).getY());
                            controller.setXCarriage(startPageX);
                            controller.setCoordX(startPageX);
                            controller.setCoordY(controller.getYCarriage());
                            controller.setNextX(controller.getElementArrayList(controller.getNextY()).size());
                        }
                    }
                }
            });
        }
    }
}
