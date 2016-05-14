package uygulama.kontrolculer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import uygulama.Main;

public class SirketKontrolcusu {
    private Parent arayuz;

    public void KarsilamaEkraninaDon(ActionEvent actionEvent) throws Exception {
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/karsilamaEkrani.fxml"));
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Karşılama Ekranına Geri Dönüldü.");
    }
}
