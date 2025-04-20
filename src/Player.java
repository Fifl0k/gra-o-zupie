import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class Player extends JPanel implements KeyListener {


    private int playerWidth = 0, playerHeight = 0;
    private int positionX = 0, positionY =0;



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);



        g.setColor(Color.RED);


        g.fillRect(positionX, positionY, playerWidth, playerHeight);

    }
Timer timer;
    public Player(){
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

         timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
         timer.start();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        int speed = 5; // jak szybko się porusza

        switch (key) {
            case KeyEvent.VK_LEFT:
                positionX -= speed;
                break;
            case KeyEvent.VK_RIGHT:
                positionX += speed;
                break;
            case KeyEvent.VK_UP:
                positionY -= speed;
                break;
            case KeyEvent.VK_DOWN:
                positionY += speed;
                break;
        }

        repaint(); // odśwież panel po każdej zmianie
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public void setPlayerWidth(int playerWidth) {
        this.playerWidth = playerWidth;
        repaint();
    }


    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }



    public void setPositionX(int positionX) {
        this.positionX = positionX;
        repaint();
    }



    public void setPositionY(int positionY) {
        this.positionY = positionY;
        repaint();
    }

}
