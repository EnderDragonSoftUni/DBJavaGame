package bg.softunitower.game;

import bg.softunitower.graphicHandler.Assets;
import bg.softunitower.graphicHandler.PlatformHandler;
import bg.softunitower.objects.Player;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

    private Game game;
    private PlatformHandler platformHandler;

    public Menu(Game game, PlatformHandler platformHandler) {
        this.platformHandler = platformHandler;
        this.game = game;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (Game.gameState == Game.STATE.Menu) {
            if (mouseOver(mx, my, Assets.startButton.getX(), Assets.startButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.Game;
                game.createPlayer();

                return;
            }

            if (mouseOver(mx, my, Assets.shopButton.getX(), Assets.shopButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.Shop;

                return;
            }

            if (mouseOver(mx, my, Assets.highScoreButton.getX(), Assets.highScoreButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.HighScore;
                return;
            }

            //Quit Button
            if (mouseOver(mx, my, Assets.exitButton.getX(), Assets.exitButton.getY(), 200, 80)) {
                System.exit(1);
            }
        }

        if (Game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, Assets.tryAgainButton.getX(), Assets.tryAgainButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.Menu;
                game.setScore(0);
            }
        }

        if (Game.gameState == Game.STATE.Game && !Game.isPaused) {
            //Pause button
            if (mouseOver(mx, my, Assets.pauseButton.getX(), Assets.pauseButton.getY(),
                    32, 32)) {
                Game.isPaused = true;
            }
        }

        if (Game.gameState == Game.STATE.Game && Game.isPaused) {
            //Continue button
            if (mouseOver(mx, my, Assets.continueButton.getX(), Assets.continueButton.getY(), 200, 80)){
                Game.isPaused = false;
            }

            if (mouseOver(mx, my, Assets.mainMenuButton.getX(), Assets.mainMenuButton.getY(), 200, 80)){
                Game.isPaused = false;
                this.game.resetGame();
                Game.gameState = Game.STATE.Menu;
            }

            if (mouseOver(mx, my, Assets.pauseMenuExit.getX(), Assets.pauseMenuExit.getY(), 200, 80)){
                System.exit(1);
            }
        }

        if (Game.gameState == Game.STATE.Shop) {
            if (mouseOver(mx, my, Assets.shopBackButton.getX(), Assets.shopBackButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.Menu;
            }
            if (mouseOver(mx, my, Assets.selectButtonLeft.getX(), Assets.selectButtonLeft.getY(), 100, 40) && Game
                    .itemOneUnlocked) {
                Game.item1Selected = true;
                Game.item2Selected = false;
                Game.item3Selected = false;
                Player.img = Assets.wizard;
                return;
            }
            if (mouseOver(mx, my, Assets.selectButtonMiddle.getX(), Assets.selectButtonMiddle.getY(), 100, 40) &&
                    Game.itemTwoUnlocked) {
                Game.item1Selected = false;
                Game.item2Selected = true;
                Game.item3Selected = false;
                Player.img = Assets.nakov;
                return;
            }
            if (mouseOver(mx, my, Assets.selectButtonRight.getX(), Assets.selectButtonRight.getY(), 100, 40) && Game
                    .itemThreeUnlocked) {
                Game.item1Selected = false;
                Game.item2Selected = false;
                Game.item3Selected = true;
                Player.img = Assets.zombie;
                return;
            }
            if (mouseOver(mx, my, Assets.buyItemOneButton.getX(), Assets.buyItemOneButton.getY(), 100, 40)) {
                if (Game.coins >= 40) {
                    Game.itemOneUnlocked = true;
                    Game.coins -= 40;
                }
                return;
            }
            if (mouseOver(mx, my, Assets.buyItemTwoButton.getX(), Assets.buyItemTwoButton.getY(), 100, 40)) {
                if (Game.coins >= 60) {
                    Game.itemTwoUnlocked = true;
                    Game.coins -= 60;
                }
                return;
            }
            if (mouseOver(mx, my, Assets.buyItemThreeButton.getX(), Assets.buyItemThreeButton.getY(), 100, 40)) {
                if (Game.coins >= 15) {
                    Game.itemThreeUnlocked = true;
                    Game.coins -= 15;
                }
                return;
            }
        }

        if (Game.gameState == Game.STATE.HighScore) {
            if (mouseOver(mx, my, Assets.shopBackButton.getX(), Assets.shopBackButton.getY(), 200, 80)) {
                Game.gameState = Game.STATE.Menu;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            return my > y && my < y + height;
        } else {
            return false;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        if (Game.gameState == Game.STATE.Menu) {

            g.drawImage(Assets.gameLogo, Game.WIDTH / 3 - 80, 20, 380, 70, null);
            Assets.startButton.render(g);
            Assets.exitButton.render(g);
            Assets.shopButton.render(g);
            Assets.highScoreButton.render(g);

        } else if (Game.gameState == Game.STATE.End) {
            g.drawImage(Assets.gameOver, 170, 40, 320, 100, null);
            Assets.tryAgainButton.render(g);

            Font font = new Font("Calibri", Font.BOLD, 40);
            g.setFont(font);
            g.setColor(Color.BLUE);
            g.drawString(String.format("You lost with a score of: %d", this.game.getScore()), Game.WIDTH / 5 - 40, 250);
        } else if (Game.gameState == Game.STATE.Shop) {
            Assets.shopBackButton.render(g);

            g.drawImage(Assets.wizImage, Assets.buyItemOneButton.getX() - 20, Assets.buyItemOneButton.getY() - 130,
                    null);
            g.drawImage(Assets.nakovImage, Assets.buyItemTwoButton.getX() - 10, Assets.buyItemTwoButton.getY() - 130,
                    null);
            g.drawImage(Assets.zombieImage, Assets.buyItemThreeButton.getX() - 10, Assets.buyItemThreeButton.getY() -
                    130, null);

            if (!Game.itemOneUnlocked) {
                Assets.buyItemOneButton.render(g);
                g.drawString("Price: 40", Assets.buyItemOneButton.getX() + 35, Assets.buyItemOneButton.getY() - 150);
            }
            if (!Game.item1Selected && Game.itemOneUnlocked) {
                Assets.selectButtonLeft.render(g);
            }
            if (!Game.itemTwoUnlocked) {
                Assets.buyItemTwoButton.render(g);
                g.drawString("Price: 60", Assets.buyItemTwoButton.getX() + 15, Assets.buyItemTwoButton.getY() - 150);
            }

            if (!Game.item2Selected && Game.itemTwoUnlocked) {
                Assets.selectButtonMiddle.render(g);
            }
            if (!Game.itemThreeUnlocked) {
                Assets.buyItemThreeButton.render(g);
                g.drawString("Price: 15", Assets.buyItemThreeButton.getX() + 35, Assets.buyItemThreeButton.getY() -
                        150);
            }

            if (!Game.item3Selected && Game.itemThreeUnlocked) {
                Assets.selectButtonRight.render(g);
            }

        } else if (Game.gameState == Game.STATE.HighScore) {
            Game.currentScore.render(g);
            Assets.shopBackButton.render(g);
        }
    }
}
