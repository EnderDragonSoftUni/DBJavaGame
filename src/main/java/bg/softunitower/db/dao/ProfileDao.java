package bg.softunitower.db.dao;

import bg.softunitower.db.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vilimir on 20.11.16.
 */
@Repository
public interface ProfileDao extends JpaRepository<Profile,String> {

}
