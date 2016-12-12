package bg.softunitower.db.services.interfaces;

import bg.softunitower.db.dtos.ProfileCoinsDto;
import bg.softunitower.db.dtos.ProfileDto;
import bg.softunitower.db.models.Profile;

import java.util.List;

public interface ProfileService {
    boolean checkIfUserExists(String username);

    void createProfile(ProfileDto profileDto);

    List<Profile> getAllProfiles();

    void saveCoins(ProfileCoinsDto profileCoinsDto);

}
