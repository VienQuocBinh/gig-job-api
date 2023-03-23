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
    private SortCriteria sortCriteria;
    // "AND" operation if dataOption is "ALL", "OR" operation if dataOption is "ANY"
    @Schema(description = "\"all\" operation if dataOption is \"AND\", \"any\" operation if dataOption is \"OR\""
            , defaultValue = "any")
    private String dataOption;
    private double latitude;
    private double longitude;
}
