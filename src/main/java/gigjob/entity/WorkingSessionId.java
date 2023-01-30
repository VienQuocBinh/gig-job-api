package gigjob.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class WorkingSessionId implements Serializable {
    // Association id for Association clas WorkingSessionId
//    @Column(name = "worker_id")
//    @Type(type = "org.hibernate.type.UUIDCharType")
//    private UUID workerId;
//    @Column(name = "job_id")
//    private Long jobId;
//    @Column(name = "wage_id")
//    private Long wageId;
//    @Column(name = "session_id")
//    private Long sessionId;
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "wage_id")
    private Wage wage;
}
