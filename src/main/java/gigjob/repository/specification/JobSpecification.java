package gigjob.repository.specification;

import gigjob.entity.Job;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface JobSpecification {
    /**
     * Returns the job specification for getting the jobs having the specified job type
     *
     * @param jobType {@code int}
     * @return {@code Specification<Job>}
     * @author Vien Binh
     */
    static Specification<Job> isOfficialJob(int jobType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("jobType").get("id"), jobType);
    }

    /**
     * Returns the job specification for getting the jobs having a title is similar to the search term
     *
     * @param title {@code String}
     * @return {@code Specification<Job>}
     * @author Vien Binh
     */
    static Specification<Job> searchByTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get("title"), "%" + title + "%");
    }

    static Specification<Job> test() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(
                    root
                            .get("jobType")
                            .get("id"), 1
            ));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
