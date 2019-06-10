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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
public class ApiTest {

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
