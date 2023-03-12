package gigjob.model.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    private String filterKey;
    private String value;
    @Schema(name = "operation", example = "eq", description = "eq: equal" +
            " | lt:less than" +
            " | gt:greater than")
    private String operation;
    private SortCriteria sortCriteria;
}
