package dreamteam.DAO.VaadinComponents;

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
import dreamteam.DAO.Alcohol;
import dreamteam.General.Constans;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;

import java.text.DecimalFormat;

@Getter
@Setter
@StyleSheet("frontend://styles/style_AlcoholCard")
public class AlcoholCard {
    private final int AMOUNT_ALCOHOL_IN_ROW = 4;
    private Alcohol alcohol;
    private Label nameLabel;
    private Label priceLabel;
    private Button orderButton;
    private FavouriteIconComponent favouriteIconComponent;
    private VerticalLayout verticalLayoutDiv;
    private Div div;
    private HorizontalLayout mainHorizontalLayout;
    private Authentication authentication;
    private String currentPrincipalName;


    public AlcoholCard(Alcohol alcohol) {
        this.alcohol = alcohol;
        nameLabel = new Label(alcohol.getName());
        nameLabel.addClassName("name-label");
        priceLabel = new Label("$"+String.format(Constans.priceFormat(alcohol.getPrice())));
        orderButton = new Button("buy");
        verticalLayoutDiv = new VerticalLayout();
        div = new Div();
        favouriteIconComponent = new FavouriteIconComponent(alcohol,true, true);
        verticalLayoutDiv.add(favouriteIconComponent.getFavouriteIcon(),nameLabel,priceLabel,orderButton);
        div.addClassName("div-alco-card");
        div.add(verticalLayoutDiv);
        orderButton.addClickListener(event -> goToUrl(Constans.APP_URL+"/order"));

    }

    private void goToUrl(String url) {
        UI.getCurrent().getPage().executeJavaScript("window.open(\""+ url + "\", \"_self\");");
    }
}
