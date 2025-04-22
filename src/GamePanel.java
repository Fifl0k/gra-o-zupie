import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel implements KeyListener {

    private Player player;
    private List<Platform> platforms = new ArrayList<>();
    private Timer timer;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);

        player = new Player(100,100,40,40);

        platforms.add(new Platform(100, 300, 200, 20));
        platforms.add(new Platform(400, 200, 150, 20));
        platforms.add(new Platform(250, 400, 300, 20));
        timer = new Timer(8, e ->{
            player.update( platforms,getHeight(), getWidth());
            repaint();
        });
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.draw(g);

        for (Platform p : platforms) {
            p.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
