package dreamteam.Repositories;

import dreamteam.DAO.AOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends CrudRepository<AOrder, Long> {
}
