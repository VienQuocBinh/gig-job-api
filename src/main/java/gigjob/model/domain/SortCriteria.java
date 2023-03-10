package gigjob.model.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortCriteria {
    @Schema(name = "sortKey", example = "id")
    private String sortKey;
    @Schema(name = "direction", example = "asc", description = "asc | desc")
    private String direction;
}
