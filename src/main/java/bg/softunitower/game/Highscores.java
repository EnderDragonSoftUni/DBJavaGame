package bg.softunitower.game;

import bg.softunitower.db.dtos.ScoreDto;
import bg.softunitower.db.services.interfaces.ScoreService;
import bg.softunitower.graphicHandler.Assets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Highscores {

    private static final int SIZE = 25;
    @Autowired
    private ScoreService scoreService;

    public static List<String> topList = new ArrayList<>();
    private int highScoreX = Game.WIDTH / 2 - 185;
    private int highScoreY = 10;
    private int textX = 20;
    private int textY = highScoreY + 105;
    private Long score;
    private Long timePlayed;
    private Font scoreFont = new Font(Font.MONOSPACED, Font.BOLD, SIZE);

    public Highscores() {
    }

    public Highscores(Long score, Long timePlayed) {
        this.score = score;
        this.timePlayed = timePlayed;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public ScoreDto prepareForDb(){
        return new ScoreDto(score, timePlayed, Game.PROFILE.getId());
    }

    public void render(Graphics g) {

        g.drawImage(Assets.hiscore, highScoreX, highScoreY, 400, 80, null);

        g.setFont(scoreFont);
        g.setColor(Color.BLACK);

        int poss = 0;
        for (int i = 0; i < topList.size() && i < 8; i++) {
            String[] data = topList.get(i).split("\\s+");
            String name = data[0];
            Long result = Long.parseLong(data[1]);

            g.drawString(String.format("%d.  %s%s%d", i + 1, name,
                    fillWithSpaces(name, result), result), textX, textY + poss);
            poss += 35;
        }
    }

    private String fillWithSpaces(String name, Long result) {
        StringBuilder spacesString = new StringBuilder();
        int spacesLenght = 35 - name.length() - String.valueOf(result).length();

        for (int i = 0; i < spacesLenght; i++) {
            spacesString.append(" ");
        }
        return spacesString.toString();
    }
}
