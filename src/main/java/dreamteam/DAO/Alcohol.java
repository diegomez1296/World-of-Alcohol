package dreamteam.DAO;

import dreamteam.General.Constans;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Alcohol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;
    private double price;
    private String description;
    private String picture;

    public Alcohol(String name, int quantity, float price, String description, String picture) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.picture = picture;
    }

    @Override // * -> for Unity
    public String toString() {
        return id +"*"+ name +"*"+ description + "*" + String.format(Constans.priceFormat(price));
    }
}
