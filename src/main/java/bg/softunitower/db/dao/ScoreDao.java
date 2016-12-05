package bg.softunitower.db.dao;

import bg.softunitower.db.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreDao extends JpaRepository<Score, Long> {
}
