package gigjob.service.impl;

import gigjob.common.embeddedkey.WorkingSessionId;
import gigjob.common.exception.model.InternalServerErrorException;
import gigjob.common.exception.model.ResourceNotFoundException;
import gigjob.common.util.DateTimeUtil;
import gigjob.entity.Job;
import gigjob.entity.Session;
import gigjob.entity.Worker;
import gigjob.entity.WorkingSession;
import gigjob.model.request.CheckInRequest;
import gigjob.model.request.CheckOutRequest;
import gigjob.model.response.JobDetailResponse;
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
    private final DateTimeUtil dateTimeUtil;

    @Override
    public List<SessionShopResponse> getSessionByShopId(UUID shopId, Date date) {
        try {
            return sessionRepository.findByDateAndShopId(shopId, date).stream()
                    .map(session -> {
                        var response = modelMapper.map(session, SessionShopResponse.class);
                        WorkerDetailResponse workerDetailResponse = workerService.getWorkerById(response.getWorkerId());
                        var job = jobRepository.findJobBySessionId(session.getId());
                        var jobDetailRes = modelMapper.map(job, JobDetailResponse.class);
                        response.setJob(jobDetailRes);
                        response.setWorker(workerDetailResponse);
                        response.setSalary(response.getSalary()); // job salary each session
                        // total = salary * duration
                        Double total = response.getSalary().doubleValue() * response.getDuration();
                        response.setTotal(total);
                        return response;
                    })
                    .toList();
        } catch (Exception exception) {
            throw new InternalServerErrorException(exception.getMessage());
        }
    }

    @Override
    public SessionResponse checkIn(CheckInRequest checkInRequest) {
        try {
            Session session = new Session();
            WorkingSession workingSession = new WorkingSession();
            WorkingSessionId workingSessionId = new WorkingSessionId();
            // check in session with duration = -1 means that worker is working
//            session.setDuration((double) duration);
            session.setDuration(-1D);
            session.setShift(checkInRequest.getShift());
            Session checkInSession = sessionRepository.save(session);
            // job
            Job job = jobRepository.findById(checkInRequest.getJobId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found job id: " + checkInRequest.getJobId()));
            // worker
            Worker worker = workerRepository.findById(checkInRequest.getWorkerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found worker id: " + checkInRequest.getWorkerId()));
            workingSessionId.setJob(job);
            workingSessionId.setSession(checkInSession);
            workingSessionId.setWorker(worker);
            workingSession.setId(workingSessionId);
            workingSessionRepository.save(workingSession);
            SessionResponse sessionResponse = modelMapper.map(checkInSession, SessionResponse.class);
            sessionResponse.setTotal(0D);
            return sessionResponse;
        } catch (Exception exception) {
            throw new InternalServerErrorException(exception.getMessage());
        }
    }

    @Override
    public SessionResponse checkOut(CheckOutRequest checkOutRequest) {
        // check exist session
        Session session = sessionRepository.findById(checkOutRequest.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("Session not found for check out id " + checkOutRequest.getSessionId()));
        // check exist job
        Job job = jobRepository.findById(checkOutRequest.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found job id: " + checkOutRequest.getJobId()));
        try {
            // calculate duration
            Date now = new Date();
            Date startDate = session.getDate();
            double duration = Double.parseDouble(dateTimeUtil.durationBetween(startDate, now));
            session.setDuration(duration);
            sessionRepository.save(session);
            // calculate session salary
            Double sessionSalary = (double) job.getSalary() * duration;
            SessionResponse sessionResponse = new SessionResponse();
            sessionResponse.setId(session.getId());
            sessionResponse.setShift(session.getShift());
            sessionResponse.setDuration(duration);
            sessionResponse.setDate(session.getDate());
            sessionResponse.setTotal(sessionSalary);

            return sessionResponse;
        } catch (Exception exception) {
            throw new InternalServerErrorException(exception.getMessage());
        }
    }

    public List<SessionResponse> getWorkingSessions(Long jobId) {
        // get job session
        // get sessions have duration == -1
        return null;
    }
}
