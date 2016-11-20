package bg.softunitower.fortunebonuslevel;

class PathPoint {
    public int x;
    public int y;
    public int px;
    public int py;
    public int cost;
    public boolean visited;

    public PathPoint(int x, int y, int px, int py, int cost, boolean visited) {
        this.setData(x, y, px, py, cost, visited);
    }

    public final void setData(int x, int y, int px, int py, int cost, boolean visited) {
        this.x = x;
        this.y = y;
        this.px = px;
        this.py = py;
        this.cost = cost;
        this.visited = visited;
    }
}

