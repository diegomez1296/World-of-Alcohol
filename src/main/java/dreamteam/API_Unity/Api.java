package dreamteam.API_Unity;

import dreamteam.DAO.Alcohol;
import dreamteam.DAO.User;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.UserRepo;
import dreamteam.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Api {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login/{username}/{userpasswd}")
    public String logIn(@PathVariable("username") String login, @PathVariable("userpasswd") String passwd) {
        try {
            User user = userRepo.findUserByUsername(login);
            if (passwordEncoder.matches(passwd, user.getPassword())) {
                List<Alcohol> alcoholList = userRepo.findUserByUsername(user.getUsername()).getFavourites();
                return formatAlcoList(alcoholList);
            } else return "false";
        } catch (Exception e) {
            return "false";
        }
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
