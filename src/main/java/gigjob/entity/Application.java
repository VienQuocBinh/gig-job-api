package gigjob.entity;

import gigjob.common.embeddedkey.ApplicationId;
import gigjob.common.meta.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application implements Serializable {
    @EmbeddedId
    private ApplicationId id;
    @Enumerated(EnumType.ORDINAL)
    private ApplicationStatus status;
}
