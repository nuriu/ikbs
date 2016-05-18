package uygulama.kontrolculer;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import uygulama.Main;
import uygulama.eleman.Deneyim;
import uygulama.eleman.Egitim;
import uygulama.eleman.Kisi;
import uygulama.veriYapilari.bagliListe.BagliListe;
import uygulama.veriYapilari.ikiliAramaAgaci.IkiliAramaAgaci;
import uygulama.veriYapilari.ikiliAramaAgaci.iAADugum;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ElemanKontrolcusu implements Initializable {
    // sistem genelinde kullanılacak olan kişi listesi
    public static IkiliAramaAgaci Kisiler;
    // karşılama ekranında seçilen kişi
    public static iAADugum SistemdekiKisi;

    private Parent arayuz;

    //--------------------------------------------------------------------------
    // ELEMAN KAYIT EKRANI ÜYELERİ
    //--------------------------------------------------------------------------
    private Kisi kaydedilecekKisi;
    private BagliListe kkDeneyimler = new BagliListe();
    private ObservableList<String> lDeneyimler = null;
    private BagliListe kkEgitim = new BagliListe();
    private ObservableList<String> lEgitim = null;
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
        // sistemde kişi yoksa yani kişi kaydı yapılıyorsa
        if (SistemdekiKisi == null) {
            ObservableList<String> md = FXCollections.observableArrayList("Evli", "Bekar");
            medeniDurum.setItems(md);
            deneyimListesi.setItems(lDeneyimler);
            egitimListesi.setItems(lEgitim);
        }
    }

    public void KarsilamaEkraninaDon() throws Exception {
        // karşılama ekranını geri yükle ve geçiş yap
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/karsilamaEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Karşılama Ekranına Geri Dönüldü.");
    }

    public void EgitimBilgisiniSil() {
        int secili = egitimListesi.getSelectionModel().getSelectedIndex();
        kkEgitim.pozisyonuSil(secili + 1);
        lEgitim.remove(secili);
    }

    public void EgitimBilgisiEkle() {
        // pencereyi oluştur
        Dialog<Egitim> dialog = new Dialog<>();
        dialog.setTitle("Eğitim Bilgisi Ekle");
        dialog.setHeaderText("Eğitim bilgilerinizi giriniz.");

        // buttonları oluştur
        ButtonType ekle = new ButtonType("Ekle", ButtonBar.ButtonData.OK_DONE);
        ButtonType iptal = new ButtonType("İptal", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(ekle, iptal);

        // alanları oluştur
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 10, 10));

        TextField egitimAdi = new TextField();
        egitimAdi.setPromptText("Okul Adı");
        TextField egitimBolumu = new TextField();
        egitimBolumu.setPromptText("Bölüm");
        DatePicker egitimBaslangic = new DatePicker();
        DatePicker egitimBitis = new DatePicker();
        TextField egitimNotOrtalamasi = new TextField();
        egitimNotOrtalamasi.setPromptText("Not Ortalaması");

        grid.add(new Label("Ad:"), 0, 0);
        grid.add(egitimAdi, 1, 0);
        grid.add(new Label("Bölüm:"), 0, 1);
        grid.add(egitimBolumu, 1, 1);
        grid.add(new Label("Başlangıç:"), 0, 2);
        grid.add(egitimBaslangic, 1, 2);
        grid.add(new Label("Bitiş:"), 0, 3);
        grid.add(egitimBitis, 1, 3);
        grid.add(new Label("Not Ortalaması:"), 0, 4);
        grid.add(egitimNotOrtalamasi, 1, 4);

        // bilgi girmeden eklemeyi önle
        Node ekleOnayi = dialog.getDialogPane().lookupButton(ekle);
        ekleOnayi.setDisable(true);

        egitimAdi.textProperty().addListener((observable, oldValue, newValue) -> {
            ekleOnayi.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // varsayılan odağı deneyimAdi alanına ayarla
        Platform.runLater(() -> egitimAdi.requestFocus());

        // ekle butonu tıklandığında girilen verileri çek
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ekle) {
                return new Egitim(egitimAdi.getText(), egitimBolumu.getText(),
                        egitimBaslangic.getValue().getYear() + "", egitimBitis.getValue().getYear() + "",
                        Double.parseDouble(egitimNotOrtalamasi.getText()));
            }
            return null;
        });

        Optional<Egitim> sonuc = dialog.showAndWait();

        // sonuç geçerli veri içeriyorsa
        sonuc.ifPresent(e -> {
            kkEgitim.sonaEkle(sonuc);

            if (lEgitim != null)
                lEgitim.add(e.Bitis + " : " + e.Ad + " - " + e.Bolum + " : " + e.NotOrtalamasi);
            else
                lEgitim = FXCollections.observableArrayList(e.Bitis + " : " + e.Ad + " - " + e.Bolum + " : " + e.NotOrtalamasi);

            // eğitim listesini güncelle
            egitimListesi.setItems(lEgitim);
        });
    }

    public void DeneyimSil() {
        int secili = deneyimListesi.getSelectionModel().getSelectedIndex();
        kkDeneyimler.pozisyonuSil(secili + 1);
        lDeneyimler.remove(secili);
    }

    public void DeneyimEkle() {
        // pencereyi oluştur
        Dialog<Deneyim> dialog = new Dialog<>();
        dialog.setTitle("Deneyim Ekle");
        dialog.setHeaderText("Deneyim bilgilerinizi giriniz.");

        // buttonları oluştur
        ButtonType ekle = new ButtonType("Ekle", ButtonBar.ButtonData.OK_DONE);
        ButtonType iptal = new ButtonType("İptal", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(ekle, iptal);

        // alanları oluştur
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 10, 10));

        TextField deneyimAdi = new TextField();
        deneyimAdi.setPromptText("Şirket Adı");
        TextArea deneyimAdresi = new TextArea();
        deneyimAdresi.setMaxHeight(100);
        deneyimAdresi.setPromptText("Şirket Adresi");
        TextField deneyimPozisyon = new TextField();
        deneyimPozisyon.setPromptText("Şirketteki Pozisyonunuz");

        grid.add(new Label("Ad:"), 0, 0);
        grid.add(deneyimAdi, 1, 0);
        grid.add(new Label("Adres:"), 0, 1);
        grid.add(deneyimAdresi, 1, 1);
        grid.add(new Label("Pozisyon:"), 0, 2);
        grid.add(deneyimPozisyon, 1, 2);

        // bilgi girmeden eklemeyi önle
        Node ekleOnayi = dialog.getDialogPane().lookupButton(ekle);
        ekleOnayi.setDisable(true);

        deneyimAdi.textProperty().addListener((observable, oldValue, newValue) -> {
            ekleOnayi.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // varsayılan odağı deneyimAdi alanına ayarla
        Platform.runLater(() -> deneyimAdi.requestFocus());

        // ekle butonu tıklandığında girilen verileri çek
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ekle) {
                return new Deneyim(deneyimAdi.getText(), deneyimAdresi.getText(), deneyimPozisyon.getText());
            }
            return null;
        });

        Optional<Deneyim> sonuc = dialog.showAndWait();

        // sonuç geçerli veri içeriyorsa
        sonuc.ifPresent(d -> {
            kkDeneyimler.sonaEkle(sonuc);

            if (lDeneyimler != null)
                lDeneyimler.add(d.Ad + " - " + d.Pozisyon);
            else
                lDeneyimler = FXCollections.observableArrayList(d.Ad + " - " + d.Pozisyon);
            // deneyim listesini güncelle
            deneyimListesi.setItems(lDeneyimler);
        });
    }

    public void SistemeKaydet() throws Exception {
        // tüm kişisel bilgiler girildiyse kaydı yap
        if (ad.getText().isEmpty() != true &&
                adres.getText().isEmpty() != true &&
                telefon.getText().isEmpty() != true &&
                eposta.getText().isEmpty() != true &&
                uyruk.getText().isEmpty() != true &&
                dogumYeri.getText().isEmpty() != true &&
                dogumTarihi.getValue().toString().isEmpty() != true &&
                medeniDurum.getValue().toString().isEmpty() != true &&
                yabanciDil.getText().isEmpty() != true &&
                ilgiAlanlari.getText().isEmpty() != true &&
                referanslar.getText().isEmpty() != true) {

            // kaydedilecek kişinin bilgilerini al
            kaydedilecekKisi = new Kisi(ad.getText(), adres.getText(),
                    telefon.getText(), eposta.getText(), uyruk.getText(),
                    dogumYeri.getText(), dogumTarihi.getValue().toString(),
                    medeniDurum.getValue(), yabanciDil.getText(),
                    ilgiAlanlari.getText(), referanslar.getText());

            // kişiyi sisteme kaydet
            if (ElemanKontrolcusu.Kisiler == null || ElemanKontrolcusu.Kisiler.dugumSayisi() == 0) {
                // ağaç boş ise
                iAADugum d = new iAADugum(kaydedilecekKisi);
                ElemanKontrolcusu.Kisiler = new IkiliAramaAgaci(d, kkDeneyimler, kkEgitim);
            } else {
                ElemanKontrolcusu.Kisiler.kisiEkle(kaydedilecekKisi, kkDeneyimler, kkEgitim);
            }
            KarsilamaEkraninaDon();
        } else {
            // TODO: Kişi bilgilerinin tam girilmemesi halinde kayıt yapma ve uyarı ver
        }
    }
}