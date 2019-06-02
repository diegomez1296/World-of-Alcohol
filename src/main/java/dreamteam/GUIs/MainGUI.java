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
import dreamteam.DAO.NavBar;
import dreamteam.Repositories.AlcoholRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route("")
@StyleSheet("frontend://styles/style_MainGUI.css")
public class MainGUI extends VerticalLayout {

    private final int AMOUNT_ALCOHOL_IN_ROW = 4;

    private NavBar navBar;
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

        navBar = new NavBar();
        this.add(navBar.getDiv());

        createAlcoholCards();

    }

    private void createAlcoholCards() {

        List<HorizontalLayout> horizontalLayoutList = new ArrayList<>();
        List<Alcohol> alcoholList = alcoholRepo.findAll();
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
