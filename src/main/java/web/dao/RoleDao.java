package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {

    public List<Role> getRoles();

    public Role getRoleById(Long id);
}
