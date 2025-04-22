public enum TileType {
    EMPTY('.'),
    PLATFORM('#'),
    PLAYER_START('P'),
    SPIKE('^');

    private final char symbol;

    TileType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static TileType fromChar(char c) {
        for (TileType type : values()) {
            if (type.symbol == c) return type;
        }
        return EMPTY;
    }
}