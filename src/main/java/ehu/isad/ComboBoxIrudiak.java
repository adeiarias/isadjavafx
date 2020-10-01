package ehu.isad;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.google.gson.Gson;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ComboBoxIrudiak extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("IRUDIEN TRATAMENDUAREN");
        InputStream is = getClass().getResourceAsStream("/índice.jpeg");
        BufferedImage reader = ImageIO.read(is);
        Image image = SwingFXUtils.toFXImage(reader,null);
        ImageView imageView = new ImageView(image);

        VBox vbox = new VBox(imageView);
        vbox.setPadding(new Insets(10,0,0,0));
        Scene scene = new Scene(vbox, 200, 120);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
