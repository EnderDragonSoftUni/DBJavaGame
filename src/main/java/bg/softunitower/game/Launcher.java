package bg.softunitower.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Launcher implements CommandLineRunner {
    @Autowired
    private Game game;

    @Override
    public void run(String... strings) throws IOException {

        game.start();
    }
}
