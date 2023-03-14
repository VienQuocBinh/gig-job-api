package gigjob.repository;

import gigjob.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShopRepository extends JpaRepository<Shop, UUID> {
    @Query("select sh from Shop sh where sh.account.id = :account_id")
    Optional<Shop> findByAccountId(@Param("account_id") String accountId);
}
