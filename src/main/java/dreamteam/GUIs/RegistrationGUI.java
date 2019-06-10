package dreamteam.GUIs;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import dreamteam.DAO.Role;
import dreamteam.DAO.TypeOfRole;
import dreamteam.DAO.User;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.RoleRepo;
import dreamteam.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

@Route("register")
public class RegistrationGUI extends VerticalLayout {

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private AlcoholRepo alcoholRepo;
    private PasswordEncoder passwordEncoder;

    private Text textUserRegistration;
    private TextField textFieldUserName;
    private PasswordField passwordFieldPassword;
    private PasswordField passwordFieldConfirmPassword;
    private Button buttonCreateUser;

    @Autowired
    public RegistrationGUI(UserRepo userRepo, RoleRepo roleRepo, AlcoholRepo alcoholRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.alcoholRepo = alcoholRepo;
        this.passwordEncoder = passwordEncoder;

        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        setSizeFull();
        //addClassName("main-gui");

        initComponents();
    }

    private void initComponents() {
        textUserRegistration = new Text("User Registration");
        textFieldUserName = new TextField("Name");
        passwordFieldPassword = new PasswordField("Password");
        passwordFieldConfirmPassword = new PasswordField("Confirm Password");
        buttonCreateUser = new Button("Create");

        buttonCreateUser.addClickListener(buttonClickEvent -> createNewUser(textFieldUserName.getValue(), passwordFieldPassword.getValue(), passwordFieldConfirmPassword.getValue()));

        this.add(textUserRegistration);
        this.add(textFieldUserName);
        this.add(passwordFieldPassword);
        this.add(passwordFieldConfirmPassword);
        this.add(buttonCreateUser);
    }

    private void createNewUser(String name, String password, String conformPassword) {
        boolean isOk = true;

        if(!(name.length() >0)) isOk = false;
        if(!(password.length() >0)) isOk = false;
        if(!(conformPassword.length() >0)) isOk = false;

        if(!isOk) Notification.show("Fields cannot be empty",3000, Notification.Position.TOP_CENTER);

        if(!(password.equals(conformPassword))) {
            isOk = false;
            Notification.show("Passwords must be the same", 3000, Notification.Position.TOP_CENTER);
        }

        if(isOk) {


            List<User> userList = userRepo.findAll();
            Role userRole = roleRepo.findRoleByRoleName(TypeOfRole.ROLE_USER);

            User newUser = new User(name, passwordEncoder.encode(password));
            newUser.setRoles(Collections.singletonList(userRole));
            userList.add(newUser);

            userRole.setUsers(userList);

            userRepo.save(newUser);
            roleRepo.save(userRole);

            Notification.show("You create an account", 3000, Notification.Position.TOP_CENTER);

            buttonCreateUser.getUI().ifPresent(ui -> ui.navigate(""));
        }
    }
}
