package dreamteam.RepositoriesTest;

import dreamteam.DAO.Role;
import dreamteam.DAO.TypeOfRole;
import dreamteam.Repositories.RoleRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepoTest {

    @Autowired
    RoleRepo roleRepo;

    @Test
    public void createRoleTest() {
        roleRepo.save(new Role(TypeOfRole.ROLE_USER));
        Assert.assertNotNull(roleRepo.findRoleByRoleName(TypeOfRole.ROLE_USER));
    }
}
