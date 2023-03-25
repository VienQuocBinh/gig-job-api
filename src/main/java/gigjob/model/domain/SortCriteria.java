package gigjob.model.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortCriteria {
    @Schema(description = "The criteria for sorting: createdDate, id",
            example = "id",
            defaultValue = "id")
    private String sortKey;
    @Schema(example = "asc",
            description = "Sort direction: asc | desc",
            defaultValue = "asc")
    private String direction;
}
