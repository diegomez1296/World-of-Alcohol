package dreamteam.RepositoriesTest;

import dreamteam.DAO.AOrder;
import dreamteam.DAO.Alcohol;
import dreamteam.Repositories.AOrderRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AOrderRepoTest {

    @Autowired
    AOrderRepo aOrderRepo;

    @Test
    public void createAOrderTest() {
        aOrderRepo.save(new AOrder(4L,2L,5,true));
        Assert.assertNotNull(aOrderRepo.findAllByUserId(4L));
    }
}
