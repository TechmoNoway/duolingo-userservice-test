package duolingobackenduserservice.service;


import duolingobackenduserservice.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    void insertRole(Role role);
}
