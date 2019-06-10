package dreamteam.API_UnityTest;

import dreamteam.DAO.Alcohol;
import dreamteam.DAO.Favourite;
import dreamteam.DAO.User;
import dreamteam.General.Constans;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.FavouriteRepo;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApiTest {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FavouriteRepo favouriteRepo;
    @Autowired
    private AlcoholRepo alcoholRepo;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void apiTestLogin() {
        String uri = Constans.getAPP_URL()+"/api/login/user/user";

        RestTemplate restTemplate = new RestTemplate();
        Boolean result = restTemplate.getForObject(uri, Boolean.class);
        Assert.assertEquals(true,result);
    }

//    @Test
//    public void apiTestFav(){
//        String uri = Constans.getAPP_URL()+"/api/login/admin/getFav";
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
//        User user = userRepo.findUserByUsername("admin");
//
//
//        List<Favourite> favourite = favouriteRepo.findAllByStringUserId(user.getUserId()+"");
//        List<Alcohol> alcoholList = new ArrayList<>();
//
//        for (Favourite item : favourite) {
//            alcoholList.add(alcoholRepo.findAlcoholById(item.getAlcohol_id()));
//        }
//
//        StringBuilder builder = new StringBuilder();
//        for (Alcohol item : alcoholList) {
//            builder.append(item.toString());
//            builder.append("|");
//        }
//
//        String resultFav = builder.toString();
//
//        Assert.assertEquals(resultFav, result);
//
//    }
}
