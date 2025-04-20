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

    private double velocityY = 0;
    private final double gravity = 0.5;
    private final double maxFallSpeed = 10;
    private boolean onGround = false;

    public Player(){
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        timer = new Timer(8, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer();
            }
        });
        timer.start();
    }

    private void movePlayer(){

        if (pressedKeys.contains(KeyEvent.VK_LEFT)||pressedKeys.contains(KeyEvent.VK_A)) {
            positionX -= speed;
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)||pressedKeys.contains(KeyEvent.VK_D)) {
            positionX += speed;
        }
        if (pressedKeys.contains(KeyEvent.VK_W) && onGround) {
            velocityY = -10;
            onGround = false;
        }

        // GRAWITACJA
        if (!onGround) {
            velocityY += gravity;
            if (velocityY > maxFallSpeed) {
                velocityY = maxFallSpeed;
            }
        }

        positionY +=  velocityY;

        // "Podłoga" – zatrzymaj spadanie
        if (positionY + playerHeight >= getHeight()) {
            positionY = getHeight() - playerHeight;
            velocityY = 0;
            onGround = true;
        } else {
            onGround = false;
        }





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
        if ((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_SPACE) && onGround) {
            velocityY = -10; // wartość skoku (ujemna, bo do góry)
            onGround = false;
        }
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
