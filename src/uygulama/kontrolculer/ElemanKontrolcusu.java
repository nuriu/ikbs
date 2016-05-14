package uygulama.kontrolculer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import uygulama.Main;
import uygulama.veriYapilari.ikiliAramaAgaci.Dugum;

public class ElemanKontrolcusu {
    private Parent arayuz;
    private Dugum SistemdekiKisi;

    public void KarsilamaEkraninaDon(ActionEvent actionEvent) throws Exception {
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/karsilamaEkrani.fxml"));
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Karşılama Ekranına Geri Dönüldü.");
    }

    public void EgitimBilgisiniSil(ActionEvent actionEvent) {

    }

    public void EgitimBilgisiEkle(ActionEvent actionEvent) {

    }

    public void DeneyimSil(ActionEvent actionEvent) {

    }

    public void DeneyimEkle(ActionEvent actionEvent) {

    }

    public void SistemeKaydet(ActionEvent actionEvent) {

    }
}
