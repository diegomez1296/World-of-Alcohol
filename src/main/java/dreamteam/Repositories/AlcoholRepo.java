package dreamteam.Repositories;

import dreamteam.DAO.Alcohol;
import dreamteam.DAO.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlcoholRepo extends CrudRepository<Alcohol, Long> {
    Alcohol findAlcoholByName(String name);

    List<Alcohol> findAll ();
    List<Alcohol> findAllByUsers(User user);
    Alcohol findAlcoholById(Long id);

}
