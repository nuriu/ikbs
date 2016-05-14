package uygulama.kontrolculer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import uygulama.Main;

/**
 * Paket:           uygulama
 * Oluşturan:       Nuri UZUNOĞLU
 * Oluşturma:       09.05.2016 | 13:04
 */

public class ElemanKontrolcusu {
    private Parent arayuz;

    public void KarsilamaEkraninaDon(ActionEvent actionEvent) throws Exception {
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/karsilamaEkrani.fxml"));
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Karşılama Ekranına Geri Dönüldü.");
    }
}
