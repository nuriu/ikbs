package uygulama;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class KarsilamaKontrolcusu {
    private Parent arayuz;

    public void ElemanGirisi(ActionEvent actionEvent) throws Exception {
        arayuz = FXMLLoader.load(getClass().getResource("elemanEkrani.fxml"));
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
    }

    public void SirketGirisi(ActionEvent actionEvent) throws Exception {
        arayuz = FXMLLoader.load(getClass().getResource("sirketEkrani.fxml"));
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
    }
}
