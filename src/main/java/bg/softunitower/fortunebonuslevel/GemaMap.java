package bg.softunitower.fortunebonuslevel;

public abstract class GemaMap {
    protected int[][] map;

    public GemaMap() {
    }

    public boolean hasTile(int x, int y) {
        return x >= 0 && y >= 0 && y < this.getHeight() && x < this.getWidth();
    }

    public int getTileId(int x, int y) {
        return this.hasTile(x, y)?this.map[y][x]:0;
    }

    public int getHeight() {
        return this.map.length;
    }

    public int getWidth() {
        return this.map[0].length;
    }
}
