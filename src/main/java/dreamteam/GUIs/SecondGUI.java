package dreamteam.GUIs;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletResponse;
import com.vaadin.flow.server.VaadinSession;
import sun.plugin.javascript.navig.Link;

import javax.annotation.Resource;

import static sun.plugin.javascript.navig.JSType.URL;

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
