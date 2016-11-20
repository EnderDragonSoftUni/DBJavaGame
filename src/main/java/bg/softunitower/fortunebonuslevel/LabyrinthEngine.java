package bg.softunitower.fortunebonuslevel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LabyrinthEngine implements Runnable {
    public static final int LEFT = 37;
    public static final int RIGHT = 39;
    public static final int DOWN = 38;
    public static final int UP = 40;
    private boolean running;
    protected int timeDelay = 100;
    protected Labyrinth labyrinth;
    protected GemaMap map;
    protected Smiley smiley;
    protected KeyAdapter keyListener = new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == 37) {
                LabyrinthEngine.this.smiley.directionX = -1;
            }

            if(e.getKeyCode() == 39) {
                LabyrinthEngine.this.smiley.directionX = 1;
            }

            if(e.getKeyCode() == 38) {
                LabyrinthEngine.this.smiley.directionY = -1;
            }

            if(e.getKeyCode() == 40) {
                LabyrinthEngine.this.smiley.directionY = 1;
            }

        }

        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == 37 || e.getKeyCode() == 39) {
                LabyrinthEngine.this.smiley.directionX = 0;
            }

            if(e.getKeyCode() == 38 || e.getKeyCode() == 40) {
                LabyrinthEngine.this.smiley.directionY = 0;
            }

        }
    };
    protected MouseAdapter mouseListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            int startTileX = (int)Math.floor((double)((Math.abs(LabyrinthEngine.this.smiley.posX - LabyrinthEngine.this.smiley.posRenderX) + e.getX()) / 40));
            int startTileY = (int)Math.floor((double)((Math.abs(LabyrinthEngine.this.smiley.posY - LabyrinthEngine.this.smiley.posRenderY) + e.getY()) / 40));
            if(LabyrinthEngine.this.tileIsWalkable(startTileX, startTileY)) {
                MapPath mapPath = LabyrinthEngine.this.makePath(LabyrinthEngine.this.smiley.posX / 40, LabyrinthEngine.this.smiley.posY / 40, startTileX, startTileY);
                LabyrinthEngine.this.smiley.mapPath = mapPath;
            }

        }
    };

    public LabyrinthEngine() {
    }

    public void start() {
        if(!this.running) {
            this.running = true;
            this.labyrinth = new Labyrinth();
            this.smiley = new Smiley();
            this.setMap(1);
            this.setListener();
            (new Thread(this)).start();
        }
    }

    public MapPath makePath(int startX, int startY, int endX, int endY) {
        MapFindingPath mfp = new MapFindingPath();
        mfp.setcheckPoint(new MapCheckPoint() {
            public boolean check(int x, int y) {
                return LabyrinthEngine.this.tileIsWalkable(x, y);
            }
        });
        return mfp.findPath(startX, startY, endX, endY)?mfp.getPath():null;
    }

    public void setMap(int mapId) {
        if(mapId == 1) {
            this.map = new Map1();
        } else if(mapId == 2) {
            this.map = new Map2();
        } else if(mapId == 3) {
            this.map = new Map3();
        }

        this.smiley.mapPath = null;
        this.smiley.posX = 40;
        this.smiley.posY = 40;
        this.smiley.posRenderX = 40;
        this.smiley.posRenderY = 40;
    }

    public void stop() {
        this.unSetListener();
        this.running = false;
    }

    public Labyrinth getLabyrinth() {
        return this.labyrinth;
    }

    public void run() {
        for(; this.running; this.update()) {
            try {
                TimeUnit.MILLISECONDS.sleep((long)this.timeDelay);
            } catch (InterruptedException var2) {
                Logger.getLogger(LabyrinthEngine.class.getName()).log(Level.SEVERE, (String)null, var2);
            }
        }

    }

    public void setListener() {
        this.labyrinth.setFocusable(true);
        this.labyrinth.addKeyListener(this.keyListener);
        this.labyrinth.addMouseListener(this.mouseListener);
    }

    public void unSetListener() {
        this.labyrinth.setFocusable(false);
        this.labyrinth.removeKeyListener(this.keyListener);
        this.labyrinth.removeMouseListener(this.mouseListener);
    }

    protected void update() {
        this.checkingChangeMap();
        this.labyrinth.removeRenders();
        int mapW = this.map.getWidth();
        int mapH = this.map.getHeight();
        byte widthTile = 18;
        byte heightTile = 12;
        int cWidthTile = widthTile / 2 - 1;
        int cHeightTile = heightTile / 2 - 1;
        int offsetX = (this.smiley.posX - this.smiley.posRenderX) % 40;
        int offsetY = (this.smiley.posY - this.smiley.posRenderY) % 40;
        int startTileX = (int)Math.floor((double)(Math.abs(this.smiley.posX - this.smiley.posRenderX) / 40));
        int startTileY = (int)Math.floor((double)(Math.abs(this.smiley.posY - this.smiley.posRenderY) / 40));
        int endTileX = widthTile + startTileX > mapW?mapW:widthTile + startTileX;
        int endTileY = heightTile + startTileY > mapH?mapH:heightTile + startTileY;
        boolean movePlayerX = this.smiley.posX / 40 < cWidthTile || mapW - this.smiley.posX / 40 < cWidthTile + 1;
        boolean movePlayerY = this.smiley.posY / 40 < cHeightTile || mapH - this.smiley.posY / 40 < cHeightTile + 2;

        for(int y = startTileY; y < endTileY; ++y) {
            for(int x = startTileX; x < endTileX; ++x) {
                int tileId = this.map.getTileId(x, y);
                BaseTile tile = BaseTile.getTileById(tileId);
                tile.posX = (x - startTileX) * 40;
                tile.posY = (y - startTileY) * 40;
                tile.posX -= offsetX;
                tile.posY -= offsetY;
                this.labyrinth.addRender(tile);
            }
        }

        this.labyrinth.addRender(this.smiley);
        if((this.smiley.directionX != 0 || this.smiley.directionY != 0) && this.accessMove()) {
            this.smiley.posX += this.smiley.directionX * this.smiley.speed;
            this.smiley.posY += this.smiley.directionY * this.smiley.speed;
            if(movePlayerX) {
                this.smiley.posRenderX += this.smiley.directionX * this.smiley.speed;
            }

            if(movePlayerY) {
                this.smiley.posRenderY += this.smiley.directionY * this.smiley.speed;
            }
        }

        this.labyrinth.repaint();
    }

    public void checkingChangeMap() {
        int tileX = this.smiley.posX / 40;
        int tileY = this.smiley.posY / 40;
        int tileId = this.map.getTileId(tileX, tileY);
        BaseTile tile = BaseTile.getTileById(tileId);
        if(tile.door > 0) {
            this.setMap(tile.door);
        }

    }

    protected boolean accessMove() {
        boolean isWalkable = true;
        int left = (int)Math.ceil((double)(this.smiley.posX / 40));
        int right = (int)Math.floor((double)((this.smiley.posX + this.smiley.width - 1) / 40));
        int top = (int)Math.ceil((double)((this.smiley.posY + this.smiley.speed * this.smiley.directionY) / 40));
        int down = (int)Math.floor((double)((this.smiley.posY + this.smiley.height + this.smiley.speed * this.smiley.directionY - 1) / 40));
        if(this.smiley.directionY != -1 || this.tileIsWalkable(left, top) && this.tileIsWalkable(right, top)) {
            if(this.smiley.directionY == 1 && (!this.tileIsWalkable(left, down) || !this.tileIsWalkable(right, down))) {
                isWalkable = false;
            }
        } else {
            isWalkable = false;
        }

        left = (int)Math.ceil((double)((this.smiley.posX + this.smiley.speed * this.smiley.directionX) / 40));
        right = (int)Math.floor((double)((this.smiley.posX + this.smiley.width + this.smiley.speed * this.smiley.directionX - 1) / 40));
        top = (int)Math.ceil((double)(this.smiley.posY / 40));
        down = (int)Math.floor((double)((this.smiley.posY + this.smiley.height - 1) / 40));
        if(this.smiley.directionX == -1 && (!this.tileIsWalkable(left, top) || !this.tileIsWalkable(left, down))) {
            isWalkable = false;
        } else if(this.smiley.directionX == 1 && (!this.tileIsWalkable(right, top) || !this.tileIsWalkable(right, down))) {
            isWalkable = false;
        }

        return isWalkable;
    }

    protected boolean tileIsWalkable(int x, int y) {
        BaseTile tile = BaseTile.getTileById(this.map.getTileId(x, y));
        return tile != null && tile.isWalkable;
    }
}
