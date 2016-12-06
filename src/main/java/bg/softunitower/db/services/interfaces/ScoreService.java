package bg.softunitower.db.services.interfaces;

import bg.softunitower.db.dtos.ScoreDto;
import org.springframework.stereotype.Repository;

public interface ScoreService {

    void createScore(ScoreDto scoreDto);
}
