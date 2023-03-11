package gigjob.repository;

import gigjob.common.embeddedkey.ApplicationId;
import gigjob.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, ApplicationId> {
    @Query("select a from Application a where a.id.worker.id = :worker_id")
    List<Application> findApplicationsByWorkerId(@Param("worker_id") UUID workerId);

    @Query("select a from Application a where a.id.job.shop.id = :shop_id")
    List<Application> findApplicationByShopId(@Param("shop_id") UUID shopId);

    @Query("select a from Application a where a.id = :app_id")
    Optional<Application> findApplicationById(@Param("app_id") ApplicationId applicationId);

    @Query("select a from Application a where a.id.job.id = :job_id and a.status = 1")
    List<Application> findAcceptedByJobId(@Param("job_id") Long jobId);

    @Query("select a from Application a where a.id.job.id = :job_id and a.status = 2")
    List<Application> findRejectedByJobId(@Param("job_id") Long jobId);
}
