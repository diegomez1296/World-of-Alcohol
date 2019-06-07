package dreamteam.Repositories;

import dreamteam.DAO.Role;
import dreamteam.DAO.TypeOfRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {

    List<Role> findAll();

    Role findRoleByRoleName(TypeOfRole roleName);
}
