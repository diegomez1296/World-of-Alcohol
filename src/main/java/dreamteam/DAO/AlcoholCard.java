package dreamteam.DAO;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.Locale;

@Getter
@Setter
@StyleSheet("frontend://styles/style_AlcoholCard")
public class AlcoholCard {

    private Alcohol alcohol;
    private Label nameText;
    private Label priceText;
    private Label isAvailableText;
    private Button orderButton;
    private Icon favouriteIcon;
    private VerticalLayout verticalLayout;
    private VerticalLayout verticalLayoutDiv;
    private Div div;


    public AlcoholCard(Alcohol alcohol, VerticalLayout verticalLayout) {
        this.alcohol = alcohol;
        nameText = new Label(alcohol.getName());
//        nameText.addClassName("");
        priceText = new Label("$"+String.format(priceFormat(alcohol.getPrice())));
        isAvailableText = new Label(checkAvailable());
        orderButton = new Button("buy");
        favouriteIcon = new Icon(VaadinIcon.PLUS);
        this.verticalLayout = verticalLayout;
        verticalLayoutDiv = new VerticalLayout();

        div = new Div();

        verticalLayoutDiv.add(favouriteIcon,nameText,priceText,isAvailableText,orderButton);
        div.addClassName("div-alco-card");
        div.add(verticalLayoutDiv);

        verticalLayout.add(div);
    }

    private String checkAvailable() {
        if (alcohol.getIsAvailable())
            return "available";
        return "not available";
    }

    private String priceFormat(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }


}
