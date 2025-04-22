import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LevelMap {

    private TileType[][] grid;
    private Map<TileType, Image> tileImages; // Mapowanie typu tile na obrazek
    private int tileSize; // Dodajemy zmienną tileSize

    public LevelMap(String[] layout, int tileSize) {
        int rows = layout.length;
        int cols = layout[0].length();
        this.tileSize = tileSize; // Przypisanie tileSize

        grid = new TileType[rows][cols];
        tileImages = new HashMap<>();

        // Ładowanie obrazków dla różnych typów kafelków
        loadTileImages();

        // Inicjalizacja siatki z layoutu
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                char c = layout[y].charAt(x);
                switch (c) {
                    case '#':
                        grid[y][x] = TileType.PLATFORM;
                        break;
                    case 'P':
                        grid[y][x] = TileType.PLAYER_START;
                        break;
                    case '^':
                        grid[y][x] = TileType.SPIKE;
                        break;
                    default:
                        grid[y][x] = TileType.EMPTY;
                        break;
                }
            }
        }
    }


        private void loadTileImages() {
            // Debugowanie: sprawdzenie dostępności zasobów
            System.out.println(getClass().getResource("/resources/textures/platform.png"));
            System.out.println(getClass().getResource("resources/textures/player_start.png"));
         //   System.out.println(getClass().getResource("/resources/textures/spike.png"));
            System.out.println(getClass().getResource("/resources/textures/empty.png"));

            tileImages.put(TileType.PLATFORM, new ImageIcon(getClass().getResource("/resources/textures/platform.png")).getImage());
            tileImages.put(TileType.PLAYER_START, new ImageIcon(getClass().getResource("/resources/textures/player_start.png")).getImage());
          //  tileImages.put(TileType.SPIKE, new ImageIcon(getClass().getResource("/resources/textures/spike.png")).getImage());
            tileImages.put(TileType.EMPTY, new ImageIcon(getClass().getResource("/resources/textures/empty.png")).getImage());

            System.out.println("Platform loaded: " + tileImages.get(TileType.PLATFORM));
        }

    public boolean isSolid(int gridX, int gridY) {
        if (gridY < 0 || gridY >= grid.length || gridX < 0 || gridX >= grid[0].length) {
            return true; // poza mapą = ściana
        }
        return grid[gridY][gridX] == TileType.PLATFORM;
    }

    public TileType getTile(int x, int y) {
        return grid[y][x];
    }

    public int getWidth() {
        return grid[0].length;
    }

    public int getHeight() {
        return grid.length;
    }

    public void draw(Graphics g) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                TileType tile = grid[y][x];
                Image tileImage = tileImages.get(tile);
                if (tileImage != null) {
                    g.drawImage(tileImage, x * tileSize, y * tileSize, tileSize, tileSize, null);
                }
            }
        }
    }
}
