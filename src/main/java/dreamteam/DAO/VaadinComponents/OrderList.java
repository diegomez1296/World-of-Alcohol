package dreamteam.DAO.VaadinComponents;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import dreamteam.DAO.AOrder;
import dreamteam.DAO.User;
import dreamteam.Repositories.AOrderRepo;
import dreamteam.Repositories.UserRepo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
public class OrderList {
    private int id;
    private String alcoholName;
    private int amount;
    private String price;
    private String summary;
    private String status;

    public OrderList(int id, String alcoholName, int amount, String price, String summary, String status) {
        this.id = id;
        this.alcoholName = alcoholName;
        this.amount = amount;
        this.price = price;
        this.summary = summary;
        this.status = status;
    }
}
