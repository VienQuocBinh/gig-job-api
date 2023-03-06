package gigjob.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import gigjob.entity.History;

public interface HistoryRepository extends JpaRepository<History , Long>{
    
}
