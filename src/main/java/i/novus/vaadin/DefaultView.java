package i.novus.vaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class DefaultView extends VerticalLayout {
    public DefaultView() {
        UI.getCurrent().navigate("/xml-to-json");
    }
}
