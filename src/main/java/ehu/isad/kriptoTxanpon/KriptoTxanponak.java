package ehu.isad.kriptoTxanpon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class KriptoTxanponak extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("TXANPONEN ARIKETA");

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().add("BTC");
        comboBox.getItems().add("ETH");
        comboBox.getItems().add("LTC");
        comboBox.setEditable(true);

        Label labela = new Label();//ERABILIKO DEL LABEL-A TXANPON BAKOITZAREN PREZIOA ADIERAZTEKO

        comboBox.setOnAction(e -> {
            try {
                float p = readFromUrl(comboBox.getValue().toString());
                labela.setText("1"+comboBox.getValue().toString()+" = "+p+"€");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        VBox vbox = new VBox(labela, comboBox);
        Scene scene = new Scene(vbox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private float readFromUrl(String txanpon) throws IOException {

        String inputLine;

        URL coinmarket = new URL("https://api.gdax.com/products/"+txanpon+"-eur/ticker");
        URLConnection yc = coinmarket.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        inputLine = in.readLine();
        in.close();

        Gson gson = new Gson();
        Txanpona tx = gson.fromJson(inputLine, Txanpona.class);
        //parametro bezala pasatutako txanponaren prezioa bueltatuko dugu
        return tx.price;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

