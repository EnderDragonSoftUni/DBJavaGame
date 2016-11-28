package bg.softunitower.db.services.interfaces;

public interface ProfileService {
    boolean checkIfUserExists(String username);

    void createProfile(String username, String password);
}
