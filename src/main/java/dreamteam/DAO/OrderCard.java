package dreamteam.DAO;

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
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import dreamteam.General.Constans;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.OrderRepo;
import dreamteam.Repositories.UserRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.DecimalFormat;
import java.util.Collections;

@Getter
@Setter
@StyleSheet("frontend://styles/style_AlcoholCard")
public class OrderCard {
    private final int AMOUNT_ALCOHOL_IN_ROW = 4;
    private Alcohol alcohol;
    private Label nameLabel;
    private Label priceLabel;
    private Label detailsLabel;
    private Button confirmButton;
    private TextField amountField;

    private VerticalLayout verticalLayoutDiv;
    private Div div;
    private HorizontalLayout horizontalLayout;
    private Authentication authentication;
    private String currentPrincipalName;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderRepo orderRepo;



    public OrderCard(Alcohol alcohol) {
        this.alcohol = alcohol;
        nameLabel = new Label(alcohol.getName());
        nameLabel.addClassName("name-label");
        detailsLabel = new Label(alcohol.getDescription());
        detailsLabel.addClassName("details-label");
        priceLabel = new Label("$"+String.format(priceFormat(alcohol.getPrice())) + " * amount:");
        priceLabel.addClassName("price-label");
        confirmButton = new Button("buy");
        verticalLayoutDiv = new VerticalLayout();
        horizontalLayout = new HorizontalLayout();
        div = new Div();
        amountField = new TextField();
        amountField.setValue("1");
        amountField.addClassName("amount-field");

        authentication = SecurityContextHolder.getContext().getAuthentication();
        currentPrincipalName = authentication.getName();

        horizontalLayout.add(priceLabel, amountField, confirmButton);
        verticalLayoutDiv.add(nameLabel, detailsLabel,horizontalLayout);
        div.addClassName("alco-details-div");
        div.add(verticalLayoutDiv);
    }


    private String priceFormat(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }

}
