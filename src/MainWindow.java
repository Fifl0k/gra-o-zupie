import javax.swing.*;

public class MainWindow {
    public static void main(String[] args) {


        JFrame frame = new JFrame("zupa z bobra");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        Player player = new Player();

        player.setPlayerWidth(50);
        player.setPlayerHeight(50);
        player.setPositionX(0);
        player.setPositionY(0);
        player.setSpeed(5);
//zf
        player.requestFocusInWindow();
        frame.add(player);

        frame.setVisible(true);

    }
}
