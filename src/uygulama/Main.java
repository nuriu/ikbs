package uygulama;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage pencere;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pencere = primaryStage;

        Parent arayuz;
        arayuz = FXMLLoader.load(getClass().getResource("ekranlar/karsilamaEkrani.fxml"));

        pencere.setTitle("İnsan Kaynakları Bilgi Sistemi");
        pencere.setScene(new Scene(arayuz, 1280, 700));
        pencere.show();

        pencere.setOnCloseRequest(e -> {
            e.consume();
            pencere.close();
        });
    }
}
