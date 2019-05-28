package dreamteam.Repositories;

import dreamteam.DAO.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
}
