package dreamteam.Repositories;

import dreamteam.DAO.Alcohol;
import dreamteam.DAO.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlcoholRepo extends CrudRepository<Alcohol, Long> {
    Alcohol findAlcoholByName(String name);

    List<Alcohol> findAll ();
    List<Alcohol> findAllByUsers(User user);


}
