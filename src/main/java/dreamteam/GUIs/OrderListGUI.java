package dreamteam.GUIs;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import dreamteam.DAO.AOrder;
import dreamteam.DAO.Alcohol;
import dreamteam.DAO.User;
import dreamteam.DAO.VaadinComponents.OrderList;
import dreamteam.General.Constans;
import dreamteam.Repositories.AOrderRepo;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.FavouriteRepo;
import dreamteam.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route("orderlist")
@StyleSheet("frontend://styles/style_MainGUI.css")
public class OrderListGUI extends AlcoholGUI {


    public OrderListGUI(UserRepo userRepo, AlcoholRepo alcoholRepo, FavouriteRepo favouriteRepo, AOrderRepo AOrderRepo) {
        super(userRepo, alcoholRepo, favouriteRepo, AOrderRepo);

        addClassName("orderlist-gui");
        //OrderList orderList = new OrderList();
        String currentUser = this.getNavBar().getUserLabel().getText();
        User user = getUserRepo().findUserByUsername(currentUser);
        List<AOrder> orderList = AOrderRepo.findAllByUserId(user.getUserId());
        List<OrderList> tempList = new ArrayList<>();
        Alcohol alcohol;
        double summary;
        String status;
        int i = 1;
        for (AOrder item: orderList) {
            alcohol = getAlcoholRepo().findAlcoholById(item.getAlcoholId());
            summary = alcohol.getPrice()* item.getQuantity();
            if (item.getIsDone())
                status = "sent";
            else
                status = "in progress";

            tempList.add(new OrderList(i, alcohol.getName(),  item.getQuantity(), String.format(Constans.priceFormat(alcohol.getPrice())), String.format(Constans.priceFormat(summary)),status));
//            System.out.println(getAlcoholRepo().findAlcoholById(item.getAlcoholId()));
        }

        Grid<OrderList> grid = new Grid<>(OrderList.class);
        grid.setItems(tempList);
        grid.removeColumnByKey("id");
        grid.setColumns("alcoholName", "price", "amount", "summary","status");
        this.add(grid);


    }
}
