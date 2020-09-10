package Example.Model;


import Example.Form.UserForm;
import lombok.*;
import org.springframework.data.annotation.Id;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "createuser")
@EqualsAndHashCode
public class User {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String lastName;
    private String firstName;
    private String fathName;
    private String email;
    private boolean done;
    @OneToMany(mappedBy = "User", fetch = FetchType.EAGER)
    List<TaskOfUser> taskOfUsers;


    public User(UUID id, String lastName, String firstName, String fathName, String email) {
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public static User form(UserForm form) {
        return User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .fathName(form.getFathName())
                .email(form.getEmail())
                .build();
    }
}
