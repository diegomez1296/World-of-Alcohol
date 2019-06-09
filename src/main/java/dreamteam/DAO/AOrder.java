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

    private Long userId;
    private Long alcoholId;
    private int quantity;
    private Boolean isDone;

    public AOrder(Long userId, Long alcoholId, int quantity, Boolean isDone) {
        this.userId = userId;
        this.alcoholId = alcoholId;
        this.quantity = quantity;
        this.isDone = isDone;
    }
}
