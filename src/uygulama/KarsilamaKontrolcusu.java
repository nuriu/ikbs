package uygulama;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import uygulama.eleman.Kisi;
import uygulama.veriYapilari.ikiliAramaAgaci.Dugum;
import uygulama.veriYapilari.ikiliAramaAgaci.IkiliAramaAgaci;

import java.net.URL;
import java.util.ResourceBundle;

public class KarsilamaKontrolcusu implements Initializable {
    private Parent arayuz;
    @FXML
    private ListView<String> kisiListesi;
    @FXML
    private ListView<String> sirketListesi;

    public void ElemanGirisi(ActionEvent actionEvent) throws Exception {
        arayuz = FXMLLoader.load(getClass().getResource("elemanEkrani.fxml"));
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
    }

    public void SirketGirisi(ActionEvent actionEvent) throws Exception {
        arayuz = FXMLLoader.load(getClass().getResource("sirketEkrani.fxml"));
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Dugum d = new Dugum();
        d.kisi = new Kisi("Nuri");
        Kisi k1 = new Kisi("Deneme");
        Kisi k2 = new Kisi("Ahmet");
        IkiliAramaAgaci agac = new IkiliAramaAgaci(d);
        agac.kisiEkle(k1);
        agac.kisiEkle(k2);
        kisiListesi.setItems(agac.soldanSagaDolas());
    }
}
