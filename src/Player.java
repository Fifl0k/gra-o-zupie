import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class Player extends JPanel implements KeyListener {


    private int playerWidth = 0, playerHeight = 0;
    private int positionX = 0, positionY =0;
    private int speed = 5;
    private Set<Integer> pressedKeys = new HashSet<>();
    Timer timer;


    public Player(){
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer();
            }
        });
        timer.start();
    }

    private void movePlayer(){
        if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
            positionX -= speed;
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            positionX += speed;
        }
        if (pressedKeys.contains(KeyEvent.VK_UP)) {
            positionY -= speed;
        }
        if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
            positionY += speed;
        }
        positionX = Math.max(0, Math.min(getWidth() - playerWidth, positionX));
        positionY = Math.max(0, Math.min(getHeight() - playerHeight, positionY));

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);



        g.setColor(Color.RED);


        g.fillRect(positionX, positionY, playerWidth, playerHeight);

    }

    @Override
    public void keyPressed(KeyEvent e) {

        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }



    public void setPlayerWidth(int playerWidth) {
        this.playerWidth = playerWidth;
    repaint();
    }

    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    repaint();
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    repaint();
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
        repaint();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    repaint();
    }
}
