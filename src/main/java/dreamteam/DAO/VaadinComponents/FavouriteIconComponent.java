package dreamteam.DAO.VaadinComponents;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import dreamteam.DAO.Alcohol;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@StyleSheet("frontend://styles/style_AlcoholCard")
public class FavouriteIconComponent {

    private Alcohol alcohol;
    private Icon favouriteIcon;
    private Dialog favouriteIconDialog;
    private boolean isActive;
    private String dialogMessage;

    public FavouriteIconComponent(Alcohol alcohol, boolean isPlus, boolean isActive) {

        if(isPlus) {
            favouriteIcon = new Icon(VaadinIcon.PLUS);
            favouriteIcon.addClassNames("favourite-icon", "icon-enable");
        }
        else {
            favouriteIcon = new Icon(VaadinIcon.MINUS);
            favouriteIcon.addClassNames("favourite-icon", "icon-disable");
        }


        favouriteIcon.setVisible(isActive);
        initDialog();

        dialogMessage = "You have to be logged in to add alcohol to favourite.";
    }

    private void initDialog() {
        favouriteIconDialog = new Dialog();
        favouriteIconDialog.add(new Label("You have to be logged in to add alcohol to favourite."));
        favouriteIconDialog.setWidth("400px");
        favouriteIconDialog.setHeight("120px");
        NativeButton cancelButton = new NativeButton("Cancel", event -> {
            favouriteIconDialog.close();
        });
        cancelButton.addClassName("dialog-cancel-btn");
        favouriteIconDialog.add(cancelButton);
    }
}
