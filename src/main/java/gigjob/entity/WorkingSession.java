package gigjob.entity;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkingSession implements Serializable {
    // Association class for Worker, Job and Wage
    @EmbeddedId
    private WorkingSessionId id;

}
