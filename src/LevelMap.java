public class LevelMap {
    private TileType[][] grid;

    public LevelMap(String[] layout) {
        int rows = layout.length;
        int cols = layout[0].length();
        grid = new TileType[rows][cols];

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
}