package Controller;

import View.MyFrame;

import java.awt.*;

/**
 * Created by Asus on 27.09.2017.
 */
public class MyThreads implements Runnable{

    private Thread thread;
    private Controller controller;
    boolean isWhite = false;
    MyFrame frame;

    public MyThreads(Controller controller, MyFrame frame)
    {
        this.frame = frame;
        this.controller = controller;
        thread = new Thread(this);
        thread.start();
    }

    public void run()
    {
        while(true) {
            if (isWhite) {
                controller.setColorCarriage(frame.getFrame().getBackground());
                isWhite = false;
            } else {
                controller.setColorCarriage(Color.BLACK);
                isWhite = true;
            }

            try{
            Thread.sleep(450);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
