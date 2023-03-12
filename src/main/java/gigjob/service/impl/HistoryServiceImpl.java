package gigjob.service.impl;

import gigjob.entity.History;
import gigjob.repository.HistoryRepository;
import gigjob.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository repo;

    @Override
    public List<History> ListAll() {
        return repo.findAll();
    }

    @Override
    public void save(History wallet) {
        repo.save(wallet);
    }

    @Override
    public History get(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("History not found " + id));
    }

    @Override
    public void Delete(Long id) {
        repo.deleteById(id);
    }
}
