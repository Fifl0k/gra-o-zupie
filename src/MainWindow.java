import javax.swing.*;

public class MainWindow {
    public static void main(String[] args) {

        JFrame frame = new JFrame("ruchomy gracz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2048, 89);

        Player player = new Player();

        player.setPlayerWidth(50);
        player.setPlayerHeight(50);
        player.setPositionX(0);
        player.setPositionY(0);


        frame.add(player);

        frame.setVisible(true);
        player.movePlayer();
    }
}
