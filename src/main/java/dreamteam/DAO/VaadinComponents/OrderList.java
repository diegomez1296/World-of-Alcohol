package dreamteam.DAO.VaadinComponents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
