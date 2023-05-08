import java.awt.*;

public class Game 
{
    private String state;

    private TitleManager title = new TitleManager();

    public Game(String state)
    {
        this.state = state; 
    }

    public void update()
    {
        if(state.equals("title"))
        {
            title.update();
        }
    }

    public void render(Graphics g)
    {
        if(state.equals("title"))
        {
            title.render(g);
        }
    }
}
