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
    private AOrderRepo AOrderRepo;
    private FavouriteRepo favouriteRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AppInit(UserRepo userRepo, RoleRepo roleRepo, AlcoholRepo alcoholRepo, AOrderRepo AOrderRepo, FavouriteRepo favouriteRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.alcoholRepo = alcoholRepo;
        this.AOrderRepo = AOrderRepo;
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
        Alcohol zolte = new Alcohol("Fonseca Porto", 666, 99.90f, "This wine shows fresh, black fruit and dark cherry aromas and flavors, with spice notes. This concentrated, structured wine has great volume and mouthfeel, with fine length on the finish.", "www.xxx.com");
        Alcohol malibu = new Alcohol("Malibu kokosowe", 100, 21.99f,"This delicious, refreshing rum can be mixed into many cocktails, making Malibu Lime a winning combination reminiscent of summer fun!", "www.malibu.com");
        Alcohol piwo = new Alcohol("Perła Export", 70, 0.85f,"A refreshing beer that is the quintessence of traditional production methods and the latest solutions used in brewing. It has a distinctive, strong taste, golden color and a subtle scent of aromatic hops. ", "www.malibu.com");
        Alcohol martini = new Alcohol("Martini", 123, 8.49f,"MARTINI IS MORE THAN THE VERMOUTH. IT'S THE STORY OF TWO MEN WITH A VISION. OF A FERTILE LAND WHERE EXPERT WINEMAKING MEETS WILD BOTANICALS. AND OF A LEGACY OF CRAFTSMANSHIP AND EXPERTISE IN EVERY BOTTLE.", "www.malibu.com");
        Alcohol redlabel = new Alcohol("Red Label", 100, 20.99f,"One of the most popular mixed whiskey in the world by Johnnie Walker. Its taste is the result of a harmonious combination of 35 ingredients: both malt and unmalted (grain) malt whiskeys.", "www.malibu.com");
        Alcohol grants = new Alcohol("Grant's", 100, 22.00f,"Many whiskies take their flavour from just one cask, but we mature Grant’s Triple Wood in three different types of wood: Virgin Oak cask provides spicy robustness, American Oak lends subtle vanilla smoothness.", "www.malibu.com");
        Alcohol carlorosi = new Alcohol("Carlo Rossi", 100, 8.00f,"WCarlo Rossi is the largest and most popular wine brand in Poland. Its origins date back to the early 70's, when it made its debut on the American market with great success.", "www.malibu.com");
        Alcohol kadarka = new Alcohol("Captain Morgan", 100, 24.99f,"Captain Morgan Spiced Gold is a Caribbean rum appreciated by experts in good spirits around the world. The meticulously controlled process of triple distillation and aging in Bourbon casks makes the presented drink impresses.", "www.malibu.com");
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

        AOrderRepo.save(new AOrder(user.getUserId(),2L,10,false));
        AOrderRepo.save(new AOrder(user.getUserId(),4L,1,false));
        AOrderRepo.save(new AOrder(user.getUserId(),5L,3,false));

    }
}
