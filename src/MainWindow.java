import javax.swing.*;

public class MainWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gra z platformami");
        GamePanel gamePanel = new GamePanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1040, 600); // Możesz dostosować ten rozmiar, aby pasował do mapy
        frame.add(gamePanel); // Dodanie panelu gry do okna
        frame.setLocationRelativeTo(null); // Centrowanie okna na ekranie
        frame.setVisible(true); // Ustawienie widoczności okna
        frame.setResizable(false); // Ustawienie, aby okno nie mogło zmieniać rozmiaru
    }
}
