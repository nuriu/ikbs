package uygulama.kontrolculer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import uygulama.Main;
import uygulama.sirket.Ilan;
import uygulama.sirket.Sirket;
import uygulama.veriYapilari.ikiliAramaAgaci.iAADugum;
import uygulama.veriYapilari.obek.oDugum;

import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;


public class SirketKontrolcusu implements Initializable {
    public static Hashtable Sirketler;
    public static Hashtable Ilanlar;
    public static Sirket sistemdekiSirket;

    private Parent arayuz;
    private Sirket kaydedilecekSirket;
    private Ilan ilan;

    @FXML
    private TextField isYeriAdi;
    @FXML
    private TextArea tamAdres;
    @FXML
    private TextField telefon;
    @FXML
    private TextField faks;
    @FXML
    private TextField ePosta;
    @FXML
    private Label lblSistemdekiSirket;
    @FXML
    private TextArea txtIsTanimi;
    @FXML
    private TextArea txtAtananOzellikler;
    @FXML
    private ListView listIlanlar;
    @FXML
    private ListView listBasvurular;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (sistemdekiSirket != null) {
            isYeriAdi.setText(sistemdekiSirket.Ad);
            tamAdres.setText(sistemdekiSirket.Adres);
            telefon.setText(sistemdekiSirket.Telefon);
            faks.setText(sistemdekiSirket.Faks);
            ePosta.setText(sistemdekiSirket.Eposta);
            lblSistemdekiSirket.setText("Sistemdeki Şirket: " +
                    sistemdekiSirket.Ad + " | " +
                    sistemdekiSirket.Adres + " | " +
                    sistemdekiSirket.Telefon + " | " +
                    sistemdekiSirket.Faks + " | " +
                    sistemdekiSirket.Eposta);
            IlanListele();
        }
    }

    public void KarsilamaEkraninaDon() throws Exception {
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/karsilamaEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Karşılama Ekranına Geri Dönüldü.");
        if (sistemdekiSirket != null) {
            sistemdekiSirket = null;
        }
    }

    public void SistemeKaydet() throws Exception {
        if(!isYeriAdi.getText().isEmpty() && !tamAdres.getText().isEmpty() && !telefon.getText().isEmpty() &&
                !faks.getText().isEmpty() && !ePosta.getText().isEmpty()){
            if (sistemdekiSirket != null) {
                sistemdekiSirket = null;
                sistemdekiSirket = new Sirket(isYeriAdi.getText(),tamAdres.getText(), telefon.getText(),
                                              faks.getText(), ePosta.getText());
                Sirketler.remove(sistemdekiSirket.Ad);
                Sirketler.put(sistemdekiSirket.Ad, sistemdekiSirket);
            } else {
                kaydedilecekSirket = new Sirket(isYeriAdi.getText(),tamAdres.getText(), telefon.getText(),
                                                faks.getText(), ePosta.getText());
                if (Sirketler == null) {
                    Sirketler = new Hashtable();
                    Sirketler.put(kaydedilecekSirket.Ad, kaydedilecekSirket);
                } else {
                    Sirketler.put(kaydedilecekSirket.Ad, kaydedilecekSirket);
                }
            }
            KarsilamaEkraninaDon();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            if (sistemdekiSirket == null) {
                alert.setHeaderText("Kayıt Hatası!");
                alert.setContentText("Kayıt için şirket bilgilerinin tamamını doldurmalısınız!");
            } else {
                alert.setHeaderText("Güncelleme Hatası!");
                alert.setContentText("Güncelleme için şirket bilgilerini doldurmalısınız!");
            }
            alert.showAndWait();
        }
    }

    public void IlanEkle(){
        if (!txtIsTanimi.getText().isEmpty() && !txtAtananOzellikler.getText().isEmpty()) {
            Ilan ilan = new Ilan(txtIsTanimi.getText(), txtAtananOzellikler.getText(),sistemdekiSirket);
            if (Ilanlar == null) {
                Ilanlar = new Hashtable();
                Ilanlar.put(ilan.IlanNo, ilan);
            } else {
                Ilanlar.put(ilan.IlanNo, ilan);
            }
            IlanListele();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("İlan Eklenemedi!");
            alert.setContentText("Lütfen boş alan bırakmayın!");
            alert.showAndWait();
        }
    }

    public void IlanListele() {
        ObservableList<String> ilan = FXCollections.observableArrayList();
        if (Ilanlar != null) {
            Enumeration e = Ilanlar.elements();
            while (e.hasMoreElements()) {
                Ilan i = (Ilan) e.nextElement();
                if (i.Sirket.Ad == sistemdekiSirket.Ad) {
                    ilan.add(i.IlanNo + " | " + i.Sirket.Ad + " | " + i.IsTanimi + " | " + i.ArananOzellikler);
                }
            }
            listIlanlar.setItems(ilan);
        }
    }

    public void BasvurulariListele() {
        if (listIlanlar.getSelectionModel().getSelectedItem() != null) {
            String[] ilanBilgileri = listIlanlar.getSelectionModel().getSelectedItem().toString().split(" \\| ");
            ilan = (Ilan) Ilanlar.get(Integer.valueOf(ilanBilgileri[0]));
            listBasvurular.setItems(ilan.Basvuranlar.KisileriListele());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("Listeleme Hatası!");
            alert.setContentText("Öncelikle başvuruları listelenecek ilanı seçmelisiniz!");
            alert.showAndWait();
        }
    }

    public void IlanSil() {
        if (listIlanlar.getSelectionModel().getSelectedItem() != null) {
            String[] ilanBilgileri = listIlanlar.getSelectionModel().getSelectedItem().toString().split(" \\| ");
            Ilanlar.remove(Integer.valueOf(ilanBilgileri[0]));
            IlanListele();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("İlan Silme Hatası!");
            alert.setContentText("Öncelikle silinecek ilanı seçmelisiniz!");
            alert.showAndWait();
        }
    }

    public void UygunOlaniIseAl(){
        if (ilan != null) {
            oDugum iseAlinan = ilan.Basvuranlar.enBuyuguSil();
            Ilanlar.remove(ilan.IlanNo);
            IlanListele();
            ilan = null;
            listBasvurular.setItems(null);
            //deneyim bilgilerini gösterebilmek için
            //iAADugum kisiTamBilgi =  (iAADugum) ElemanKontrolcusu.Kisiler.kisiAra(iseAlinan.Kisi.Ad);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BİLGİ");
            alert.setHeaderText("İşe Alınan Kişinin Bilgileri!");
            alert.setContentText("Uygunluk: " + iseAlinan.Uygunluk + "\n" + iseAlinan.Kisi.bilgileriGetir() +
                                "\nişe alındı ve ilan kaldırıldı.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("İlan Hatası!");
            alert.setContentText("İlan seçilmemiş!");
            alert.showAndWait();
        }
    }

    public void secileniIseAl() {
        if (listBasvurular.getSelectionModel().getSelectedItem() != null){
            String[] kisiBilgileri = listBasvurular.getSelectionModel().getSelectedItem().toString().split(" \\| ");
            oDugum iseAlinan = ilan.Basvuranlar.adaGoreKisiAra(kisiBilgileri[1]);
            Ilanlar.remove(ilan.IlanNo);
            IlanListele();
            ilan = null;
            listBasvurular.setItems(null);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BİLGİ");
            alert.setHeaderText("İşe Alınan Kişinin Bilgileri!");
            alert.setContentText("Uygunluk: " + iseAlinan.Uygunluk + "\n" + iseAlinan.Kisi.bilgileriGetir() +
                    "\nKişi işe alındı ve ilan kaldırıldı.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("Eleman Seçme Hatası!");
            alert.setContentText("İşe almak istediğiniz elemanı seçmelisiniz!");
            alert.showAndWait();
        }
    }

    public void AyrintilariGoster(){
        if (listBasvurular.getSelectionModel().getSelectedItem() != null) {
            String[] kisiBilgileri = listBasvurular.getSelectionModel().getSelectedItem().toString().split(" \\| ");
            iAADugum secilen = ElemanKontrolcusu.Kisiler.kisiAra(kisiBilgileri[1]);
            //Kontrol için eklendi
            System.out.println(secilen.kisi.Ad);
            System.out.println(secilen.Deneyimler.listele());
            System.out.println(secilen.EgitimDurumu.listele());
            //---
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("Eleman Seçme Hatası!");
            alert.setContentText("Ayrıntılarını görmek istediğiniz elemanı seçmelisiniz!");
            alert.showAndWait();
        }
    }
}
