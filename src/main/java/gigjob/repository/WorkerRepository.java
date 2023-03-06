package gigjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gigjob.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    
}
