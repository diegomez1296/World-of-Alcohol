package dreamteam.DAO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Repository
@NoArgsConstructor
public class StaticList {
    public static final List<Long> tempList = new ArrayList<>();
    
}
