package bg.softunitower.db.services.interfaces;

import bg.softunitower.db.models.Profile;

import java.util.List;

public interface ProfileService {
    boolean checkIfUserExists(String username);

    void createProfile(String username, String password);

    List<Profile> getAllProfiles();
}
