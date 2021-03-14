package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private RoleDao roleDao;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleDao roleDao, UserDao userDao, PasswordEncoder passwordEncoder) {
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(User user, Long[] rolesId) {
        HashSet<Role> roles = new HashSet<>();
        for (Long id : rolesId) {
            roles.add(roleDao.getRoleById(id));
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }

    @Override
    public List<User> listUsers() {
        return userDao.getUsers();
    }

    @Override
    public void remove(Long id) {
        userDao.remove(id);
    }

    @Override
    public void update(User user, Long[] rolesId) {
        Set<Role> roles = new HashSet<>();
        User oldUser = userDao.getUserById(user.getId());
        if (rolesId != null) {
            for (Long id : rolesId) {
                roles.add(roleDao.getRoleById(id));
            }
        } else {
            roles = (Set<Role>) oldUser.getRoles();
        }
        user.setPassword(oldUser.getPassword());
        user.setRoles(roles);
        userDao.update(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByFirstName(String name) {
        return userDao.findByUsername(name);
    }

    @Override
    public boolean checkUserById(Long id) {
        return userDao.checkUserById(id);
    }
}
