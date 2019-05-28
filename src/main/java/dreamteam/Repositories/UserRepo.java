package dreamteam.Repositories;

import dreamteam.DAO.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findUserByUsername(String name);
}
