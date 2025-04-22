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

    public void update(List<Platform> platforms, int panelHeight, int panelWidth) {
        int prevX = positionX;
        int prevY = positionY;

        // Ruch poziomy
        if (pressedKeys.contains(KeyEvent.VK_LEFT) || pressedKeys.contains(KeyEvent.VK_A)) {
            positionX -= speed;
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT) || pressedKeys.contains(KeyEvent.VK_D)) {
            positionX += speed;
        }

        // Grawitacja i skok
        if ((pressedKeys.contains(KeyEvent.VK_W) || pressedKeys.contains(KeyEvent.VK_SPACE)) && onGround) {
            velocityY = -10;
            onGround = false;
        }
        velocityY += gravity;
        if (velocityY > maxFallSpeed) velocityY = maxFallSpeed;
        positionY += velocityY;

        Rectangle playerBounds = new Rectangle(positionX, positionY, playerWidth, playerHeight);
        onGround = false;

        for (Platform platform : platforms) {
            Rectangle platBounds = platform.getBounds();
            if (playerBounds.intersects(platBounds)) {
                Rectangle intersection = playerBounds.intersection(platBounds);

                // Sprawdzamy kierunek kolizji
                if (prevY + playerHeight <= platBounds.y) {
                    // Kolizja od góry (lądowanie)
                    positionY = platBounds.y - playerHeight;
                    velocityY = 0;
                    onGround = true;
                } else if (prevY >= platBounds.y + platBounds.height) {
                    // Kolizja od dołu (sufit)
                    positionY = platBounds.y + platBounds.height;
                    velocityY = 0;
                } else if (prevX + playerWidth <= platBounds.x) {
                    // Kolizja z lewej strony
                    positionX = platBounds.x - playerWidth;
                } else if (prevX >= platBounds.x + platBounds.width) {
                    // Kolizja z prawej strony
                    positionX = platBounds.x + platBounds.width;
                }

                // Aktualizuj playerBounds po poprawkach
                playerBounds = new Rectangle(positionX, positionY, playerWidth, playerHeight);
            }
        }

        // Ograniczenia ekranu
        if (positionY + playerHeight >= panelHeight) {
            positionY = panelHeight - playerHeight;
            velocityY = 0;
            onGround = true;
        }
        if (positionX < 0) positionX = 0;
        if (positionX + playerWidth > panelWidth) positionX = panelWidth - playerWidth;
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
