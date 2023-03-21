package gigjob.repository.specification;

import gigjob.common.meta.SearchOperation;
import gigjob.entity.Job;
import gigjob.entity.JobType;
import gigjob.entity.Shop;
import gigjob.model.domain.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Nonnull;
import javax.persistence.criteria.*;
import java.util.Objects;
import java.util.UUID;

public class JobSpecification implements Specification<Job> {
    private final SearchCriteria searchCriteria;

    public JobSpecification(final SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    public static Specification<Job> getJobsByTitleSpec(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    @Override
    public Predicate toPredicate(@Nonnull Root<Job> root, @Nonnull CriteriaQuery<?> query, @Nonnull CriteriaBuilder criteriaBuilder) {
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();
        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
            case EQUAL -> {
                if (searchCriteria.getFilterKey().equals("jobType")) {
                    return criteriaBuilder.equal(jobTypeJoin(root).get("id"), Integer.parseInt(strToSearch));
                } else if (searchCriteria.getFilterKey().equals("shop")) {
                    return criteriaBuilder.equal(shopJoin(root).get("id"), UUID.fromString(strToSearch));
                }
            }
            case CONTAINS -> {
                if (searchCriteria.getFilterKey().equals("title")) {
                    return criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + strToSearch + "%");
                }
            }
            // Other cases
            // ...............................

            // ...............................
            case ALL -> {
                // all record by title
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + strToSearch + "%");
            }
        }
        return criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + strToSearch + "%");
    }

    private Join<Job, JobType> jobTypeJoin(Root<Job> root) {
        return root.join("jobType");
    }

    private Join<Job, Shop> shopJoin(Root<Job> root) {
        return root.join("shop");
    }
}
