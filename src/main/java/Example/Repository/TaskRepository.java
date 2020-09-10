package Example.Repository;

import Example.Model.TaskOfUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskOfUser, Long> {
}
