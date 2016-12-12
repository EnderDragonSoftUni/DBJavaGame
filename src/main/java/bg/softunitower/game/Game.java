package bg.softunitower.game;

import bg.softunitower.db.dtos.ProfileCoinsDto;
import bg.softunitower.db.dtos.ProfileDto;
import bg.softunitower.db.models.Profile;
import bg.softunitower.db.services.interfaces.ProfileService;
import bg.softunitower.db.services.interfaces.ScoreService;
import bg.softunitower.fortunebonuslevel.Fortune;
import bg.softunitower.display.Window;
import bg.softunitower.graphicHandler.*;
import bg.softunitower.objects.HighScore;
import bg.softunitower.objects.Player;
import bg.softunitower.objects.ProgressBar;
import bg.softunitower.objects.gift.Gift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

@Component
public class Game extends Canvas implements Runnable {

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private  ProfileService profileService;

    public static final int SCALE = 2;
    public static final int WIDTH = 320 * SCALE;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final String TITLE = "Icy Tower+";


    public static Highscores highscore;
    public static long score = 0;
    public static int coins;
    public static boolean coinTransactionFlag = false;

    public static boolean isPaused = false;

    public static Profile PROFILE = null;

    public static boolean itemOneUnlocked;
    public static boolean itemTwoUnlocked;
    public static boolean itemThreeUnlocked;

    public static boolean item1Selected;
    public static boolean item2Selected;
    public static boolean item3Selected;

    @Autowired
    private Menu menu;
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
    private HighScore highScore;
    private boolean isWindowInitialized;

    //Bonus level called "Fortune"
    private Fortune fortune;

    public static void setProfile(Profile profile) {
        Game.PROFILE = profile;
        coins = profile.getMoney();
    }

    public long getTimePlayed() {
        return timePlayed;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
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

        highscore = new Highscores(score, this.getTimePlayed());
        PlatformHandler.addStartingPlatforms();
        GiftHandler.addRandomGifts();
        this.createPlayer();
        this.isWindowInitialized = false;
        //startGameWindow();
    }

    public static void initializeUnlocks() {
        itemOneUnlocked = PROFILE.getUnlocks().getItemOneUnlocked();
        itemTwoUnlocked = PROFILE.getUnlocks().getItemTwoUnlocked();
        itemThreeUnlocked = PROFILE.getUnlocks().getItemThreeUnlocked();

        item1Selected = PROFILE.getUnlocks().getItemOneSelected();
        item2Selected = PROFILE.getUnlocks().getItemTwoSelected();
        item3Selected = PROFILE.getUnlocks().getItemThreeSelected();
        setImage();

    }

    private static void setImage() {
        if (item1Selected){
            Player.img = Assets.wizard;
        }else if (item2Selected){
            Player.img = Assets.nakov;
        }else if (item3Selected){
            Player.img = Assets.zombie;
        }
    }

    private void startGameWindow() {
        //menu //= new Menu(this, platformHandler);
        this.addMouseListener(menu);
        this.inputHandler = new InputHandler(this);
        new Window(WIDTH, HEIGHT, TITLE, this);
        this.isWindowInitialized = true;

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
                if (PROFILE != null && this.isWindowInitialized == false) {
                    this.startGameWindow();
                }
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
                    if (gameState == STATE.Game) {
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
                if (coinTransactionFlag){
                    saveCoins();
                    coinTransactionFlag = false;
                }
            }
        } else if (gameState == Game.STATE.Menu ||
                gameState == STATE.End ||
                gameState == STATE.Shop ||
                gameState == STATE.HighScore) {
            if (isWindowInitialized) menu.tick();
        }
    }

    private void render() {
        if (!isWindowInitialized) {
            return;
        }
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
        g.drawString(String.format("Time: %d", this.timePlayed), 80, 20);

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

        highscore = new Highscores(this.getScore(), this.getTimePlayed());
        scoreService.createScore(highscore.prepareForDb());
        saveCoins();
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

    public void saveCoins() {
        ProfileCoinsDto profileCoinsDto = new ProfileCoinsDto();
        profileCoinsDto.setCoins(coins);
        this.profileService.saveCoins(profileCoinsDto);
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

    public static void raiseCoinTransactionFlag(){
        coinTransactionFlag = true;
    }
}