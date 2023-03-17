package gigjob.repository;

import gigjob.entity.Session;
import gigjob.model.response.SessionShopResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("select new gigjob.model.response.SessionShopResponse(s.id, ws.id.worker.id, s.shift, s.duration, s.date)" +
            " from Session s " +
            " join WorkingSession ws on ws.id.session.id = s.id " +
            " join Job j on j.id = ws.id.job.id " +
            " join Shop sh on sh.id = j.shop.id " +
            " where day(s.date) = day(:date) and month(s.date) = month(:date) and year(s.date) = year(:date) " +
            " and sh.id = :shop_id")
    List<SessionShopResponse> findByDateAndShopId(@Param("shop_id") UUID shopId, @Param("date") Date date);
}
