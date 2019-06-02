package dreamteam.GUIs;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import dreamteam.DAO.Alcohol;
import dreamteam.DAO.AlcoholCard;
import dreamteam.Repositories.AlcoholRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@Route("")
@StyleSheet("frontend://styles/style_MainGUI.css")
public class MainGUI extends VerticalLayout {

    private AlcoholCard alcoholCard;
    private HorizontalLayout horizontalLayout;
    private AlcoholRepo alcoholRepo;

    @Autowired
    public MainGUI(AlcoholRepo alcoholRepo) {
        this.alcoholRepo = alcoholRepo;

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassName("main-gui");

        initComponents();
    }

    private void initComponents() {
        alcoholCard = new AlcoholCard(alcoholRepo.findAlcoholByName("Malibu kokosowe"), this);



//        Grid<AlcoholCard> grid = new Grid<>(AlcoholCard.class);
//        grid.setItems(Collections.singletonList(alcoholCard));
//        this.add(grid);

//        this.add(alcoholCard.getFavouriteIcon(), alcoholCard.getNameText(), alcoholCard.getPriceText(), alcoholCard.getIsAvailableText(), alcoholCard.getOrderButton());

        //horizontalLayout.add(alcoholCard.setNameText());


    }

}
