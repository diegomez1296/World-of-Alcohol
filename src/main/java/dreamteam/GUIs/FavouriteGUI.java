package dreamteam.GUIs;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import dreamteam.DAO.*;
import dreamteam.DAO.VaadinComponents.AlcoholCard;
import dreamteam.DAO.VaadinComponents.NavBar;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.FavouriteRepo;
import dreamteam.Repositories.AOrderRepo;
import dreamteam.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@Route("favourite")
@StyleSheet("frontend://styles/style_MainGUI.css")
public class FavouriteGUI extends AlcoholGUI {

    private final int AMOUNT_ALCOHOL_IN_ROW = 4;
    private Authentication authentication;
    private String currentPrincipalName;

    public FavouriteGUI(UserRepo userRepo, AlcoholRepo alcoholRepo, FavouriteRepo favouriteRepo, AOrderRepo AOrderRepo) {
        super(userRepo, alcoholRepo, favouriteRepo, AOrderRepo);
        addClassNames("favour-gui","main-bg");
        createAlcoholCards();
    }

    private void createAlcoholCards() {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        currentPrincipalName=authentication.getName();
        User user = getUserRepo().findUserByUsername(currentPrincipalName);

        List<Favourite> favouriteAlcoList = getFavouriteRepo().findAllByStringUserId(user.getUserId()+"");
        List<Alcohol> alcoholList = new ArrayList<>();

        for (Favourite item :favouriteAlcoList) {
            alcoholList.add(getAlcoholRepo().findAlcoholById(item.getAlcohol_id()));
        }

        super.addAlcoholCards(alcoholList);
    }

}