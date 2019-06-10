package dreamteam.Repositories;

import dreamteam.DAO.Alcohol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlcoholRepo extends CrudRepository<Alcohol, Long> {

    Alcohol findAllByName(String s);
    Alcohol findAlcoholById (Long id);
    List<Alcohol> findAll ();



}
