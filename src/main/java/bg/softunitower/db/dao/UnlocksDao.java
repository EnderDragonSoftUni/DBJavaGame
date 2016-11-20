package bg.softunitower.db.dao;

import bg.softunitower.db.models.Unlocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vilimir on 20.11.16.
 */
@Repository
public interface UnlocksDao extends JpaRepository<Unlocks,Long> {
}
