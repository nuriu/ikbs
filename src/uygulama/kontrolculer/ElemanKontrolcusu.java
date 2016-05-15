package uygulama.kontrolculer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import uygulama.Main;
import uygulama.eleman.Kisi;
import uygulama.veriYapilari.bagliListe.BagliListe;
import uygulama.veriYapilari.ikiliAramaAgaci.Dugum;
import uygulama.veriYapilari.ikiliAramaAgaci.IkiliAramaAgaci;

import java.net.URL;
import java.util.ResourceBundle;

public class ElemanKontrolcusu implements Initializable {
    // sistem genelinde kullanılacak olan kişi listesi
    public static IkiliAramaAgaci Kisiler;
    private Parent arayuz;
    // karşılama ekranında seçilen kişi
    private Dugum SistemdekiKisi;

    //--------------------------------------------------------------------------
    // ELEMAN KAYIT EKRANI ÜYELERİ
    //--------------------------------------------------------------------------
    private Kisi kaydedilecekKisi;
    private BagliListe kkDeneyimler = new BagliListe();
    private BagliListe kkEgitim = new BagliListe();
    @FXML
    private TextField ad;
    @FXML
    private TextArea adres;
    @FXML
    private TextField telefon;
    @FXML
    private TextField eposta;
    @FXML
    private TextField uyruk;
    @FXML
    private TextField dogumYeri;
    @FXML
    private DatePicker dogumTarihi;
    @FXML
    private ComboBox<String> medeniDurum;
    @FXML
    private TextArea ilgiAlanlari;
    @FXML
    private TextField yabanciDil;
    @FXML
    private TextArea referanslar;
    @FXML
    private ListView deneyimListesi;
    @FXML
    private ListView egitimListesi;
    //--------------------------------------------------------------------------

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> md = FXCollections.observableArrayList("Evli", "Bekar");
        medeniDurum.setItems(md);
    }

    public void KarsilamaEkraninaDon(ActionEvent actionEvent) throws Exception {
        // karşılama ekranını geri yükler ve geçiş yapar
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/karsilamaEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi");
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
        // kaydedilecek kişinin bilgilerini al
        kaydedilecekKisi = new Kisi(ad.getText(), adres.getText(),
                telefon.getText(), eposta.getText(), uyruk.getText(),
                dogumYeri.getText(), dogumTarihi.getValue().toString(),
                medeniDurum.getValue(), yabanciDil.getText(),
                ilgiAlanlari.getText(), referanslar.getText());

        // kişinin diğer bilgilerini al
        kkDeneyimler.sonaEkle(deneyimListesi.getItems().toArray());
        kkEgitim.sonaEkle(egitimListesi.getItems().toArray());

        // kişiyi sisteme kaydet
        if (Kisiler == null || Kisiler.dugumSayisi() == 0) {
            // ağaç boş ise
            Dugum d = new Dugum(kaydedilecekKisi);
            Kisiler = new IkiliAramaAgaci(d, kkDeneyimler, kkEgitim);
        } else {
            Kisiler.kisiEkle(kaydedilecekKisi, kkDeneyimler, kkEgitim);
        }
    }
}
