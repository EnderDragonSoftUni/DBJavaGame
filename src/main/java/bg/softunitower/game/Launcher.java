package bg.softunitower.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Launcher implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        Game game = null;
        try {
            game = new Game();
        } catch (IOException e) {
            e.printStackTrace();
        }
        game.start();
    }
}
