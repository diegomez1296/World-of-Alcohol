package dreamteam.DAO;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import dreamteam.General.Constans;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Getter
@Setter
public class NavBar{
    private Label titleLabel;
    private Label userLabel;
    private Button logInButton;
    private Button logOutButton;
    private Button registerButton;
    private Div div;
    private Div btnDiv;
    private HorizontalLayout horizontalLayout;
    private Authentication authentication;
    private String currentPrincipalName;

    public NavBar() {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        currentPrincipalName = authentication.getName();

        initNavBar();

        if(currentPrincipalName == "anonymousUser") {
            getAnonymousUserNavBar();
        }else {
            getUserNavBar();
        }

    }

    public void getAnonymousUserNavBar(){
        btnDiv.add(logInButton,registerButton);
        horizontalLayout.add(titleLabel, btnDiv);
        div.add(horizontalLayout);

        logInButton.addClickListener(event -> goToUrl(Constans.APP_URL+"/login"));
        registerButton.addClickListener(event -> registerButton.getUI().ifPresent(ui -> ui.navigate("register")));
    }

    public void getUserNavBar() {
        btnDiv.add(userLabel, logOutButton);
        horizontalLayout.add(titleLabel, btnDiv);
        div.add(horizontalLayout);
        logOutButton.addClickListener(event -> goToUrl(Constans.APP_URL+"/logout"));
    }

    private void initNavBar() {
        horizontalLayout = new HorizontalLayout();
        titleLabel = new Label("World of Alcohol");
        titleLabel.addClassName("project-title");
        userLabel = new Label(currentPrincipalName);
        userLabel.addClassName("username-navbar-txt");
        btnDiv = new Div();
        btnDiv.addClassNames("btn-div");
        div = new Div();
        div.addClassName("nav-bar");
        logInButton = new Button("Log In");
        registerButton = new Button("Register");
        logOutButton = new Button("Log out");
    }

    private void goToUrl(String url) {
        UI.getCurrent().getPage().executeJavaScript("window.open(\""+ url + "\", \"_self\");");
    }
}
