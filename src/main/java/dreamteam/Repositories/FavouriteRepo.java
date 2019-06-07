package dreamteam.Repositories;

import dreamteam.DAO.Favourite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepo extends CrudRepository<Favourite, Long> {

    List<Favourite> findAllByStringUserId(String user_id);
}
