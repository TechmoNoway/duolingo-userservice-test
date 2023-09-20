package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.mapper.RoleMapper;
import duolingobackenduserservice.model.Role;
import duolingobackenduserservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Override
    public void insertRole(Role role) {
        roleMapper.insertRole(role);
    }
}
