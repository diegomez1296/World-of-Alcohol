package dreamteam.RepositoriesTest;

import dreamteam.DAO.Favourite;
import dreamteam.Repositories.FavouriteRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FavouriteTest {

    @Autowired
    FavouriteRepo favouriteRepo;

    @Test
    public void createFavouriteTest() {
        favouriteRepo.save(new Favourite("1",4L));
        Assert.assertNotNull(favouriteRepo.findAllByStringUserId("1"));
    }
}
