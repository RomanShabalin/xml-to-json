package i.novus.vaadin;

import com.google.gson.Gson;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import i.novus.entity.ResultXmlJson;
import i.novus.entity.xml.MainXml;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.XML;

import java.io.InputStream;

@JavaScript(value = "Frontend/generated/jar-resources/myScript.js/")
@Route("/xml-to-json")
@PageTitle("XmlToJson")
public class MainView extends VerticalLayout {
    private Upload upload;
    private Button uploadButton;
    private MultiFileMemoryBuffer buffer;
    private TextArea textArea;

    public MainView() {
        this.init();
    }

    private void init() {
        buffer = new MultiFileMemoryBuffer();

        this.createTextArea();
        this.createUpload();
        this.createUploadButton();

        this.add(upload, textArea);
        this.setAlignItems(Alignment.CENTER);
    }

    private void createTextArea() {
        textArea = new TextArea("Итоговый результат");
        textArea.setWidth(400, Unit.PIXELS);
        textArea.setHeight(500, Unit.PIXELS);
        textArea.setVisible(false);
    }

    private void createUpload() {
        upload = new Upload(buffer);
        upload.setAcceptedFileTypes("application/xml", ".xml");
        upload.setUploadButton(new Button("Select xml-file"));
        upload.setDropLabel(new Label("Drop xml-file here"));

        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            InputStream inputStream = buffer.getInputStream(fileName);
            try {
                byte[] b = IOUtils.toByteArray(inputStream);
                String xml = new String(b);
                JSONObject jsonObject = XML.toJSONObject(xml);

                Gson gson = new Gson();
                MainXml main = gson.fromJson(jsonObject.toString(), MainXml.class);

                ResultXmlJson resultXmlJson = new ResultXmlJson(main);
                String mainJson = resultXmlJson.calculateResult();

                if (mainJson.trim().isEmpty()) {
                    this.addSettingsWhenError();
                } else {
                    textArea.setVisible(true);
                    textArea.setId("textArea");
                    textArea.setValue(mainJson);

                    UI.getCurrent().getPage().executeJs("prettyPrint();");
                }
            } catch (Exception e) {
                this.addSettingsWhenError();
            }
        });

        upload.addFailedListener(event -> this.showNotification());
    }

    private void createUploadButton() {
        uploadButton = (Button) upload.getUploadButton();
        uploadButton.addClickListener(event -> {
            upload.clearFileList();
            textArea.clear();
            textArea.setVisible(false);
        });
    }

    private void showNotification() {
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setPosition(Notification.Position.MIDDLE);

        Div text = new Div(new Text("Не удалось загрузить файл. Проверьте правильность шаблона"));

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(eventClose -> notification.close());

        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(Alignment.CENTER);

        notification.add(layout);
        notification.open();
    }

    private void addSettingsWhenError() {
        upload.clearFileList();
        textArea.clear();
        textArea.setVisible(false);
        this.showNotification();
    }
}
