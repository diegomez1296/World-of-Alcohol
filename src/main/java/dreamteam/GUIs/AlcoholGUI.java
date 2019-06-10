package dreamteam.GUIs;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import dreamteam.DAO.Alcohol;
import dreamteam.DAO.VaadinComponents.AlcoholCard;
import dreamteam.DAO.VaadinComponents.NavBar;
import dreamteam.General.Constans;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.FavouriteRepo;
import dreamteam.Repositories.AOrderRepo;
import dreamteam.Repositories.UserRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static dreamteam.DAO.StaticList.tempList;

@Getter
@Setter
public abstract class AlcoholGUI extends VerticalLayout{

    private UserRepo userRepo;
    private AlcoholRepo alcoholRepo;
    private FavouriteRepo favouriteRepo;
    private AOrderRepo AOrderRepo;
    private List<AlcoholCard> alcoholCardList;
    private NavBar navBar;

    @Autowired
    public AlcoholGUI(UserRepo userRepo, AlcoholRepo alcoholRepo, FavouriteRepo favouriteRepo, AOrderRepo AOrderRepo) {
        this.userRepo = userRepo;
        this.alcoholRepo = alcoholRepo;
        this.favouriteRepo = favouriteRepo;
        this.AOrderRepo = AOrderRepo;

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();

        navBar = new NavBar();
        this.add(navBar.getDiv());

        alcoholCardList = new ArrayList<>();
    }

    public void addAlcoholCards(List<Alcohol> alcoholList) {
        List<HorizontalLayout> horizontalLayoutList = new ArrayList<>();
        int counter = 0;
        HorizontalLayout tmpLayout = new HorizontalLayout();
        for (Alcohol item: alcoholList) {
            if (counter%Constans.AMOUNT_ALCOHOL_IN_ROW==0){
                tmpLayout = new HorizontalLayout();
                horizontalLayoutList.add(tmpLayout);
            }
            AlcoholCard tmp = new AlcoholCard(item);
            alcoholCardList.add(tmp);
            tmpLayout.add(tmp.getDiv());
            counter++;
        }

        for (HorizontalLayout item: horizontalLayoutList) {
            this.add(item);
        }
    }

    public void orderButtonListener(AlcoholCard card) {
        tempList.add(card.getAlcohol().getId());
        getNavBar().goToUrl(Constans.getAPP_URL()+"/order");
    }
}
