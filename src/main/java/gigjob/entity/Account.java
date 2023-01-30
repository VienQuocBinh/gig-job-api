package gigjob.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@EntityListeners(AuditingEntityListener.class)
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, unique = true)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String username;
    private String password;
    @Email(message = "Invalid email format")
    private String email;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;
    private boolean isLocked;
    private boolean isDisable;

    @OneToOne(mappedBy = "account")
    private Shop shop;

    @OneToOne(mappedBy = "account")
    private Wallet wallet;
}
