package dreamteam.GUIs;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import dreamteam.DAO.Alcohol;
import dreamteam.DAO.AlcoholCard;
import dreamteam.DAO.AlcoholCardDetails;
import dreamteam.DAO.NavBar;
import dreamteam.Repositories.AlcoholRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Route("buy")
@StyleSheet("frontend://styles/style_MainGUI.css")
public class AlcoDetailsGUI extends VerticalLayout {
    private AlcoholRepo alcoholRepo;
    private Alcohol alcohol;
    private NavBar navBar;

    @Autowired
    static final List<Long> nums = new ArrayList<Long>() {};

    @Autowired
    public AlcoDetailsGUI(AlcoholRepo alcoholRepo) {
        this.alcoholRepo = alcoholRepo;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassName("details-gui");
        initComponents();
    }

    private void initComponents() {
        navBar = new NavBar();
        this.add(navBar.getDiv());
        createAlcoCard(2L);

    }

    private void createAlcoCard(Long id) {
        Alcohol alcohol =  alcoholRepo.findAlcoholById(id);
        AlcoholCardDetails cardDetails = new AlcoholCardDetails(alcohol);
        this.add(cardDetails.getDiv());
    }

}
