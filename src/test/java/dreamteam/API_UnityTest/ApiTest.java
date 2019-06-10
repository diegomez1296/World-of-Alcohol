package dreamteam.API_UnityTest;

import dreamteam.DAO.User;
import dreamteam.Repositories.UserRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApiTest {

    @Autowired
    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Test
    public void apiTest() {

        userRepo.save(new User("user", passwordEncoder.encode("user")));


        String uri = "http://localhost:8080/api/login/user/user";

        RestTemplate restTemplate = new RestTemplate();
        Boolean result = restTemplate.getForObject(uri, Boolean.class);
        Assert.assertEquals(true,result);
    }
}
