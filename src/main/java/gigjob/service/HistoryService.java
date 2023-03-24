package gigjob.service;

import gigjob.entity.History;
import gigjob.model.request.HistoryRequest;
import gigjob.model.request.HistoryUpdateRequest;
import gigjob.model.response.HistoryResponse;

import java.util.List;
import java.util.UUID;

public interface HistoryService {
    HistoryResponse create(HistoryRequest historyRequest);

    HistoryResponse update(HistoryUpdateRequest historyRequest);

    List<HistoryResponse> getByWorkerId(UUID workerId);

    History get(Long id);

    void Delete(Long id);
}
