package dreamteam.GUIs;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
@StyleSheet("frontend://styles/style_MainGUI.css")
public class MainGUI extends VerticalLayout {

    private Text startText;
    private Button nextGUIView;

    public MainGUI() {

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassName("main-gui");

        initComponents();
    }

    private void initComponents() {
        startText = new Text("Hello World!");
        nextGUIView = new Button("Next View");

        nextGUIView.addClickListener(buttonClickEvent -> { nextGUIView.getUI().ifPresent(ui -> ui.navigate("NextView"));});


        this.add(startText, nextGUIView);
    }

}
