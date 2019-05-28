package dreamteam.DAO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

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
    private float price;
    private String description;
    private String picture;
    private Boolean isAvailable;

    @ManyToMany(mappedBy = "alcohols")
    private List<User> users;

    public Alcohol(String name, int quantity, float price, String description, String picture, Boolean isAvailable) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.picture = picture;
        this.isAvailable = isAvailable;
    }
}
