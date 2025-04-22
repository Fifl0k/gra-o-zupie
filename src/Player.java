import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Player {

    private int playerWidth = 50, playerHeight = 50;
    private int positionX = 0, positionY =0;
    private int speed = 5;

    private Set<Integer> pressedKeys = new HashSet<>();




    private double velocityY = 0;
    private final double gravity = 0.5;
    private final double maxFallSpeed = 10;
    private boolean onGround = false;

    public Player(int positionX, int positionY, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.playerWidth = width;
        this.playerHeight = height;
    }

    public void update(List<Platform> platforms, int panelHeight, int panelWidth){

        if (pressedKeys.contains(KeyEvent.VK_LEFT)||pressedKeys.contains(KeyEvent.VK_A)) {
            positionX -= speed;
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)||pressedKeys.contains(KeyEvent.VK_D)) {
            positionX += speed;
        }
        if ((pressedKeys.contains(KeyEvent.VK_W )|| pressedKeys.contains((KeyEvent.VK_SPACE))) && onGround) {
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
        if (positionY + playerHeight >= panelHeight) {
            positionY = panelHeight - playerHeight;
            velocityY = 0;
            onGround = true;
        } else {
            onGround = false;
        }
        if(positionX +playerWidth >= panelWidth){
            positionX = panelWidth - playerWidth;

        }
    }


    protected void draw(Graphics g) {


        g.setColor(Color.RED);


        g.fillRect(positionX, positionY, playerWidth, playerHeight);

    }




    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }



}
