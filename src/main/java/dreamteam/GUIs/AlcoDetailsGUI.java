package dreamteam.GUIs;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.router.Route;
import dreamteam.DAO.Alcohol;
import dreamteam.DAO.AlcoholCardDetails;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.FavouriteRepo;
import dreamteam.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route("buy")
@StyleSheet("frontend://styles/style_MainGUI.css")
public class AlcoDetailsGUI extends AlcoholGUI {
    private Alcohol alcohol;

    @Autowired
    static final List<Long> nums = new ArrayList<Long>() {};

    @Autowired
    public AlcoDetailsGUI(UserRepo userRepo, AlcoholRepo alcoholRepo, FavouriteRepo favouriteRepo) {
        super(userRepo, alcoholRepo, favouriteRepo);

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassName("details-gui");


        createAlcoCard(2L);
    }


    private void createAlcoCard(Long id) {
        Alcohol alcohol =  getAlcoholRepo().findAlcoholById(id);
        AlcoholCardDetails cardDetails = new AlcoholCardDetails(alcohol);
        this.add(cardDetails.getDiv());
    }

}
