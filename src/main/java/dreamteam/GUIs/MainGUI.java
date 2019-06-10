package dreamteam.GUIs;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;
import dreamteam.DAO.Alcohol;
import dreamteam.DAO.Favourite;
import dreamteam.DAO.User;
import dreamteam.DAO.VaadinComponents.AlcoholCard;
import dreamteam.General.Constans;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.FavouriteRepo;
import dreamteam.Repositories.AOrderRepo;
import dreamteam.Repositories.UserRepo;

import java.util.List;

import static dreamteam.DAO.StaticList.tempList;

@Route("")
@StyleSheet("frontend://styles/style_MainGUI.css")
public class MainGUI extends AlcoholGUI {

    public MainGUI(UserRepo userRepo, AlcoholRepo alcoholRepo, FavouriteRepo favouriteRepo, AOrderRepo AOrderRepo) {
        super(userRepo, alcoholRepo, favouriteRepo, AOrderRepo);

        addClassNames("main-gui","main-bg");
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
            card.getOrderButton().addClickListener(btnClickEvent -> {
                tempList.add(card.getAlcohol().getId());
                getNavBar().goToUrl(Constans.getAPP_URL()+"/order");
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
