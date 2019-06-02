package dreamteam.GUIs;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("nextView")
public class SecondGUI extends VerticalLayout {

    private Text text;
    private Icon icon;
    private TextArea textArea;
    private Button button;

    public SecondGUI() {

//        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
//        setSizeFull();
//        addClassName("main-gui");

        initComponents();
    }

    private void initComponents() {
        button = new Button("CLICK");

        button.addClickListener(buttonClickEvent -> {UI.getCurrent().getPage().executeJavaScript("window.open(\"http://127.0.0.1:8080/login\", \"_self\");");});

        this.add(button);
    }
}
