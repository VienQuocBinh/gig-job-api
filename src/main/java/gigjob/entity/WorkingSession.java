package gigjob.entity;

import gigjob.common.embeddedkey.WorkingSessionId;
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
    // Association class for Worker, Job, Session
    @EmbeddedId
    private WorkingSessionId id;

}
