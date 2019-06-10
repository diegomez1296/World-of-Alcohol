package dreamteam.API_UnityTest;

import dreamteam.DAO.User;
import dreamteam.Repositories.UserRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApiTest {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private String username;
    private String passwd;

    @Test
    public void logInTest(String username,String passwd){
        User user = userRepo.findUserByUsername(username);


        Assert.assertTrue(passwordEncoder.matches(passwd, user.getPassword()));
    }
}
