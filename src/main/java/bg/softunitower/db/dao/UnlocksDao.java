package bg.softunitower.db.dao;

import bg.softunitower.db.models.Unlocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UnlocksDao extends JpaRepository<Unlocks,Long> {
}
