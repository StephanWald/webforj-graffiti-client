package samples;

import static com.webforj.component.optiondialog.OptionDialog.showMessageDialog;

import com.webforj.App;
import com.webforj.annotation.AppTitle;
import com.webforj.annotation.InlineStyleSheet;
import com.webforj.component.Component;
import com.webforj.component.button.Button;
import com.webforj.component.button.ButtonTheme;
import com.webforj.component.html.elements.Paragraph;
import com.webforj.component.text.Label;
import com.webforj.component.window.Frame;
import com.webforj.exceptions.WebforjException;
import com.webforj.graffiti.model.WebforjLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A simple HelloWorld app.
 */
@InlineStyleSheet(/* css */"""
  .mainFrame {
    display: inline-grid;
    gap: 20px;
    margin: 20px;
    padding: 20px;
    border: 1px dashed;
    border-radius: 10px;
  }
""")
@AppTitle("webforJ Hello World")
public class WebforjHelloWorld extends App {
  
  Paragraph hello = new Paragraph("Hello World!");
  Button btn = new Button("Say Hello");

  @Override
  public void run() throws WebforjException {
    Frame mainFrame = new Frame();
    mainFrame.addClassName("mainFrame");


    String json = null;
    try {
      json = WebforjHelloWorld.readResourceFile("/hello.json");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    if (json == null) {
      mainFrame.add(new Label("json is null"));
      return;
    }

    WebforjLoader loader = new WebforjLoader();
    loader.setJson(json);
    final Component load = loader.load();
    if (load != null )
      mainFrame.add(load);
    else
      mainFrame.add(new Label("component was null"));

  }
  public static String readResourceFile(String resourcePath) throws IOException {
    try (InputStream inputStream = WebforjHelloWorld.class.getResourceAsStream(resourcePath)) {
      if (inputStream == null) {
        throw new IOException("Resource not found: " + resourcePath);
      }
      return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
  }

}
