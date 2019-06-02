package dreamteam.DAO;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NavBar  extends HorizontalLayout {
    private Label projectNameLabel;
    private Button logInButton;
    private Button logOutButton;
    private Button registerButton;
    private Div div;

    public NavBar() {
        projectNameLabel = new Label("World of Alcohol");
        logInButton = new Button("Log In");
        registerButton = new Button("Register");
        this.add(projectNameLabel, logInButton, registerButton);
        div = new Div();
        div.add(this);
    }

    public NavBar(String userName) {
        projectNameLabel = new Label("Hello " + userName);
        logOutButton = new Button("Log Out");
        this.add(projectNameLabel, logOutButton);
        div = new Div();
        div.add(this);
    }
}
