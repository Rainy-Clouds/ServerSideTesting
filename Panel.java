import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;

public class Panel extends JPanel implements Runnable
{
    private static final int width = 800;
    private static final int height = 600; 

    private Game game = new Game("title");

    private boolean running = false;
    private Thread thread;
    private int fps = 60;
    private long time = 1000 / fps;

    public Panel()
    {
        this.setPreferredSize(new Dimension(width, height));
        start();
    }

    private void start()
    {
        running = true;
        thread = new Thread(this);
        thread.start();
        setFocusable(true);
        requestFocus();
    } 

    @Override
    public void run()
    {
        long start;
        long elapsed;
        long wait;
        while(running)
        {
            start = System.nanoTime();

            tick();
            repaint();

            elapsed = System.nanoTime() - start;
            wait = time - elapsed / 1000000;

            if(wait <= 0)
            {
                wait = 5;
            }

            try
            {
                Thread.sleep(wait);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void tick()
    {
        game.update();
    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);

        game.render(g);
    }
}
