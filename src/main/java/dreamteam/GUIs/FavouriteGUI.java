package dreamteam.GUIs;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import dreamteam.DAO.Alcohol;
import dreamteam.DAO.AlcoholCard;
import dreamteam.DAO.NavBar;
import dreamteam.DAO.User;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@Route("favourite")
@StyleSheet("frontend://styles/style_MainGUI.css")
public class FavouriteGUI extends VerticalLayout {
    private final int AMOUNT_ALCOHOL_IN_ROW = 4;
    private Authentication authentication;
    private String currentPrincipalName;
    private UserRepo userRepo;
    private NavBar navBar;
    private AlcoholRepo alcoholRepo;

    @Autowired
    public FavouriteGUI(AlcoholRepo alcoholRepo, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.alcoholRepo = alcoholRepo;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassName("favour-gui");

        initComponents();
    }

    private void initComponents() {
        navBar = new NavBar();
        this.add(navBar.getDiv());
        createAlcoholCards();

    }

    private void createAlcoholCards() {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        currentPrincipalName=authentication.getName();
        User user = userRepo.findUserByUsername(currentPrincipalName);
        List<Alcohol> alcoholList = user.getFavourites();

        List<HorizontalLayout> horizontalLayoutList = new ArrayList<>();

        int counter = 0;
        HorizontalLayout tmpLayout = new HorizontalLayout();
        for (Alcohol item: alcoholList) {
            if (counter%AMOUNT_ALCOHOL_IN_ROW==0){
                tmpLayout = new HorizontalLayout();
                horizontalLayoutList.add(tmpLayout);
            }
            AlcoholCard tmp = new AlcoholCard(item);
            tmpLayout.add(tmp.getDiv());
            counter++;
        }

        for (HorizontalLayout item: horizontalLayoutList) {
            this.add(item);
        }
    }


}
