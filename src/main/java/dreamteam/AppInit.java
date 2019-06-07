package dreamteam;

import dreamteam.DAO.*;
import dreamteam.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AppInit {

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private AlcoholRepo alcoholRepo;
    private OrderRepo orderRepo;
    private FavouriteRepo favouriteRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AppInit(UserRepo userRepo, RoleRepo roleRepo, AlcoholRepo alcoholRepo, OrderRepo orderRepo, FavouriteRepo favouriteRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.alcoholRepo = alcoholRepo;
        this.orderRepo = orderRepo;
        this.favouriteRepo = favouriteRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){

        //region ROLES
        Role adminRole = new Role(TypeOfRole.ROLE_ADMIN);
        Role userRole = new Role(TypeOfRole.ROLE_USER);
        //endregion

        //region USERS
        User admin = new User("admin", passwordEncoder.encode("admin"));
        User user = new User("user", passwordEncoder.encode("user"));
        //endregion

        //region ALCOHOLS
        Alcohol zolte = new Alcohol("Żółte najlepsze", 666, 999.90f, "Najlepsze, bo żółte", "www.xxx.com");
        Alcohol malibu = new Alcohol("Malibu kokosowe", 100, 49.99f,"Kokosowe z prądem", "www.malibu.com");
        Alcohol piwo = new Alcohol("Perła Export", 70, 3.99f,"Sfermentowane drożdże o smaku chmielu", "www.malibu.com");
        Alcohol martini = new Alcohol("Martini", 123, 24.99f,"Prawie jak wino", "www.malibu.com");
        Alcohol redlabel = new Alcohol("Red Label", 100, 59.99f,"(Nie)Czerwone whisky", "www.malibu.com");
        Alcohol grants = new Alcohol("Grant's", 100, 49.99f,"Whisky", "www.malibu.com");
        Alcohol carlorosi = new Alcohol("Carlo Rosi", 100, 26.00f,"Wino z tradycją", "www.malibu.com");
        Alcohol kadarka = new Alcohol("Kadarka", 100, 9.99f,"Wino na siarczanach", "www.malibu.com");
        //endregion

        user.setRoles(Collections.singletonList(userRole));
        admin.setRoles(Collections.singletonList(adminRole));

        userRole.setUsers(Collections.singletonList(user));
        adminRole.setUsers(Collections.singletonList(admin));

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

        roleRepo.save(adminRole);
        roleRepo.save(userRole);

        favouriteRepo.save(new Favourite(admin.getUserId()+"", zolte.getId()));
        favouriteRepo.save(new Favourite(admin.getUserId()+"", malibu.getId()));

    }
}
