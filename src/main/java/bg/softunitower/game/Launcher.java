package bg.softunitower.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Launcher  implements CommandLineRunner{
    @Override
    public void run(String... strings) throws IOException {
        Game game = new Game();
        game.start();
    }
}
