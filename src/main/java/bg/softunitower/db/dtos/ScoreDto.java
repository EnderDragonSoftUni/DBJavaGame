package bg.softunitower.db.dtos;

public class ScoreDto {
    private long playtime;

    private long points;

    private long profileId;

    public ScoreDto(long points, long playtime, long profileId) {
        this.playtime = playtime;
        this.points = points;
        this.profileId = profileId;
    }

    public long getPlaytime() {
        return playtime;
    }

    public long getPoints() {
        return points;
    }

    public long getProfileId() {
        return profileId;
    }
}
