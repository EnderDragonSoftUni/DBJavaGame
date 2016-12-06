package bg.softunitower.db.dtos;

public class ProfileDto {
    private String username;
    private String password;

    public ProfileDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
