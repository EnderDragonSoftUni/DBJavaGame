package bg.softunitower.objects;

import bg.softunitower.graphicHandler.LevelHandler;

import java.awt.*;


public class HighScore {

    public static final int FONTSIZE = 28;

    private static final int DRAW_STRING_X = 125;

    private static final int DRAW_STRING_Y = 30;

    private String score;
    private Font font;

    public HighScore(long score) {
        this.score = Long.toString(score);
        this.font = new Font("Calibri", Font.BOLD, FONTSIZE);

    }

    public void tick(long score) {
        this.score = Long.toString(score);

    }

    public void render(Graphics g) {
        g.setFont(font);
        g.setColor(Color.green);

        g.drawString(String.format("Level: %d | Score: %s", LevelHandler.getCurrentLevel(), score), DRAW_STRING_X, DRAW_STRING_Y);

        g.drawString(String.format("Level: %d | Highscores: %s", LevelHandler.getCurrentLevel(), score), DRAW_STRING_X, DRAW_STRING_Y);

    }

}
