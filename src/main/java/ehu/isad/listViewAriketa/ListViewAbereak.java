package ehu.isad.listViewAriketa;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListViewAbereak extends Application{

    private Map<String, List<Argazki>> bildumaMap;
    ListView listViewOfArgazki = new ListView();
    ImageView imageView = new ImageView();
    ObservableList<Argazki> argazkiList;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ComboBox comboBilduma = new ComboBox();
        List<String> bildumak = List.of("abereak", "landareak", "frutak");
        ObservableList<String> bildumaList = FXCollections.observableArrayList(bildumak);
        comboBilduma.setItems(bildumaList);
        comboBilduma.setEditable(true);

        hasieratuHashMap();

        comboBilduma.setOnAction(e -> {
            String iz = comboBilduma.getValue().toString();
            listaBete(iz);
            listViewOfArgazki.getSelectionModel().selectedItemProperty().addListener(  (observable, oldValue, newValue) -> {
                if (observable.getValue() == null) return;
                String fitx = ((Argazki) observable.getValue()).getIrudia();
                try {
                    imageView.setImage(lortuIrudia(fitx));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        });



        VBox vbox = new VBox(comboBilduma,listViewOfArgazki,imageView);
        Scene scene = new Scene(vbox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void hasieratuHashMap(){
        bildumaMap = new HashMap<>();

        bildumaMap.put("abereak", List.of(
                new Argazki("elefantea", "elefantea.jpeg"),
                new Argazki("txakurra", "txakurra.jpeg"),
                new Argazki("untxia", "untxia.png")
        ));

        bildumaMap.put("landareak", List.of(
                new Argazki("cactus", "cactus.png"),
                new Argazki("landareBerdea", "landareberdea.jpeg"),
                new Argazki("landareHoria", "landarehoria.jpeg")
        ));

        bildumaMap.put("frutak", List.of(
                new Argazki("fresa", "fresa.jpeg"),
                new Argazki("sandia", "sandia.png"),
                new Argazki("sagarra", "sagarra.jpeg")
        ));
    }

    private Image lortuIrudia(String location) throws IOException {
        InputStream is = getClass().getResourceAsStream("/" + location);
        BufferedImage reader = ImageIO.read(is);
        return SwingFXUtils.toFXImage(reader, null);
    }

    private void listaBete(String izena){
        argazkiList = FXCollections.observableArrayList();
        argazkiList.addAll(bildumaMap.get(izena));
        listViewOfArgazki.setItems(argazkiList);
    }
}