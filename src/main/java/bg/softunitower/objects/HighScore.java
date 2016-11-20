package bg.softunitower.objects;

import bg.softunitower.graphicHandler.LevelHandler;

import java.awt.*;


public class HighScore {
    public static final int FONTSIZE = 28;
    private String score;
    private Font font;

    public HighScore(int score) {
        this.score = Integer.toString(score);
        this.font = new Font("Calibri", Font.BOLD, FONTSIZE);

    }

    public void tick(int score) {
        this.score = Integer.toString(score);

    }

    public void render(Graphics g) {
        g.setFont(font);
        g.setColor(Color.green);
        g.drawString(String.format("Level: %d | Score: %s", LevelHandler.getCurrentLevel(), score), 125, 30);
    }

}
