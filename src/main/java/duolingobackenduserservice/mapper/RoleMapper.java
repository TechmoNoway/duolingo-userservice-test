package duolingobackenduserservice.mapper;

import duolingobackenduserservice.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<Role> getAllRoles();
    void insertRole(Role role);
}
