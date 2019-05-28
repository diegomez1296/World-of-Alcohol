package dreamteam.API_Unity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Api {

    @GetMapping("/login/{username}/{userpasswd}")
    public String logIn(@PathVariable("username") String login, @PathVariable("userpasswd") String passwd)
    {
        return login + " || " + passwd;
    }
}
