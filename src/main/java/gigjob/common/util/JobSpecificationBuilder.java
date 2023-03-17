package gigjob.common.util;

import gigjob.common.meta.SearchOperation;
import gigjob.entity.Job;
import gigjob.model.domain.SearchCriteria;
import gigjob.repository.specification.JobSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class JobSpecificationBuilder {
    private final List<SearchCriteria> params;

    public JobSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public final JobSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final JobSpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification<Job> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification<Job> result = new JobSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++) {
            SearchCriteria criteria = params.get(idx);
//            "AND" operation if dataOption is "ALL", "OR" operation if dataOption is "ANY"
            result = SearchOperation.getDataOption(criteria
                    .getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new JobSpecification(criteria))
                    : Specification.where(result).or(new JobSpecification(criteria));
        }
        return result;
    }
}
