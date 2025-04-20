import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends JPanel {


    private int playerWidth = 0, playerHeight;
private int positionX = 0, positionY =0;


    public void movePlayer(){
positionX += 5;
//positionY +=5;
        if (positionX > getWidth()) positionX = 0;
  //      if (positionY > getHeight()) positionY = 0;
repaint();
}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);



        g.setColor(Color.RED);


        g.fillRect(positionX, positionY, playerWidth, playerHeight);

    }
Timer timer;
    public Player(){
         timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer();
            }
        });
         timer.start();
    }

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
