package gigjob.model.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    @Schema(description = "The search key words that find the jobs have the similar title to the search key")
    private String searchKey;
    @Schema(description = "The name of the field that used to filter",
            example = "jobType")
    private String filterKey;
    @Schema(description = "The value to filter", example = "1")
    private String value;
    @Schema(example = "eq",
            description = "How to filter:" +
                    " eq: equal" +
                    " | lt: less than" +
                    " | gt: greater than",
            defaultValue = "eq")
    private String operation;
    private SortCriteria sortCriteria;
}
