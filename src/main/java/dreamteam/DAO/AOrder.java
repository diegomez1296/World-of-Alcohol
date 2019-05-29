package dreamteam.DAO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AOrder {

    @Id
    @GeneratedValue
    private Long id;

    private Long user_id;
    private Long alcohol_id;
    private int quantity;
    private Boolean isDone;

    public AOrder(Long user_id, Long alcohol_id, int quantity, Boolean isDone) {
        this.user_id = user_id;
        this.alcohol_id = alcohol_id;
        this.quantity = quantity;
        this.isDone = isDone;
    }
}
