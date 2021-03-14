package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    List<User> getUsers();

    void remove(Long id);

    void update(User user);

    User getUserById(Long id);

    User findByUsername(String username);

    boolean checkUserById(Long id);
}
