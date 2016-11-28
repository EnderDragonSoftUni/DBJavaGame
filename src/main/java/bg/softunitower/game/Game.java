package bg.softunitower.game;
import bg.softunitower.fortunebonuslevel.Fortune;
import bg.softunitower.display.RegistrationFrame;
import bg.softunitower.display.Window;
import bg.softunitower.graphicHandler.*;
import bg.softunitower.objects.HighScore;
import bg.softunitower.objects.Player;
import bg.softunitower.objects.ProgressBar;
import bg.softunitower.objects.gift.Gift;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Game extends Canvas implements Runnable {

    public static final int SCALE = 2;
    public static final int WIDTH = 320 * SCALE;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final String TITLE = "Icy Tower+";

    public static Score currentScore;
    public static int score = 0;
    public static int coins = 0;

    public static boolean isPaused = false;

    public static boolean itemOneUnlocked = false;
    public static boolean itemTwoUnlocked = false;
    public static boolean itemThreeUnlocked = false;

    public static boolean item1Selected = false;
    public static boolean item2Selected = false;
    public static boolean item3Selected = false;


    private Menu menu;
    private HighScore highScore;
    public boolean running = false;
    private Thread thread;
    private Player player;
    private PlatformHandler platformHandler;
    private GiftHandler giftHandler;
    private ProgressBar progressBar;
    private LevelHandler levelHandler;
    private InputHandler inputHandler;
    private PauseMenu pauseMenu;
    private long timePlayed;

    //Bonus level called "Fortune"
    private Fortune fortune;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        Game.score = score;
    }

    public static void addScore(Gift tempObject) {
        score += getPoints(tempObject);
    }

    public static void addCoin() {
        coins++;
    }

    public enum STATE {
        Menu,
        Game,
        Shop,
        HighScore,
        Labyrinth,
        Credentials,
        End
    }

    public static STATE gameState = STATE.Menu;

    public Game() throws IOException {

        Assets.init();
        this.platformHandler = new PlatformHandler();
        this.giftHandler = new GiftHandler();
        this.highScore = new HighScore(score);
        this.progressBar = new ProgressBar(this);
        this.levelHandler = new LevelHandler(this.platformHandler, this.giftHandler);
        this.pauseMenu = new PauseMenu();
        this.fortune = new Fortune();

        currentScore = new Score(score);
        PlatformHandler.addStartingPlatforms();
        GiftHandler.addRandomGifts();
        this.createPlayer();

        menu = new Menu(this, platformHandler);
        this.addMouseListener(menu);
        this.inputHandler = new InputHandler(this);
        new Window(WIDTH, HEIGHT, TITLE, this);
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run() {

        while (running) {
            long lastTime = System.nanoTime();
            double amountOfTicks = 20.0;
            double ns = 1000000000 / amountOfTicks;
            double delta = 0;
            long timer = System.currentTimeMillis();
            int frames = 0;
            while (running) {

                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (delta >= 1) {
                    tick();
                    delta--;
                }
                if (running) {
                    render();
                }
                frames++;
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
//                    System.out.println("FPS " + frames);
                    frames = 0;
                    if (gameState == STATE.Game){
                        this.timePlayed++;
                    }
                }
            }
            stop();
        }
    }

    private void tick() {
        if (gameState == STATE.Game) {
            if (!isPaused) {
                player.tick();
                platformHandler.tick();
                giftHandler.tick();
                progressBar.tick();
                highScore.tick(score);
                levelHandler.tick();

                if (Player.isDead) {
                    this.resetGame();
                }
            }
        } else if (gameState == Game.STATE.Menu ||
                gameState == STATE.End ||
                gameState == STATE.Shop ||
                gameState == STATE.HighScore) {
            menu.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();


        if (gameState == STATE.Game || gameState == STATE.End) {
            levelHandler.render(g);
        } else {
            g.drawImage(Assets.background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
        }

        g.drawString(String.format("Coins: %s", coins), 10, 20);
        g.drawString(String.format("Time: %d", this.timePlayed), 50, 20);

        if (gameState == STATE.Game) {
            player.render(g);
            platformHandler.render(g);
            giftHandler.render(g);
            highScore.render(g);
            progressBar.render(g);
            if (!isPaused) {
                Assets.pauseButton.render(g);
            } else {
                this.pauseMenu.render(g);
            }
        } else if (gameState == Game.STATE.Menu ||
                gameState == STATE.End ||
                gameState == STATE.Shop ||
                gameState == STATE.HighScore) {
            menu.render(g);
        }


        g.dispose();
        bs.show();
    }

    public void resetGame() {

       // this.fortune.start();
//        this.fortune.start();
        currentScore = new Score(score);
        isPaused = false;
        Score.tick(currentScore);
        Game.gameState = Game.STATE.End;
        Player.isDead = false;
        PlatformHandler.clearAllPlatforms();
        PlatformHandler.addStartingPlatforms();
        GiftHandler.clearAllGifts();
        GiftHandler.addRandomGifts();
        progressBar.setFillProgressBar(0);
        LevelHandler.setCurrentLevel(1);
        InputHandler.beginning = true;
        LevelHandler.levelPassed();
    }

    public void createPlayer() {
        timePlayed = 0;
        player = new Player(WIDTH / 2 - 60, 345, 60, 70, platformHandler, giftHandler, progressBar);
    }

    public static Integer getPoints(Gift get) {
        switch (get.getClass().getSimpleName()) {
            case "GoldCoin":
                return 500;
            case "SilverCoin":
                return 200;
            case "CopperCoin":
                return 75;
            default:
                return 0;
        }
    }
}