package gigjob.service.impl;

import gigjob.common.embeddedkey.WorkingSessionId;
import gigjob.common.exception.model.InternalServerErrorException;
import gigjob.common.exception.model.ResourceNotFoundException;
import gigjob.common.meta.Shift;
import gigjob.entity.Job;
import gigjob.entity.Session;
import gigjob.entity.Worker;
import gigjob.entity.WorkingSession;
import gigjob.model.response.SessionResponse;
import gigjob.model.response.SessionShopResponse;
import gigjob.model.response.WorkerDetailResponse;
import gigjob.repository.JobRepository;
import gigjob.repository.SessionRepository;
import gigjob.repository.WorkerRepository;
import gigjob.repository.WorkingSessionRepository;
import gigjob.service.SessionService;
import gigjob.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final ModelMapper modelMapper;
    private final SessionRepository sessionRepository;
    private final WorkingSessionRepository workingSessionRepository;
    private final JobRepository jobRepository;
    private final WorkerRepository workerRepository;
    private final WorkerService workerService;

    @Override
    public List<SessionShopResponse> getSessionByShopId(UUID shopId, Date date) {
        try {
            return sessionRepository.findByDateAndShopId(shopId, date).stream()
                    .map(session -> {
                        var response = modelMapper.map(session, SessionShopResponse.class);
                        WorkerDetailResponse workerDetailResponse = workerService.getWorkerById(response.getWorkerId());
                        response.setWorker(workerDetailResponse);
                        response.setSalary(response.getSalary() * session.getDuration());
                        return response;
                    })
                    .toList();
        } catch (Exception exception) {
            throw new InternalServerErrorException(exception.getMessage());
        }
    }

    @Override
    public SessionResponse checkIn(UUID workerId, Long jobId, int duration, Shift shift) {
        try {
            Session session = new Session();
            WorkingSession workingSession = new WorkingSession();
            WorkingSessionId workingSessionId = new WorkingSessionId();
            // session
            session.setDuration(duration);
            session.setShift(shift);
            Session checkInSession = sessionRepository.save(session);
            // job
            Job job = jobRepository.findById(jobId)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found job id: " + jobId));
            // worker
            Worker worker = workerRepository.findById(workerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found worker id: " + workerId));
            workingSessionId.setJob(job);
            workingSessionId.setSession(checkInSession);
            workingSessionId.setWorker(worker);
            workingSession.setId(workingSessionId);
            workingSessionRepository.save(workingSession);
            return modelMapper.map(checkInSession, SessionResponse.class);
        } catch (Exception exception) {
            throw new InternalServerErrorException(exception.getMessage());
        }
    }
}
