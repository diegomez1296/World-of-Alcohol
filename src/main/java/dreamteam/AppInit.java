package dreamteam;

import dreamteam.DAO.Alcohol;
import dreamteam.DAO.Role;
import dreamteam.DAO.TypeOfRole;
import dreamteam.DAO.User;
import dreamteam.Repositories.AlcoholRepo;
import dreamteam.Repositories.OrderRepo;
import dreamteam.Repositories.RoleRepo;
import dreamteam.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AppInit(UserRepo userRepo, RoleRepo roleRepo, AlcoholRepo alcoholRepo, OrderRepo orderRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.alcoholRepo = alcoholRepo;
        this.orderRepo = orderRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        Role adminRole = new Role(TypeOfRole.ROLE_ADMIN);
        Role userRole = new Role(TypeOfRole.ROLE_USER);

        User admin = new User("admin", passwordEncoder.encode("admin"));
        User user = new User("user", passwordEncoder.encode("user"));

        Alcohol zolte = new Alcohol("Żółte najlepsze", 666, 999.90f, "Najlepsze, bo żółte", "www.xxx.com", true);
        Alcohol malibu = new Alcohol("Malibu kokosowe", 100, 49.99f,"Kokosowe z prądem", "www.malibu.com", true);
        Alcohol piwo = new Alcohol("Perła Export", 70, 3.99f,"Sfermentowane drożdże o smaku chmielu", "www.malibu.com", true);
        Alcohol martini = new Alcohol("Martini", 123, 24.99f,"Prawie jak wino", "www.malibu.com", true);
        Alcohol redlabel = new Alcohol("Red Label", 100, 59.99f,"(Nie)Czerwone whisky", "www.malibu.com", true);
        Alcohol grants = new Alcohol("Grant's", 100, 49.99f,"Whisky", "www.malibu.com", true);
        Alcohol carlorosi = new Alcohol("Carlo Rosi", 100, 26.00f,"Wino z tradycją", "www.malibu.com", true);
        Alcohol kadarka = new Alcohol("Kadarka", 100, 9.99f,"Wino na siarczanach", "www.malibu.com", true);

        user.setRoles(Collections.singletonList(userRole));
        admin.setRoles(Collections.singletonList(adminRole));

        userRole.setUsers(Collections.singletonList(user));
        adminRole.setUsers(Collections.singletonList(admin));

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(admin);

        List<Alcohol> alcohols = new ArrayList<>();
        alcohols.add(zolte);
        alcohols.add(malibu);



        zolte.setUsers(users);
        malibu.setUsers(Collections.singletonList(admin));
        user.setFavourites(alcohols);
        admin.setFavourites(Collections.singletonList(zolte));

        userRepo.save(user);
        userRepo.save(admin);

        alcoholRepo.save(zolte);
        alcoholRepo.save(malibu);
        alcoholRepo.save(piwo);
        alcoholRepo.save(redlabel);
        alcoholRepo.save(grants);
        alcoholRepo.save(carlorosi);
        alcoholRepo.save(kadarka);
        alcoholRepo.save(martini);

        roleRepo.save(userRole);
        roleRepo.save(adminRole);

    }
}
