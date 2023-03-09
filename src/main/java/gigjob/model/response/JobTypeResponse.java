package gigjob.model.response;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobTypeResponse implements Serializable {
    private Long id;
    private String name;
}
