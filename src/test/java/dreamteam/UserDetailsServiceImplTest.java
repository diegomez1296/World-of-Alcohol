package dreamteam;

import dreamteam.DAO.Role;
import dreamteam.DAO.TypeOfRole;
import dreamteam.DAO.User;
import dreamteam.Repositories.RoleRepo;
import dreamteam.Repositories.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDetailsServiceImplTest {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    private UserDetailsServiceImpl userDetailsService;

    @Before
    public void setUp() {
        userDetailsService = new UserDetailsServiceImpl(userRepo);
        Role userRole = roleRepo.save(new Role(TypeOfRole.ROLE_ADMIN));
        userRepo.save(new User("mod", "mod"));
    }

    @Test
    public void shouldGetUserByUsername() {
        String name = "mod";
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        assertNotNull(userDetails);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void ifNoCorrectUsername_shouldThrowUsernameNotFoundException() {
        String name = "admin";
        User user = (User) userDetailsService.loadUserByUsername(name);

        if (user == null) {
            throw new UsernameNotFoundException("The user was not found");
        }
    }
}
