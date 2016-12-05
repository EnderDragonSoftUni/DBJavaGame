package bg.softunitower.db.services;

import bg.softunitower.db.dao.ProfileDao;
import bg.softunitower.db.dao.ScoreDao;
import bg.softunitower.db.dtos.ScoreDto;
import bg.softunitower.db.models.Profile;
import bg.softunitower.db.models.Score;
import bg.softunitower.db.services.interfaces.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreDao scoreDao;
    @Autowired
    private ProfileDao profileDao;

    @Override
    public void createScore(ScoreDto scoreDto) {
        Score score = new Score();
        score.setPlaytime(scoreDto.getPlaytime());
        score.setPoints(scoreDto.getPoints());
        score.setProfile(profileDao.findOne(scoreDto.getProfileId()));
        scoreDao.saveAndFlush(score);
    }
}
