package gigjob.entity;

import gigjob.common.meta.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@EntityListeners(AuditingEntityListener.class)
public class Account {
    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(updatable = false, unique = true)
//    @Type(type = "org.hibernate.type.UUIDCharType")
//    private UUID id;
    private String id; // Get from Firebase
    @NotNull
    private String username;
    private String password;
    @NotNull
    @Email(message = "Invalid email format")
    private String email;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;
    private boolean isLocked;
    private boolean isDisable;
    @Nullable
    private String image_url;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @OneToOne(mappedBy = "account")
    private Shop shop;
    @OneToOne(mappedBy = "account")
    private Wallet wallet;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Address> addresses;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<FcmToken> fcmTokens;
}
