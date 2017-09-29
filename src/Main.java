import Controller.Controller;
import Model.Document;
import View.MyFrame;

import java.awt.*;

/**
 * Created by Asus on 02.09.2017.
 */
public class Main {
    public static void main(String args[])
    {
        Document base = new Document();
        Controller controller = new Controller(base);

        MyFrame frame = new MyFrame("Текстовый редактор", new Dimension(700,700),controller);
        frame.init();
    }
}
