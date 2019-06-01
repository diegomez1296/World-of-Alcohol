package dreamteam.GUIs;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("nextView")
public class SecondGUI extends VerticalLayout {

    private Text text;

    public SecondGUI() {

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassName("main-gui");

        initComponents();
    }

    private void initComponents() {
        text = new Text("Hello World!");

        this.add(text);
    }
}
