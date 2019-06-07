package dreamteam.API_Unity;

import dreamteam.DAO.Alcohol;
import dreamteam.DAO.Favourite;
import dreamteam.DAO.User;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.FavouriteRepo;
import dreamteam.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Api {

    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    private AlcoholRepo alcoholRepo;
    private FavouriteRepo favouriteRepo;

    @Autowired
    public Api(PasswordEncoder passwordEncoder, UserRepo userRepo, AlcoholRepo alcoholRepo, FavouriteRepo favouriteRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.alcoholRepo = alcoholRepo;
        this.favouriteRepo = favouriteRepo;
    }

    @GetMapping("/login/{username}/{userpasswd}")
    public boolean logIn(@PathVariable("username") String username, @PathVariable("userpasswd") String passwd) {
        try {
            User user = userRepo.findUserByUsername(username);
            return passwordEncoder.matches(passwd, user.getPassword());
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/login/{username}/getFav")
    public Map<String, String> getFavAlco(@PathVariable("username") String username) {

        User user = userRepo.findUserByUsername(username);

        HashMap<String, String> userDataMap = new HashMap<>();
        userDataMap.put("username", user.getUsername());

        List<Favourite> favouriteAlcoList = favouriteRepo.findAllByStringUserId(user.getUserId()+"");
        List<Alcohol> alcoholList = new ArrayList<>();

        for (Favourite item : favouriteAlcoList) {
            alcoholList.add(alcoholRepo.findAlcoholById(item.getAlcohol_id()));
        }

        for (Alcohol item : alcoholList) {
            userDataMap.put(item.getId()+"", item.getName());
        }

        return userDataMap;
    }

    private String formatAlcoList(List<Alcohol> alcoholList) {
        StringBuilder builder = new StringBuilder();
        for (Alcohol item : alcoholList) {
            builder.append(item.toString());
            builder.append("||");
        }
        return builder.toString();
    }
}
