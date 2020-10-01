package ehu.isad;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class liburakAriketa extends Application{

    private Liburua liburua = new Liburua();
    private HashMap<String, String> mapa;
    Label izenburua,orriak,argitaletxea;
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Liburu tratamenduen ariketa");

        ComboBox comboBox = new ComboBox();
        List<String> bildumak = List.of("Blockchain: Blueprint for a New Economy", "R for Data Science", "Fluent Python", "Natural Language Processing with PyTorch", "Data Algorithms");
        ObservableList<String> bildumaList = FXCollections.observableArrayList(bildumak);
        comboBox.setItems(bildumaList);
        comboBox.setEditable(true);

        mapa = new HashMap<>();
        mapa.put("Blockchain: Blueprint for a New Economy","9781491920497");
        mapa.put("R for Data Science","1491910399");
        mapa.put("Fluent Python","1491946008");
        mapa.put("Natural Language Processing with PyTorch","1491978236");
        mapa.put("Data Algorithms","9781491906187");

        //LABELAK SORTUKO DIRA DATUAK BISTARATZEKO
        izenburua = new Label("IZENBURUA");
        orriak = new Label("ORRIALDE KOPURUA");
        argitaletxea = new Label("ARGITALETXEA(K)");

        comboBox.setOnAction(e -> {
            String izena = comboBox.getValue().toString();
            String isbn = mapa.get(izena);
            try {
                liburuarenDatuakHasieratu(isbn);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            labelakEguneratu();
        });

        VBox vbox = new VBox(comboBox,izenburua,orriak,argitaletxea);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void liburuarenDatuakHasieratu(String ISBN) throws IOException {
        URL oracle = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:"+ISBN+"&jscmd=details&format=json");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        String inputLine;
        inputLine = in.readLine();

        //SPLITAK EGINGO DIRA ORAIN
        String[] lehenengoa = inputLine.split("\"details\": ");
        String p1 = lehenengoa[1];

        String[] bigarrena = p1.split(", \"preview\":");
        String helburua = bigarrena[0];

        Gson gson = new Gson();
        liburua = gson.fromJson(helburua, Liburua.class);
        in.close();
    }

    public void labelakEguneratu(){
        String izen = liburua.getTitle();
        String orr = liburua.getNumber_of_pages();
        String[] argitaletxe = liburua.getPublishers();
        izenburua.setText(izen);
        orriak.setText(orr);
        String argi = null;
        for(int i = 0; i < argitaletxe.length; i++){
            if(argi == null){
                argi = argitaletxe[i];
            }else{
                argi = argi +", "+argitaletxe[i];
            }
        }
        argitaletxea.setText(argi);
    }
}
