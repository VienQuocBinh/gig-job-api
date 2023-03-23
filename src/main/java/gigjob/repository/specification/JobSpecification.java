package gigjob.repository.specification;

import gigjob.common.meta.SearchOperation;
import gigjob.entity.Job;
import gigjob.entity.JobType;
import gigjob.entity.Shop;
import gigjob.model.domain.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Nonnull;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
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

    public static Specification<Job> getJobsByNearByShop(List<UUID> shopIds) {
        return (root, query, criteriaBuilder) -> {
            // Create a predicate for each shop ID in the list
            List<Predicate> predicates = new ArrayList<>();
            for (UUID uuid : shopIds) {
                predicates.add(criteriaBuilder.equal(
                        shopJoin(root).get("id"), uuid
                ));
            }
            // combine predicate using OR
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

    private static Join<Job, Shop> shopJoin(Root<Job> root) {
        return root.join("shop");
    }

    @Override
    public Predicate toPredicate(@Nonnull Root<Job> root, @Nonnull CriteriaQuery<?> query, @Nonnull CriteriaBuilder criteriaBuilder) {
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();
//        List<Predicate> predicates = new ArrayList<>();
        // Priority SQL condition: 1: Title, 2: jobType
//        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + strToSearch + "%"));

        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
            case EQUAL -> {
                if (searchCriteria.getFilterKey().equals("jobType")) {
                    // check has multiple job types
                    if (strToSearch.contains(",")) {
                        String[] jobTypes = strToSearch.split(",");
                        List<Predicate> jobTypePredicates = new ArrayList<>();
                        for (String jobType : jobTypes) {
                            jobTypePredicates.add(criteriaBuilder.equal(jobTypeJoin(root).get("id"), Integer.parseInt(jobType)));
                        }
                        return criteriaBuilder.or(jobTypePredicates.toArray(new Predicate[0]));
                        // Add OR condition of the job type to the criteria
//                        predicates.add(criteriaBuilder.or(jobTypePredicates.toArray(new Predicate[0])));
                    } else {
                        return criteriaBuilder.equal(jobTypeJoin(root).get("id"), Integer.parseInt(strToSearch));
//                        predicates.add(criteriaBuilder.equal(jobTypeJoin(root).get("id"), Integer.parseInt(strToSearch)));
                    }
                } else if (searchCriteria.getFilterKey().equals("shop")) {
                    // single shop id
//                    predicates.add(criteriaBuilder.equal(shopJoin(root).get("id"), UUID.fromString(strToSearch)));
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
//        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Join<Job, JobType> jobTypeJoin(Root<Job> root) {
        return root.join("jobType");
    }
}
