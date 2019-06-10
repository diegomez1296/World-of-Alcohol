package dreamteam.RepositoriesTest;

import dreamteam.DAO.User;
import dreamteam.Repositories.UserRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepoTest {

    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @Test
    public void createUserTest() {
        userRepo.save(new User("user", passwordEncoder.encode("user")));
        Assert.assertNotNull(userRepo.findUserByUsername("user"));
    }
}
