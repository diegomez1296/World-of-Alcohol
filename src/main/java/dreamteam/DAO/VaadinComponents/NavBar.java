package dreamteam.DAO.VaadinComponents;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
    private Icon favouriteIcon;
    private HorizontalLayout horizontalLayout;
    private Authentication authentication;
    private String currentPrincipalName;
    private Span tooltiptext;
    private Div tooltip;
    private Icon homeIcon;
    private Icon orderListIcon;

    public NavBar() {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        currentPrincipalName = authentication.getName();
        initNavBar();

        if(currentPrincipalName.equals("anonymousUser")) {
            getAnonymousUserNavBar();
        }else {
            getUserNavBar();
        }

    }

    private void getAnonymousUserNavBar(){
        btnDiv.add(logInButton,registerButton);
        horizontalLayout.add(titleLabel, btnDiv);
        div.add(horizontalLayout);
        btnDiv.addClassNames("leftside-div","btn-div");

        logInButton.addClickListener(event -> goToUrl(Constans.APP_URL+"/login"));
        registerButton.addClickListener(event -> registerButton.getUI().ifPresent(ui -> ui.navigate("register")));
    }

    private void getUserNavBar() {
        tooltip.add(userLabel, homeIcon, orderListIcon, favouriteIcon, tooltiptext);
        tooltip.addClassNames("leftside-div");
        btnDiv.add(logOutButton);
        horizontalLayout.add(titleLabel, tooltip, btnDiv);
        div.add(horizontalLayout);
        logOutButton.addClickListener(event -> goToUrl(Constans.APP_URL+"/logout"));
        homeIcon.addClickListener(event -> goToUrl(Constans.APP_URL+"/"));
        favouriteIcon.addClickListener(event -> goToUrl(Constans.APP_URL+"/favourite"));
        orderListIcon.addClickListener(event -> goToUrl(Constans.APP_URL+"/orderlist"));

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
        favouriteIcon = new Icon(VaadinIcon.HEART);
        favouriteIcon.addClassName("favourite-icon");
        homeIcon = new Icon(VaadinIcon.HOME);
        homeIcon.addClassName("home-icon");
        orderListIcon = new Icon(VaadinIcon.LINES_LIST);
        orderListIcon.addClassName("home-icon");
        tooltiptext = new Span("favourites \nalcoholes");
        tooltiptext.addClassName("tooltiptext");
        tooltip = new Div();
        tooltip.addClassName("tooltip");
    }

    public void goToUrl(String url) {
        UI.getCurrent().getPage().executeJavaScript("window.open(\""+ url + "\", \"_self\");");
    }
}
