package gigjob.repository;

import gigjob.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
    @Query("select j from Job j where j.shop.id = :shop_id")
    List<Job> findAllByShopId(@Param("shop_id") UUID id);

    @Query("select j from Job j where j.title like %:title%")
    List<Job> searchByTitle(@Param("title") String title);

    @Query("select j from Job j join WorkingSession ws on j.id = ws.id.job.id join Session s on ws.id.session.id = s.id where s.id = :session_id")
    Job findJobBySessionId(@Param("session_id") Long sessionId);
}
