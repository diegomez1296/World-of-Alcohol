package dreamteam.RepositoriesTest;

import dreamteam.DAO.Alcohol;
import dreamteam.Repositories.AlcoholRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AlcoholRepoTest {

    @Autowired
    AlcoholRepo alcoholRepo;

    @Test
    public void createAlcoholTest() {
        alcoholRepo.save(new Alcohol("name", 1, 1.99f,"test", "www.test.com"));
        Assert.assertNotNull(alcoholRepo.findAllByName("name"));
    }
}
