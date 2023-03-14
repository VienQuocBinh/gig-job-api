package gigjob.repository;

import gigjob.common.embeddedkey.WorkingSessionId;
import gigjob.entity.WorkingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingSessionRepository extends JpaRepository<WorkingSession, WorkingSessionId> {
}
