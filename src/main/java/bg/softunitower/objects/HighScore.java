package bg.softunitower.objects;

import bg.softunitower.graphicHandler.LevelHandler;

import java.awt.*;


public class HighScore {
    public static final int FONTSIZE = 28;
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
        g.drawString(String.format("Level: %d | Highscores: %s", LevelHandler.getCurrentLevel(), score), 125, 30);
    }

}
