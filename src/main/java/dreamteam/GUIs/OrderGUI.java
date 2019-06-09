package dreamteam.GUIs;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.router.Route;
import dreamteam.DAO.AOrder;
import dreamteam.DAO.Alcohol;
import dreamteam.DAO.OrderCard;
import dreamteam.DAO.User;
import dreamteam.General.Constans;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.FavouriteRepo;
import dreamteam.Repositories.AOrderRepo;
import dreamteam.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static dreamteam.DAO.StaticList.tempList;

@Route("order")
@StyleSheet("frontend://styles/style_MainGUI.css")
public class OrderGUI extends AlcoholGUI {
    private OrderCard cardDetails;
    private Authentication authentication;
    private UserRepo userRepo;

    @Autowired
    private AOrderRepo AOrderRepo;


    @Autowired
    public OrderGUI(UserRepo userRepo, AlcoholRepo alcoholRepo, FavouriteRepo favouriteRepo, AOrderRepo AOrderRepo) {
        super(userRepo, alcoholRepo, favouriteRepo, AOrderRepo);

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassNames("details-gui","main-bg");

        int last = tempList.size() - 1;
        createAlcoCard(tempList.get(last));

    }


    private void createAlcoCard(Long id) {
        Alcohol alcohol =  getAlcoholRepo().findAlcoholById(id);
        cardDetails = new OrderCard(alcohol);
        this.add(cardDetails.getDiv());

        cardDetails.getConfirmButton().addClickListener(btnClickEvent -> {
            addOrderToDB(id);
        });
    }

    private void addOrderToDB(Long alcoId) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = this.getNavBar().getUserLabel().getText();
        User user = getUserRepo().findUserByUsername(currentPrincipalName);

        Long userId = user.getUserId();
        AOrderRepo.save(new AOrder(userId, alcoId, Integer.parseInt(cardDetails.getAmountField().getValue()), false));
        initDialog();
    }

    private void initDialog() {
        Dialog dialog = new Dialog();
        dialog.add(new Label("The order has been successfully sent!"));

        dialog.setWidth("400px");
        dialog.setHeight("130px");
        NativeButton cancelButton = new NativeButton("check order", event -> goToUrl(Constans.APP_URL+"/orderlist"));
        cancelButton.addClassName("dialog-cancel-btn");
        dialog.add(cancelButton);
        dialog.open();
    }
    public void goToUrl(String url) {
        UI.getCurrent().getPage().executeJavaScript("window.open(\""+ url + "\", \"_self\");");
    }
}
