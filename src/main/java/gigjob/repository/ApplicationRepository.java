package gigjob.repository;

import gigjob.common.embeddedkey.ApplicationId;
import gigjob.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, ApplicationId> {
    @Query("select a from Application a where a.id.worker.id = :worker_id")
    List<Application> findApplicationsByWorkerId(@Param("worker_id") UUID workerId);
}
