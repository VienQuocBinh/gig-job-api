package gigjob.service.impl;

import gigjob.common.exception.model.InternalServerErrorException;
import gigjob.common.exception.model.ResourceNotFoundException;
import gigjob.entity.History;
import gigjob.entity.Worker;
import gigjob.model.request.HistoryRequest;
import gigjob.model.request.HistoryUpdateRequest;
import gigjob.model.response.HistoryResponse;
import gigjob.repository.HistoryRepository;
import gigjob.repository.WorkerRepository;
import gigjob.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper;

    @Override
    public HistoryResponse create(HistoryRequest historyRequest) {
        try {
            Worker worker = workerRepository.findById(historyRequest.getWorkerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Worker not found for id: " + historyRequest.getWorkerId()));
            History newHistory = new History();
            newHistory.setPosition(historyRequest.getPosition());
            newHistory.setStartDate(historyRequest.getStartDate());
            newHistory.setEndDate(historyRequest.getEndDate());
            newHistory.setWorker(worker);
            History history = historyRepository.save(newHistory);
            return modelMapper.map(history, HistoryResponse.class);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public HistoryResponse update(HistoryUpdateRequest historyRequest) {
        try {
            History history = historyRepository.findById(historyRequest.getId()).
                    orElseThrow(() -> new ResourceNotFoundException("History not found for id: " + historyRequest.getId()));
            history.setPosition(historyRequest.getPosition());
            history.setStartDate(historyRequest.getStartDate());
            history.setEndDate(historyRequest.getEndDate());
            History updatedHistory = historyRepository.save(history);
            return modelMapper.map(updatedHistory, HistoryResponse.class);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public List<HistoryResponse> getByWorkerId(UUID workerId) {
        try {
            List<History> histories = historyRepository.getHistoryByWorkerId(workerId);
            return histories.stream()
                    .map(history -> modelMapper.map(history, HistoryResponse.class))
                    .toList();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public History get(Long id) {
        return historyRepository.findById(id).orElseThrow(() -> new NotFoundException("History not found " + id));
    }

    @Override
    public void Delete(Long id) {
        historyRepository.deleteById(id);
    }
}
