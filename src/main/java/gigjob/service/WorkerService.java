package gigjob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gigjob.entity.Worker;
import gigjob.repository.WorkerRepository;

@Service
public class WorkerService {
    
    @Autowired
    private WorkerRepository repo;

    public List<Worker> ListAll(){
        return repo.findAll();
    }

    public void save (Worker worker){
        repo.save(worker);
    }

    public Worker get(Integer id){
        return repo.findById(id).get();
    }
    public void Delete(Integer id){
        repo.deleteById(id);
    }
}
