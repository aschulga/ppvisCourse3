package Controller;

import Model.OptionsLetter;
import View.MyFrame;

import java.util.List;


/**
 * Created by Asus on 30.09.2017.
 */
public class ButtonBackSpace {

    Controller controller;
    MyFrame frame;
    int startPageX = 20;

    public ButtonBackSpace(Controller controller, MyFrame frame)
    {
        this.controller = controller;
        this.frame = frame;
    }

    public void pressBackSpace() {

        List<OptionsLetter> list = controller.getElementArrayList(controller.getNextY());
        controller.test(list);
        controller.setXCarriage(controller.getCoordX());

        if(controller.getXCarriage() > startPageX && (controller.getNextY() == 0 || controller.getNextY() != 0)) {
            controller.deleteElement(controller.getNextX(), list);
            controller.setCoordX(controller.getCoordX()-list.get(list.size() - 1 - controller.getNextX()).getWidth());
        }
        else if(controller.getXCarriage() == startPageX) {

            if (controller.getNextY() == 0) {
                if (list.size() != 0) {
                    if (controller.getNextX() < list.size())
                        controller.deleteElement(controller.getNextX(), list);
                }
            }

            if (controller.getNextY() != 0) {
                if (list.size() == controller.getNextX()) {

                    controller.setNextY(controller.getNextY()-1);

                    List<OptionsLetter> listElements = controller.getElementArrayList(controller.getNextY());

                    controller.setCoordX(listElements.get(listElements.size() - 1).getX());
                    controller.setCoordY(listElements.get(0).getY());

                    controller.setXCarriage(controller.getCoordX() + listElements.get(listElements.size() - 1).getWidth());
                    controller.setYCarriage(controller.getCoordY());

                    if (controller.getNextX() != 0)
                        controller.addLetters(controller.getNextX(), controller.getNextY(), controller.getXCarriage(),
                                controller.getYCarriage(), controller.getListArrayList());

                    controller.getListArrayList().remove(controller.getElementArrayList(controller.getNextY() + 1));
                    controller.deleteList(controller.getNextY() + 1, controller.getListArrayList());
                    controller.getListRectangle().remove(controller.getListRectangle().size() - 1);

                } else {

                    List<OptionsLetter> elementArrayList = controller.getElementArrayList(controller.getNextY());

                    elementArrayList.add(0, new OptionsLetter("", startPageX, elementArrayList.get(controller.getNextX()).getY(),
                            frame.getPanelList().getType(), frame.getPanelList().getFont(), frame.getPanelList().getSize(),
                            0, elementArrayList.get(controller.getNextX()).getHeight()));

                    controller.deleteElement(controller.getNextX(), elementArrayList);
                }
            }
        }

        if(controller.getNextY() != 0) {

            controller.changeParametrs(controller.getNextY(), controller.getListArrayList());
            controller.setCoordY(controller.getElementArrayList(controller.getNextY()-1).get(0).getY()+
                    controller.getHeightList(controller.getElementArrayList(controller.getNextY())));
            controller.setYCarriage(controller.getCoordY());
        }
    }
}
