package dreamteam.API_Unity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlcoholClassApi {
    private String name;
    private String description;
    private String image;

    @Override
    public String toString() {
        return name + " " + description + " " + image;
    }
}
