package gigjob.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    @Schema(description = "The name of the field that used to filter",
            example = "jobType")
    private String filterKey;
    @Schema(description = "The value to filter", example = "1")
    private Object value;
    @Schema(example = "eq",
            description = "How to filter:" +
                    " eq: equal" +
                    " | lt: less than" +
                    " | gt: greater than",
            defaultValue = "eq")
    private String operation;
    // "AND" operation if dataOption is "ALL", "OR" operation if dataOption is "ANY"
    @JsonIgnore
    private String dataOption;


    public SearchCriteria(String filterKey, String operation, Object value) {
        super();
        this.filterKey = filterKey;
        this.operation = operation;
        this.value = value;
    }
}
