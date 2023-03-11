package gigjob.repository;

import gigjob.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    @Query("select w from Worker w where w.account.id = :account_id")
//    Worker findByAccount_Id(@Param("account_id") String accountId);
    Worker findByAccountId(@Param("account_id") String accountId);
}
