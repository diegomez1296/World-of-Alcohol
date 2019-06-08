package dreamteam.GUIs;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import dreamteam.DAO.*;
import dreamteam.DAO.VaadinComponents.AlcoholCard;
import dreamteam.DAO.VaadinComponents.NavBar;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.FavouriteRepo;
import dreamteam.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route("")
@StyleSheet("frontend://styles/style_MainGUI.css")
public class MainGUI extends AlcoholGUI {


    public MainGUI(UserRepo userRepo, AlcoholRepo alcoholRepo, FavouriteRepo favouriteRepo) {
        super(userRepo, alcoholRepo, favouriteRepo);

        addClassName("main-gui");

        createAlcoholCards();
    }

    private void createAlcoholCards() {

        List<Alcohol> alcoholList = getAlcoholRepo().findAll();

        super.addAlcoholCards(alcoholList);

        initAlcoholCardsListeners(this.getAlcoholCardList());
    }

    private void initAlcoholCardsListeners(List<AlcoholCard> alcoholCardList) {

        for (AlcoholCard card : alcoholCardList) {
            card.getFavouriteIconComponent().getFavouriteIcon().addClickListener(iconClickEvent -> {

                String currentPrincipalName = this.getNavBar().getUserLabel().getText();

                if(currentPrincipalName.equals("anonymousUser")) {
                    card.getFavouriteIconComponent().getFavouriteIconDialog().open();
                }
                else {
                    addFavourToDB(card.getAlcohol(), currentPrincipalName);
                }
            });
        }
    }

    private void addFavourToDB(Alcohol alcohol, String currentPrincipalName) {
        String dialogText;
        boolean isFavourite = false;
        User user = getUserRepo().findUserByUsername(currentPrincipalName);
        List<Favourite> favouriteAlcoList = getFavouriteRepo().findAllByStringUserId(user.getUserId()+"");

        for (Favourite item : favouriteAlcoList) {
            if (alcohol.getId() == item.getAlcohol_id()){
                isFavourite = true;
                dialogText = alcohol.getName() + " is already in your favourites!";
                initDialog(dialogText);
            }
        }
        if (!isFavourite){
            getFavouriteRepo().save(new Favourite(user.getUserId()+"", alcohol.getId()));
            dialogText = "The alcohol was added successfully!";
            initDialog(dialogText);
        }
    }

    private void initDialog(String txt) {
        Dialog dialog = new Dialog();
        dialog.add(new Label(txt));

        dialog.setWidth("400px");
        dialog.setHeight("30px");

        dialog.open();
    }

}
