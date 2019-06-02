package dreamteam.DAO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TypeOfRole roleName;

    public Role(TypeOfRole roleName) {
        this.roleName = roleName;
    }

    @ManyToMany
    private List<User> users;

    @Override
    public String getAuthority() {
        return roleName+"";
    }
}
