import javax.swing.*;

public class MainWindow {
    public static void main(String[] args) {


        JFrame frame = new JFrame("zupa z bobra");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        Player player = new Player();

        player.setPlayerWidth(30);
        player.setPlayerHeight(30);
        player.setPositionX(500);
        player.setPositionY(0);
        player.setSpeed(7);

        player.requestFocusInWindow();
        frame.add(player);

        frame.setVisible(true);

    }
}
