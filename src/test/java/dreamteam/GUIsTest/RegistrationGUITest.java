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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;

@DataJpaTest
public class RegistrationGUITest {
    private String name = "user";
    private String password = "user";
    private String conformPassword = "user";

//    @Test
//    public void isOkIsTrue(String name, String password, String conformPassword){
//        RegistrationGUI registrationGUI = new RegistrationGUI();
//
//
////        registrationGUI.getTextFieldUserName().setValue("test");
////        registrationGUI.getPasswordFieldPassword().setValue("test");
////        registrationGUI.getPasswordFieldConfirmPassword().setValue("test1");
//
//        //clickButton(registrationGUI.getButtonCreateUser());
////        registrationGUI.getButtonCreateUser().addClickListener(e -> {
////            registrationGUI.getTextFieldUserName().setValue("test");
////            registrationGUI.getPasswordFieldPassword().setValue("test");
////            registrationGUI.getPasswordFieldConfirmPassword().setValue("testtt");
////        });
//
//        //registrationGUI.getButtonCreateUser().click();
//        //Assert.assertEquals(true, registrationGUI.createNewUser(name,password,conformPassword));
//
//
//    }
}
