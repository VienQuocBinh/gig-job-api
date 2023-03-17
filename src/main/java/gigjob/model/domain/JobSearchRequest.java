package gigjob.model.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSearchRequest {
    private List<SearchCriteria> searchCriteriaList;
    @Schema(description = "\"AND\" operation if dataOption is \"ALL\", \"OR\" operation if dataOption is \"ANY\"")
    // "AND" operation if dataOption is "ALL", "OR" operation if dataOption is "ANY"
    private String dataOption;
//    @Schema(description = "The search key words that find the jobs have the similar title to the search key")
//    private String searchKey;
}
