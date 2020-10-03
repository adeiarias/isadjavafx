package ehu.isad;

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


public class ListViewAbereLandareFruta extends Application{

    private Map<String, List<Argazki>> bildumaMap;//HashMap honetan, abere,landare eta fruta motak gordeko ditugu
    ListView listViewOfArgazki = new ListView();
    ImageView imageView = new ImageView();

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("ABERE-LANDARE-FRUTA LISTVIEW ARIKETA");

        ComboBox comboBilduma = new ComboBox();
        List<String> bildumak = List.of("abereak", "landareak", "frutak");
        ObservableList<String> bildumaList = FXCollections.observableArrayList(bildumak);
        comboBilduma.setItems(bildumaList);
        comboBilduma.setEditable(true);

        hasieratuHashMap();

        comboBilduma.setOnAction(e -> {
            String iz = comboBilduma.getValue().toString();
            listaBete(iz);//ListView hau, klikatu dugun arabera beteko da. Hau da, abereak klikatuz gero, elefantea,untxia eta txakurra sartuko da
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
                new Argazki("Elefantea", "elefantea.jpeg"),
                new Argazki("Txakurra", "txakurra.jpeg"),
                new Argazki("Untxia", "untxia.png")
        ));

        bildumaMap.put("landareak", List.of(
                new Argazki("Cactus", "cactus.png"),
                new Argazki("LandareBerdea", "landareberdea.jpeg"),
                new Argazki("LandareHoria", "landarehoria.jpeg")
        ));

        bildumaMap.put("frutak", List.of(
                new Argazki("Fresa", "fresa.jpeg"),
                new Argazki("Sandia", "sandia.png"),
                new Argazki("Sagarra", "sagarra.jpeg")
        ));
    }

    private Image lortuIrudia(String location) throws IOException {
        InputStream is = getClass().getResourceAsStream("/" + location);
        BufferedImage reader = ImageIO.read(is);
        return SwingFXUtils.toFXImage(reader, null);
    }

    private void listaBete(String izena){//izenaren arabera(ABERE,LANDARE,FRUTA), listView-a beteko da
        ObservableList<Argazki> argazkiList;
        argazkiList = FXCollections.observableArrayList();
        argazkiList.addAll(bildumaMap.get(izena));
        listViewOfArgazki.setItems(argazkiList);
    }
}