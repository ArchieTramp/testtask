package Example.Controller;

import Example.Model.User;
import lombok.var;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("user")

public final class UserRestController {

    private JdbcOperations jdbcOperations;

    /**
     * получение списка всех юзеров
     **/
    @GetMapping
    public ResponseEntity<List<User>> getUserList() {
        return ResponseEntity.ok(this.jdbcOperations.query("select * from createuser", (resultSet, i) ->
                new User(UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("lastName"),
                        resultSet.getString("firstName"),
                        resultSet.getString("fathName"),
                        resultSet.getString("email"))));

    }

    /**
     * создание юзера
     **/
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> createNewUser(@RequestBody UserPayloadController payload) {
        var user = new User();
        jdbcOperations.update("insert into createuser (lastname, firstname, fathname, email) values (?, ?, ?, ?)",
                user.getLastName(), user.getFirstName(), user.getFathName(), user.getEmail());

        return ResponseEntity.ok(user);
    }

    /**
     * изменение юзера
     **/

    @PutMapping(path = "modId", consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Void> modifyUser(@PathVariable UUID modId,
                                           @RequestBody UserPayloadController payload) {
        if (this.jdbcOperations.update("update createuser set lastname = ?, firstname = ?, fathname = ?, email = ? " +
                "where id = ?", payload.isDone(), payload.getUser()) == 1) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * удаление юзера
     */

    @DeleteMapping("delId")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID delId) {
        if (this.jdbcOperations.update("delete from createuser where id = ?",
                delId.toString()) == 1) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
