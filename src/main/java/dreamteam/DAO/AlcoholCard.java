package dreamteam.DAO;

import com.vaadin.flow.component.Text;
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
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        verticalLayoutDiv.add(favouriteIcon,nameLabel,priceLabel,orderButton);
        div.addClassName("div-alco-card");
        div.add(verticalLayoutDiv);
    }

    private void initFavouriteIcon() {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        currentPrincipalName = authentication.getName();

        if(currentPrincipalName == "anonymousUser") {
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
        }

    }

    private String priceFormat(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }

}
