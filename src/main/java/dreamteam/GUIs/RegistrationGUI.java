package dreamteam.GUIs;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import dreamteam.DAO.User;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.RoleRepo;
import dreamteam.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Route("Registration")
public class RegistrationGUI extends VerticalLayout {

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private AlcoholRepo alcoholRepo;
    private PasswordEncoder passwordEncoder;

    private Text textUserRegistration;
    private TextField textFieldUserName;
    private PasswordField PasswordFieldPassword;
    private PasswordField PasswordFieldConfirmPassword;
    private Button buttonCreateUser;

    @Autowired
    public RegistrationGUI(UserRepo userRepo, RoleRepo roleRepo, AlcoholRepo alcoholRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.alcoholRepo = alcoholRepo;
        this.passwordEncoder = passwordEncoder;

        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        setSizeFull();
        addClassName("main-gui");

        initComponents();
    }

    private void initComponents() {
        textUserRegistration = new Text("User Registration");
        textFieldUserName = new TextField("Name");
        PasswordFieldPassword = new PasswordField("Password");
        PasswordFieldConfirmPassword = new PasswordField("Confirm Password");
        buttonCreateUser = new Button("Create");

        // Filtering the correct registration - to fix
        buttonCreateUser.addClickListener(buttonClickEvent -> {
            if (emptyCheck(textFieldUserName) || emptyCheck(PasswordFieldPassword) || emptyCheck(PasswordFieldConfirmPassword)) {
                if (emptyCheck(PasswordFieldPassword) || emptyCheck(PasswordFieldConfirmPassword) && PasswordFieldPassword.getValue().equals(PasswordFieldConfirmPassword.getValue())) {

                    //userRepo.save(new User(textFieldUserName.getValue(), passwordEncoder.encode(PasswordFieldPassword.getValue())));

                    // Add User Role

                    // Add User empty Alcohol List

                    Notification.show("Poprawne dane logowania", 1000, Notification.Position.TOP_CENTER);

                    //buttonCreateUser.getUI().ifPresent(ui -> ui.navigate("NextView"));
                } else {
                    Notification.show("Podane hasła nie są takie same", 1000, Notification.Position.TOP_CENTER);
                }
            } else {
                Notification.show("Jedno z pól jest puste",1000, Notification.Position.TOP_CENTER);
            }
        });

        this.add(textUserRegistration);
        this.add(textFieldUserName);
        this.add(PasswordFieldPassword);
        this.add(PasswordFieldConfirmPassword);
        this.add(buttonCreateUser);
    }

    private boolean emptyCheck(Component component){
        //if (component.getElement().getText().length() > 0){
        if (component.toString().length() > 0){
            return true;
        }
        return false;
    }
}
