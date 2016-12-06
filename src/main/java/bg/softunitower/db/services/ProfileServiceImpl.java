package bg.softunitower.db.services;

import bg.softunitower.db.dao.ProfileDao;
import bg.softunitower.db.dtos.ProfileDto;
import bg.softunitower.db.models.Profile;
import bg.softunitower.db.models.Unlocks;
import bg.softunitower.db.services.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vilimir on 20.11.16.
 */
@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileDao profileDao;

    @Override
    public boolean checkIfUserExists(String username) {
        if (profileDao.countAllProfilesWithUsername(username) != 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void createProfile(ProfileDto profileDto) {
        Unlocks unlocks = new Unlocks();
        Profile profileToSave = new Profile();
        profileToSave.setUsername(profileDto.getUsername());
        profileToSave.setPassword(profileDto.getPassword());
        profileToSave.setMoney(0);
        profileToSave.setUnlocks(unlocks);
        profileDao.saveAndFlush(profileToSave);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileDao.findAll();
    }


}
