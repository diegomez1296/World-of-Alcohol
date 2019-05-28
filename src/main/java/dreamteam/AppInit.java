package dreamteam;

import dreamteam.DAO.Alcohol;
import dreamteam.DAO.Role;
import dreamteam.DAO.User;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.OrderRepo;
import dreamteam.Repositories.RoleRepo;
import dreamteam.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AppInit {

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private AlcoholRepo alcoholRepo;
    private OrderRepo orderRepo;

    @Autowired
    public AppInit(UserRepo userRepo, RoleRepo roleRepo, AlcoholRepo alcoholRepo, OrderRepo orderRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.alcoholRepo = alcoholRepo;
        this.orderRepo = orderRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        User user1 = new User("user1","user1");
        User user2 = new User("admin", "admin");

        Alcohol zolte = new Alcohol("Żółte najlepsze", 666, 999.9f, "Najlepsze, bo żółte", "www.xxx.com", true);
        Alcohol malibu = new Alcohol("Malibu kokosowe", 100, 49.99f,"Kokosowe z prądem", "www.malibu.com", true);

        user1.setRoles(Collections.singletonList(user));
        user2.setRoles(Collections.singletonList(admin));

        user.setUsers(Collections.singletonList(user1));
        admin.setUsers(Collections.singletonList(user2));

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        List<Alcohol> alcohols = new ArrayList<>();
        alcohols.add(zolte);
        alcohols.add(malibu);

//        user1.setAlcohols(alcohols);
//        user2.setAlcohols(Collections.singletonList(zolte));
//
//        zolte.setUsers(users);
//        malibu.setUsers(Collections.singletonList(user1));

        userRepo.save(user1);
        userRepo.save(user2);

        roleRepo.save(user);
        roleRepo.save(admin);

        alcoholRepo.save(zolte);
        alcoholRepo.save(malibu);


    }
}
