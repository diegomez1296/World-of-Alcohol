package dreamteam.GUIsTest;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import dreamteam.GUIs.RegistrationGUI;
import dreamteam.General.ExampleClass;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.RoleRepo;
import dreamteam.Repositories.UserRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RegistrationGUITest {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private AlcoholRepo alcoholRepo;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void createUser(){
        RegistrationGUI registrationGUI = new RegistrationGUI(userRepo,roleRepo,alcoholRepo, passwordEncoder);
        registrationGUI.createNewUser("userr", "userr", "userr");
        Assert.assertEquals(true, registrationGUI.isOk());
    }
}
