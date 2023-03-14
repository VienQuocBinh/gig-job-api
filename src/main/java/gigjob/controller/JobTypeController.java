package gigjob.controller;

import gigjob.model.response.JobTypeResponse;
import gigjob.service.JobTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class JobTypeController {
    private final JobTypeService jobTypeService;

    @GetMapping("/v1/job-type")
    public ResponseEntity<List<JobTypeResponse>> getJobTypeList() {
        return ResponseEntity.status(HttpStatus.OK).body(jobTypeService.getJobTypeResponseList());
    }
}
