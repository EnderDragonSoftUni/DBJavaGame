package bg.softunitower.db.services;

import bg.softunitower.db.dao.UnlocksDao;
import bg.softunitower.db.dtos.UnlockDto;
import bg.softunitower.db.models.Unlocks;
import bg.softunitower.db.services.interfaces.UnlockService;
import bg.softunitower.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnlocksServiceImpl implements UnlockService {

    @Autowired
    private UnlocksDao unlocksDao;

    @Override
    public void save(UnlockDto unlockDto) {
        Unlocks unlocks = Game.PROFILE.getUnlocks();
        unlocks.setItemOneSelected(unlockDto.isItemOneSelected());
        unlocks.setItemOneUnlocked(unlockDto.isItemOneUnlocked());
        unlocks.setItemTwoSelected(unlockDto.isItemTwoSelected());
        unlocks.setItemTwoUnlocked(unlockDto.isItemTwoUnlocked());
        unlocks.setItemThreeSelected(unlockDto.isItemThreeSelected());
        unlocks.setItemThreeUnlocked(unlockDto.isItemThreeUnlocked());
        this.unlocksDao.saveAndFlush(unlocks);


    }
}
