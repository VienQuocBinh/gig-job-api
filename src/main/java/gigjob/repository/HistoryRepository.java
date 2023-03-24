package gigjob.repository;


import gigjob.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("select h from History h where h.worker.id = :worker_id ")
    List<History> getHistoryByWorkerId(@Param("worker_id") UUID workerId);
}
