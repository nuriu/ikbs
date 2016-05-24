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
import uygulama.sirket.Ilan;
import uygulama.veriYapilari.bagliListe.BagliListe;
import uygulama.veriYapilari.ikiliAramaAgaci.IkiliAramaAgaci;
import uygulama.veriYapilari.ikiliAramaAgaci.iAADugum;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Optional;
import java.util.ResourceBundle;

public class ElemanKontrolcusu implements Initializable {
    // sistem genelinde kullanılacak olan kişi listesi
    public static IkiliAramaAgaci Kisiler;
    // karşılama ekranında seçilen kişi
    public static iAADugum SistemdekiKisi;

    private Parent arayuz;

    //--------------------------------------------------------------------------
    // ARAYÜZ ÜYELERİ
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
    @FXML
    private Label lblSistemdekiKisi;
    @FXML
    private ListView listIsIlanlari;
    //--------------------------------------------------------------------------

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (SistemdekiKisi == null) {
            ObservableList<String> md = FXCollections.observableArrayList("Evli", "Bekar");
            medeniDurum.setItems(md);
            deneyimListesi.setItems(lDeneyimler);
            egitimListesi.setItems(lEgitim);
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate tarih = LocalDate.parse(SistemdekiKisi.kisi.DogumTarihi, format);
            lblSistemdekiKisi.setText("Sistemdeki Kişi : " + SistemdekiKisi.kisi.bilgileriGetir());
            ad.setText(SistemdekiKisi.kisi.Ad);
            adres.setText(SistemdekiKisi.kisi.Adres);
            telefon.setText(SistemdekiKisi.kisi.Telefon);
            eposta.setText(SistemdekiKisi.kisi.Eposta);
            uyruk.setText(SistemdekiKisi.kisi.Uyruk);
            dogumYeri.setText(SistemdekiKisi.kisi.DogumYeri);
            dogumTarihi.setValue(tarih);
            medeniDurum.setItems(FXCollections.observableArrayList("Evli", "Bekar"));
            medeniDurum.setValue(SistemdekiKisi.kisi.MedeniDurum);
            yabanciDil.setText(SistemdekiKisi.kisi.YabanciDil);
            ilgiAlanlari.setText(SistemdekiKisi.kisi.IlgiAlanlari);
            referanslar.setText(SistemdekiKisi.kisi.Referanslar);

            if (SistemdekiKisi.Deneyimler != null) {
                for (int i = SistemdekiKisi.Deneyimler.Boyut; i > 0; i--) {
                    if (lDeneyimler != null)
                        lDeneyimler.add(SistemdekiKisi.Deneyimler.elemanGetir(i));
                    else
                        lDeneyimler = FXCollections.observableArrayList(SistemdekiKisi.Deneyimler.elemanGetir(i));
                }

                deneyimListesi.setItems(lDeneyimler);
            }

            if (SistemdekiKisi.EgitimDurumu != null) {
                for (int i = SistemdekiKisi.EgitimDurumu.Boyut; i > 0; i--) {
                    if (lEgitim != null)
                        lEgitim.add(SistemdekiKisi.EgitimDurumu.elemanGetir(i));
                    else
                        lEgitim = FXCollections.observableArrayList(SistemdekiKisi.EgitimDurumu.elemanGetir(i));
                }

                egitimListesi.setItems(lEgitim);
            }
            if (SirketKontrolcusu.Ilanlar != null) {
                IlanListele();
            }
        }
    }

    public void IlanListele(){
        ObservableList<String> ilan = FXCollections.observableArrayList();
        if (SirketKontrolcusu.Ilanlar != null) {
            Enumeration e = SirketKontrolcusu.Ilanlar.elements();
            while (e.hasMoreElements()) {
                Ilan i = (Ilan) e.nextElement();
                ilan.add(i.IlanNo + " | " + i.Sirket.Ad + " | " + i.IsTanimi + " | " + i.ArananOzellikler);
            }
            listIsIlanlari.setItems(ilan);
        }
    }

    public void KarsilamaEkraninaDon() throws Exception {
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/karsilamaEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Karşılama Ekranına Geri Dönüldü.");

        if (SistemdekiKisi != null) {
            SistemdekiKisi = null;
            lDeneyimler = null;
            lEgitim = null;
        }
    }

    public void EgitimBilgisiniSil() {
        if (egitimListesi.getSelectionModel().getSelectedIndex() != -1) {
            int secili = egitimListesi.getSelectionModel().getSelectedIndex();

            if (SistemdekiKisi != null)
                SistemdekiKisi.EgitimDurumu.pozisyonuSil(secili + 1);
            else
                kkEgitim.pozisyonuSil(secili + 1);

            lEgitim.remove(secili);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("Silme Hatası!");
            alert.setContentText("Eğitim bilgisini silmek için seçim yapmalısınız!");
            alert.showAndWait();
        }
    }

    public void EgitimBilgisiEkle() {
        Dialog<Egitim> dialog = new Dialog<>();
        dialog.setTitle("Eğitim Bilgisi Ekle");
        dialog.setHeaderText("Eğitim bilgilerinizi giriniz.");

        ButtonType ekle = new ButtonType("Ekle", ButtonBar.ButtonData.OK_DONE);
        ButtonType iptal = new ButtonType("İptal", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(ekle, iptal);

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

        Node ekleOnayi = dialog.getDialogPane().lookupButton(ekle);
        ekleOnayi.setDisable(true);

        egitimAdi.textProperty().addListener((observable, oldValue, newValue) -> {
            ekleOnayi.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> egitimAdi.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ekle) {
                return new Egitim(egitimAdi.getText(), egitimBolumu.getText(),
                        egitimBaslangic.getValue().getYear() + "", egitimBitis.getValue().getYear() + "",
                        Double.parseDouble(egitimNotOrtalamasi.getText()));
            }
            return null;
        });

        Optional<Egitim> sonuc = dialog.showAndWait();

        sonuc.ifPresent(e -> {
            if (SistemdekiKisi != null) {
                if (SistemdekiKisi.EgitimDurumu == null)
                    SistemdekiKisi.EgitimDurumu = new BagliListe();
                SistemdekiKisi.EgitimDurumu.sonaEkle(e);
            } else {
                kkEgitim.sonaEkle(e);
            }

            if (lEgitim != null)
                lEgitim.add(e.Bitis + " : " + e.Ad + " - " + e.Bolum + " : " + e.NotOrtalamasi);
            else
                lEgitim = FXCollections.observableArrayList(e.Bitis + " : " + e.Ad + " - " + e.Bolum + " : " + e.NotOrtalamasi);

            egitimListesi.setItems(lEgitim);
        });
    }

    public void DeneyimSil() {
        if (deneyimListesi.getSelectionModel().getSelectedIndex() != -1) {
            int secili = deneyimListesi.getSelectionModel().getSelectedIndex();

            if (SistemdekiKisi != null)
                SistemdekiKisi.Deneyimler.pozisyonuSil(secili + 1);
            else
                kkDeneyimler.pozisyonuSil(secili + 1);

            lDeneyimler.remove(secili);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("Silme Hatası!");
            alert.setContentText("Deneyimi silmek için seçim yapmalısınız!");
            alert.showAndWait();
        }
    }

    public void DeneyimEkle() {
        Dialog<Deneyim> dialog = new Dialog<>();
        dialog.setTitle("Deneyim Ekle");
        dialog.setHeaderText("Deneyim bilgilerinizi giriniz.");

        ButtonType ekle = new ButtonType("Ekle", ButtonBar.ButtonData.OK_DONE);
        ButtonType iptal = new ButtonType("İptal", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(ekle, iptal);

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

        Node ekleOnayi = dialog.getDialogPane().lookupButton(ekle);
        ekleOnayi.setDisable(true);

        deneyimAdi.textProperty().addListener((observable, oldValue, newValue) -> {
            ekleOnayi.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> deneyimAdi.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ekle) {
                return new Deneyim(deneyimAdi.getText(), deneyimAdresi.getText(), deneyimPozisyon.getText());
            }
            return null;
        });

        Optional<Deneyim> sonuc = dialog.showAndWait();

        sonuc.ifPresent(d -> {
            if (SistemdekiKisi != null) {
                if (SistemdekiKisi.Deneyimler == null)
                    SistemdekiKisi.Deneyimler = new BagliListe();

                SistemdekiKisi.Deneyimler.sonaEkle(d);
            } else {
                kkDeneyimler.sonaEkle(d);
            }

            if (lDeneyimler != null)
                lDeneyimler.add(d.Ad + " - " + d.Pozisyon);
            else
                lDeneyimler = FXCollections.observableArrayList(d.Ad + " - " + d.Pozisyon);

            deneyimListesi.setItems(lDeneyimler);
        });
    }

    public void SistemeKaydet() throws Exception {
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

            if (SistemdekiKisi != null) {
                SistemdekiKisi.kisi = null;
                SistemdekiKisi.kisi = new Kisi(ad.getText(), adres.getText(),
                        telefon.getText(), eposta.getText(), uyruk.getText(),
                        dogumYeri.getText(), dogumTarihi.getValue().toString(),
                        medeniDurum.getValue(), yabanciDil.getText(),
                        ilgiAlanlari.getText(), referanslar.getText());

                iAADugum d = new iAADugum(SistemdekiKisi.kisi, SistemdekiKisi.Deneyimler, SistemdekiKisi.EgitimDurumu);
                Kisiler.kisiGuncelle(SistemdekiKisi.kisi.Ad, d);
            } else {
                kaydedilecekKisi = new Kisi(ad.getText(), adres.getText(),
                        telefon.getText(), eposta.getText(), uyruk.getText(),
                        dogumYeri.getText(), dogumTarihi.getValue().toString(),
                        medeniDurum.getValue(), yabanciDil.getText(),
                        ilgiAlanlari.getText(), referanslar.getText());

                if (Kisiler == null || Kisiler.dugumSayisi() == 0) {
                    iAADugum d = new iAADugum(kaydedilecekKisi);
                    Kisiler = new IkiliAramaAgaci(d, kkDeneyimler, kkEgitim);
                } else {
                    Kisiler.kisiEkle(kaydedilecekKisi, kkDeneyimler, kkEgitim);
                }
            }
            KarsilamaEkraninaDon();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            if (SistemdekiKisi == null) {
                alert.setHeaderText("Kayıt Hatası!");
                alert.setContentText("Kayıt için kişisel bilgilerin tamamını doldurmalısınız!");
            } else {
                alert.setHeaderText("Güncelleme Hatası!");
                alert.setContentText("Güncelleme için kişisel bilgileri doldurmalısınız!");
            }
            alert.showAndWait();
        }
    }

    public void BasvuruYap(){
        if (listIsIlanlari.getSelectionModel().getSelectedItem() != null) {
            String[] ilanBilgileri = listIsIlanlari.getSelectionModel().getSelectedItem().toString().split(" \\| ");
            Ilan ilan = (Ilan) SirketKontrolcusu.Ilanlar.get(Integer.valueOf(ilanBilgileri[0]));
            boolean kisiAra =  ilan.Basvuranlar.KisiAra(SistemdekiKisi.kisi);
            if (!kisiAra) {
                ilan.Basvuranlar.ekle(SistemdekiKisi.kisi);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("BİLGİ");
                alert.setHeaderText("Başvuru Bilgisi!");
                alert.setContentText("Seçilen işe başarıyla başvuru yapılmıştır.");
                alert.showAndWait();
            } else {
                System.out.println(kisiAra + " - ");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("HATA");
                alert.setHeaderText("Başvuru Hatası!");
                alert.setContentText("Bu işe daha önce başvuru yaptınız!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("Başvuru Hatası!");
            alert.setContentText("Başvuru yapılacak ilanı seçmelisiniz!");
            alert.showAndWait();
        }
    }
}