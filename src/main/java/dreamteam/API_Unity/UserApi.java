package dreamteam.API_Unity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserApi {

    private String username;
    private String userpasswd;

}
