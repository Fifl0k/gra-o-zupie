import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Player {

    private int playerWidth = 50, playerHeight = 50;
    private int positionX = 0, positionY = 0;
    private int speed = 5;

    private Set<Integer> pressedKeys = new HashSet<>();

    private double velocityY = 0;
    private final double gravity = 0.5;
    private final double maxFallSpeed = 10;
    private boolean onGround = false;

    private int tileSize;
    private int panelWidth, panelHeight;

    public Player(int positionX, int positionY, int width, int height, int tileSize, int panelWidth, int panelHeight) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.playerWidth = width;
        this.playerHeight = height;
        this.tileSize = tileSize;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;


    }


    public void update(LevelMap map) {
        // Sterowanie poziome
        int moveX = 0;
        if (pressedKeys.contains(KeyEvent.VK_LEFT) || pressedKeys.contains(KeyEvent.VK_A)) {
            moveX -= speed;
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT) || pressedKeys.contains(KeyEvent.VK_D)) {
            moveX += speed;
        }

        if (moveX != 0) {
            int top = positionY / tileSize;
            int bottom = (positionY + playerHeight - 1) / tileSize;

            if (moveX > 0) { // Przemieszczanie się w prawo
                int right = (positionX + playerWidth - 1 + moveX) / tileSize;
                boolean collision = false;
                for (int y = top; y <= bottom; y++) {
                    if (map.isSolid(right, y)) {
                        collision = true;
                        break;
                    }
                }
                if (!collision) {
                    positionX += moveX;
                } else {
                    positionX = right * tileSize - playerWidth;
                }
            } else { // Przemieszczanie się w lewo
                int left = (positionX + moveX) / tileSize;
                boolean collision = false;
                for (int y = top; y <= bottom; y++) {
                    if (map.isSolid(left, y)) {
                        collision = true;
                        break;
                    }
                }
                if (!collision) {
                    positionX += moveX;
                } else {
                    positionX = (left + 1) * tileSize;
                }
            }
        }

        // Skok
        if ((pressedKeys.contains(KeyEvent.VK_W) || pressedKeys.contains(KeyEvent.VK_SPACE)) && onGround) {
            velocityY = -10;
            onGround = false;
        }

        // Grawitacja
        velocityY += gravity;
        if (velocityY > maxFallSpeed) {
            velocityY = maxFallSpeed;
        }

        double newY = positionY + velocityY;


        // Kolizja - spadanie
        if (velocityY > 0) {
            int bottom = (int) (newY + playerHeight) / tileSize;
            int left = (int) (positionX) / tileSize;
            int right = (int) (positionX + playerWidth - 1) / tileSize;

            for (int x = left; x <= right; x++) {
                if (map.isSolid(x, bottom)) {
                    newY = bottom * tileSize - playerHeight;
                    velocityY = 0;
                    onGround = true;
                    break;
                } else {
                    onGround = false;
                }

            }
        }


        if(positionX<0){
            positionX = 0;
        }

        // Kolizja - skok w sufit
        else if (velocityY < 0) {
            int top = (int) (newY) / tileSize;
            int left = (int) (positionX) / tileSize;
            int right = (int) (positionX + playerWidth - 1) / tileSize;

            for (int x = left; x <= right; x++) {
                if (map.isSolid(x, top)) {
                    newY = (top + 1) * tileSize;
                    velocityY = 0;
                    break;
                }
            }
        }

        // Aktualizacja pozycji
        positionY = (int) newY;

    }
    public void draw(Graphics g) {
        g.setColor(Color.RED); // Ustawienie koloru
        g.fillRect(positionX, positionY, playerWidth, playerHeight); // Rysowanie prostokąta jako postaci
    }


    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
