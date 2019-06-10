package dreamteam.API_UnityTest;

import dreamteam.API_Unity.Api;
import dreamteam.DAO.Alcohol;
import dreamteam.DAO.Favourite;
import dreamteam.DAO.User;
import dreamteam.General.Constans;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.FavouriteRepo;
import dreamteam.Repositories.UserRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApiTest {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AlcoholRepo alcoholRepo;
    @Autowired
    private FavouriteRepo favouriteRepo;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new Api(new BCryptPasswordEncoder(), userRepo, alcoholRepo, favouriteRepo))
                .build();
    }

    @Test
    public void apiTestLogin() throws Exception {
        //given
        userRepo.save(new User("user", new BCryptPasswordEncoder().encode("user")));
        //when
        mockMvc.perform(get("/api/login/user/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        //then

    }

    @Test
    public void apiTestLogin2() throws Exception {
        //given
        userRepo.save(new User("user", new BCryptPasswordEncoder().encode("user")));
        //when
        mockMvc.perform(get("/api/login/ugser/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        //then

    }

    @Test
    public void apiTestFav() throws Exception {
        User user = userRepo.save(new User("admin","admin"));
        Alcohol alcohol = alcoholRepo.save(new Alcohol("Wóda", 15,12.12f, "Test Alco", "temp_picture"));

        favouriteRepo.save(new Favourite(user.getUserId().toString(),1L));

        mockMvc.perform(get("/api/login/admin/getFav"))
                .andExpect(status().isOk())
                .andExpect(content().string(alcohol.toString() + "|"));

    }
}
