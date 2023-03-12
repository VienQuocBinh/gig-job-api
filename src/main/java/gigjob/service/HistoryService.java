package gigjob.service;

import gigjob.entity.History;

import java.util.List;

public interface HistoryService {
    List<History> ListAll();

    void save(History wallet);

    History get(Long id);

    void Delete(Long id);
}
