package Example.Controller;


import Example.Model.ListOfTask;
import Example.Model.TaskOfUser;
import lombok.var;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("task")
public final class TaskRestController {

    private JdbcOperations jdbcOperations;


    /**
     * получение всех задач юзера
     *
     * @return*/
    @GetMapping("task")
    public ResponseEntity<List<ListOfTask>> getTaskList() {
        return ResponseEntity.ok(this.jdbcOperations.query("select * from dailytask where id = ? and dateoftask = ?",
                (resultSet, i) ->
                        new ListOfTask(resultSet.getString("task"))));

    }


    /**
     * создание таски
     **/
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskOfUser> createTask(@RequestBody TaskPayloadController payload) {
        var task = new TaskOfUser();
        jdbcOperations.update("insert into dailytask (id, task, hours, dateoftask) values (?, ?, ?, ?)",
                task.getId().toString(), Timestamp.valueOf(String.valueOf(task.getDateoftask())),
                payload.isDone(), payload.getTask());

        return ResponseEntity.ok(task);
    }

    /**
     * изменение таски
     **/
    @PutMapping(path = "modId", consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Void> modifyTask(@PathVariable UUID modId,
                                           @RequestBody TaskPayloadController payload) {
        if (this.jdbcOperations.update("update dailytask set task = ?, hours = ?, dateoftask = ? " +
                "where id = ?", payload.isDone(), payload.getTask()) == 1) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * удаление таски
     **/

    @DeleteMapping("delId")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID delId) {
        if (this.jdbcOperations.update("delete from dailytask where id = ?",
                delId.toString()) == 1) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
