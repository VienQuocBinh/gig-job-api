package gigjob.repository;

import gigjob.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, UUID> {

    @Query("select w from Worker w where w.id = :worker_id")
    Worker getWorkerById(@Param("worker_id") UUID workerId);

    @Query("select w from Worker w where w.account.id = :account_id")
    Worker findByAccountId(@Param("account_id") String accountId);
}
