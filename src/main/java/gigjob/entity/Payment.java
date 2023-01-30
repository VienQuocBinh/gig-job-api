package gigjob.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Payment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, unique = true)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private Double amount;
    private Double tax;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
