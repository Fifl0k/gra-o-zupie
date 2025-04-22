import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel implements KeyListener {

    private static final int TILE_SIZE = 32;

    private Player player;
    private List<Platform> platforms = new ArrayList<>();
    private Timer timer;

    private LevelMap map;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);


                String[] layout = {
                        "................................",
                        "......P..........#..............",
                        "................................",
                        "................................",
                        "............###.................",
                        ".......................###......",
                        "................................",
                        "....####........................",
                        "................................",
                        ".........#......###.............",
                        "................................",
                        "................................",
                        "........####...........#........",
                        "................................",
                        ".................###............",
                        "################################",
                        "################################",
                        "################################",
        };

        map = new LevelMap(layout);

        loadLevelFromMap(map);

        timer = new Timer(8, e ->{
            player.update( platforms,getHeight(), getWidth());
            repaint();
        });
        timer.start();
    }
    private void loadLevelFromMap(LevelMap map) {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                TileType tile = map.getTile(x, y);
                int px = x * TILE_SIZE;
                int py = y * TILE_SIZE;

                if (tile == TileType.PLATFORM) {
                    platforms.add(new Platform(px, py, TILE_SIZE, TILE_SIZE));
                } else if (tile == TileType.PLAYER_START) {
                    player = new Player(px, py, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Platform p : platforms) p.draw(g);
        if (player != null) player.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
