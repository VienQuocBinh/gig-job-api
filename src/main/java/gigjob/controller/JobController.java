package gigjob.controller;

import gigjob.entity.Job;
import gigjob.model.domain.SearchCriteria;
import gigjob.model.request.JobRequest;
import gigjob.model.response.JobDetailResponse;
import gigjob.model.response.JobResponse;
import gigjob.repository.JobRepository;
import gigjob.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping("/v1/job/search")
    public ResponseEntity<List<JobDetailResponse>> getJobListSpec(
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestBody SearchCriteria searchCriteria
    ) {
        Pageable pageable;
        if (searchCriteria.getSortCriteria().getDirection().equalsIgnoreCase("asc")) {
            pageable = PageRequest.of(pageIndex, pageSize, Sort.by(searchCriteria.getSortCriteria().getSortKey()).ascending());
        } else {
            pageable = PageRequest.of(pageIndex, pageSize, Sort.by(searchCriteria.getSortCriteria().getSortKey()).descending());
        }
//        Specification<Job> specification = JobSpecification.isOfficialJob(Integer.parseInt(searchCriteria.getValue()));
//        Page<Job> jobs = jobRepository.findAll(specification, pageable);
        Page<Job> jobs = jobRepository.findAll(pageable);
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

    @GetMapping("/v1/job/shop/{id}")
    public ResponseEntity<Object> getJobsByShopId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                jobService.findJobsByShopId(UUID.fromString(id))
        );
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
