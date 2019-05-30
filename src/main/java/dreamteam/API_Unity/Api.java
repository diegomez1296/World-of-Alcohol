package dreamteam.API_Unity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Api {

    @GetMapping("/login/{username}/{userpasswd}")
    public String logIn(@PathVariable("username") String login, @PathVariable("userpasswd") String passwd)
    {
        //Find favourite alcohol
        StringBuilder builder = new StringBuilder();
        List<AlcoholClassApi> alcoholClassApis = new ArrayList<>();
        alcoholClassApis.add(new AlcoholClassApi("Rum", "Camptain Morgan is the best", "url"));
        alcoholClassApis.add(new AlcoholClassApi("Beer", "Only true chocolate can create a real beer", "url2"));

        for (AlcoholClassApi item : alcoholClassApis) {
            builder.append(item.toString() + System.lineSeparator());
        }


        return builder.toString();
    }
}
