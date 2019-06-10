package dreamteam.Repositories;

import dreamteam.DAO.AOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AOrderRepo extends CrudRepository<AOrder, Long> {
    List<AOrder> findAllByUserId(Long id);
    List<AOrder> findAll();
}
