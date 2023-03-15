package gigjob.service.impl;

import gigjob.common.exception.model.InternalServerErrorException;
import gigjob.model.response.JobTypeResponse;
import gigjob.repository.JobTypeRepository;
import gigjob.service.JobTypeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobTypeServiceImpl implements JobTypeService {
    private final ModelMapper modelMapper;
    private final JobTypeRepository jobTypeRepository;

    @Override
    public List<JobTypeResponse> getJobTypeResponseList() {
        try {
            return jobTypeRepository.findAll().stream()
                    .map(jobType -> modelMapper.map(jobType, JobTypeResponse.class))
                    .toList();
        } catch (Exception exception) {
            throw new InternalServerErrorException(exception.getMessage());
        }
    }
}
