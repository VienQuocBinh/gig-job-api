package gigjob.repository.specification;

import gigjob.entity.Job;
import org.springframework.data.jpa.domain.Specification;

public interface JobSpecification {
    static Specification<Job> isOfficialJob(int jobType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("job_type_id"), jobType);
    }
}
