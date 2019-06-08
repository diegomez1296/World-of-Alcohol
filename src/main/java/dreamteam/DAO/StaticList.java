package dreamteam.DAO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Repository
public class StaticList {
    public static final List<Long> tempList = new ArrayList<>();

    public StaticList() {
    }
}
