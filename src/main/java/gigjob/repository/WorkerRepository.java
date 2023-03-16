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
//    @Query("select new gigjob.model.response.WorkerDetailResponse(w.id, w.firstName, w.lastName, w.middleName, w.birthday, w.education, " +
//            " a.username, a.password, a.email, a.phone, a.createdDate, a.updatedDate, a.isLocked, a.isDisable, a.isLocked, a.image_url )" +
//            " from Worker w " +
//            " join Account a on a.id = w.account.id " +
//            " where w.account.id = :account_id ")
//    WorkerDetailResponse findByAccountId(@Param("account_id") String accountId);
}
