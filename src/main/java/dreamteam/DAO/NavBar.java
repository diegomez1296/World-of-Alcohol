package dreamteam.DAO;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NavBar{
    private Label projectNameLabel;
    private Button logInButton;
    private Button logOutButton;
    private Button registerButton;
    private Div div;
    private Div btnDiv;
    private HorizontalLayout horizontalLayout;

    public NavBar() {
        horizontalLayout = new HorizontalLayout();
        projectNameLabel = new Label("World of Alcohol");
        projectNameLabel.addClassName("project-title");
        btnDiv = new Div();
        btnDiv.addClassNames("btn-div");
        logInButton = new Button("Log In");
        registerButton = new Button("Register");
        btnDiv.add(logInButton,registerButton);

        horizontalLayout.add(projectNameLabel, btnDiv);
        div = new Div();
        div.addClassName("nav-bar");
        div.add(horizontalLayout);

        logInButton.addClickListener(buttonClickEvent -> { logInButton.getUI().ifPresent(ui -> ui.navigate("login"));});
        registerButton.addClickListener(buttonClickEvent -> { registerButton.getUI().ifPresent(ui -> ui.navigate("register"));});
    }

    public NavBar(String userName) {
        horizontalLayout = new HorizontalLayout();
        projectNameLabel = new Label("Hello " + userName);
        logOutButton = new Button("Log Out");
        horizontalLayout.add(projectNameLabel, logOutButton);
        div = new Div();
        div.add(horizontalLayout);
    }
}
