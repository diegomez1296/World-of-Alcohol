package dreamteam.DAO;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import dreamteam.General.Constans;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.UserRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@StyleSheet("frontend://styles/style_AlcoholCard")
public class AlcoholCard {
    private final int AMOUNT_ALCOHOL_IN_ROW = 4;
    private Alcohol alcohol;
    private Label nameLabel;
    private Label priceLabel;
    private Button orderButton;
    private Icon favouriteIcon;
    private VerticalLayout verticalLayoutDiv;
    private Div div;
    private HorizontalLayout mainHorizontalLayout;
    private Authentication authentication;
    private String currentPrincipalName;
    static final List<Long> nums = new ArrayList<Long>() {};



    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AlcoholRepo alcoholRepo;



    public AlcoholCard(Alcohol alcohol) {
        this.alcohol = alcohol;
        nameLabel = new Label(alcohol.getName());
        nameLabel.addClassName("name-label");
        priceLabel = new Label("$"+String.format(priceFormat(alcohol.getPrice())));
        orderButton = new Button("buy");
        favouriteIcon = new Icon(VaadinIcon.PLUS);
        verticalLayoutDiv = new VerticalLayout();
        div = new Div();

        initFavouriteIcon();

        orderButton.addClickListener(event ->
            goToUrl(Constans.APP_URL+"/buy")
        );
        verticalLayoutDiv.add(favouriteIcon,nameLabel,priceLabel,orderButton);
        div.addClassName("div-alco-card");
        div.add(verticalLayoutDiv);
    }
    private void goToUrl(String url) {
        UI.getCurrent().getPage().executeJavaScript("window.open(\""+ url + "\", \"_self\");");
    }
    private void initFavouriteIcon() {
        if(getCurrentUserName() == "anonymousUser") {
            favouriteIcon.addClassNames("favourite-icon","icon-disable");
            Dialog dialog = new Dialog();
            dialog.add(new Label("You have to be logged in to add alcohol to favourite."));
            dialog.setWidth("400px");
            dialog.setHeight("120px");
            NativeButton cancelButton = new NativeButton("Cancel", event -> {
                dialog.close();
            });
            cancelButton.addClassName("dialog-cancel-btn");
            dialog.add(cancelButton);
            favouriteIcon.addClickListener(event -> dialog.open());
        }else {
            favouriteIcon.addClassNames("favourite-icon","icon-enable");
            favouriteIcon.addClickListener(event -> addFavourToDB());
        }

    }

    // TUTAJ NIE DZIAŁA :(
    private void addFavourToDB() {
        User user = userRepo.findUserByUsername(getCurrentUserName());

        Alcohol alcohol = this.alcohol;
        alcohol.setUsers(Collections.singletonList(user));
        user.setFavourites(Collections.singletonList(alcohol));
        userRepo.save(user);
        alcoholRepo.save(alcohol);
    }

    private String getCurrentUserName(){
        authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    private String priceFormat(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }

}
