package bg.softunitower.db.dao;

import bg.softunitower.db.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends JpaRepository<Profile,Long> {
    @Query("SELECT COUNT(p) from Profile as p WHERE p.username = :username")
    int countAllProfilesWithUsername(@Param(value = "username") String username);
}
