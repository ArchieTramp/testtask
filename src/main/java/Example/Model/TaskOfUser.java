package Example.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "dailytask")
public class TaskOfUser {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String task;
    private int hours;
    private Timestamp dateoftask;
    @ManyToOne
    @JoinColumn(name = "email")
    User user;

    public TaskOfUser(String task) {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
