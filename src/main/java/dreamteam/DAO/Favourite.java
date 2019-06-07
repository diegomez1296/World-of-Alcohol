package dreamteam.DAO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stringUserId;
    private Long alcohol_id;

    public Favourite(String stringUserId, Long alcohol_id) {
        this.stringUserId = stringUserId;
        this.alcohol_id = alcohol_id;
    }
}
