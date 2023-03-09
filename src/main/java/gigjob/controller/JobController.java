package gigjob.controller;

import gigjob.entity.Job;
import gigjob.entity.JobType;
import gigjob.model.request.JobRequest;
import gigjob.model.response.JobDetailResponse;
import gigjob.model.response.JobResponse;
import gigjob.repository.JobRepository;
import gigjob.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class JobController {
    private final ModelMapper modelMapper;
    private final JobRepository jobRepository;
    private final JobService jobService;

    @GetMapping("/v1/job")
    public ResponseEntity<List<JobDetailResponse>> getJobList() {
        List<JobDetailResponse> jobResponseList = jobService.getJob();
        return ResponseEntity.status(HttpStatus.OK).body(jobResponseList);
    }

    @GetMapping("/v1/job/specification")
    public ResponseEntity<List<JobDetailResponse>> getJobListSpec() {
//        List<JobDetailResponse> jobResponseList = jobRepository.findAll(JobSpecification.isOfficialJob(1))
//                .stream()
//                .map(job -> modelMapper.map(job, JobDetailResponse.class))
//                .toList();
        JobType type = new JobType(1L, "Việc khối văn phòng", null);
        Specification<Job> specification1 = (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("id"), 3);
        Specification<Job> specification2 = (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("description").as(String.class), "%Quản lý%");
        List<Job> jobs = jobRepository.findAll(specification1.and(specification2));
        List<JobDetailResponse> jobResponseList = jobs
                .stream()
                .map(job -> modelMapper.map(job, JobDetailResponse.class))
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(jobResponseList);
    }

    @GetMapping("/v1/job/{id}")
    public ResponseEntity<JobDetailResponse> getJobById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getJobById(id));
    }

    @PostMapping("/v1/job")
    public ResponseEntity<JobResponse> createJob(@RequestBody JobRequest jobRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.addJob(jobRequest));
    }

    @PatchMapping("/v1/job")
    public ResponseEntity<JobResponse> updateJob(@RequestBody JobRequest jobRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.updateJob(jobRequest));
    }

    @DeleteMapping("/v1/job")
    public ResponseEntity<String> deleteJob(@RequestBody Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.deleteJob(id));
    }
}
