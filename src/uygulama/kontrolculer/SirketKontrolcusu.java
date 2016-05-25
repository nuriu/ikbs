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
    @FXML
    private ListView listOzel;

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
                !faks.getText().isEmpty() && !ePosta.getText().isEmpty()) {
            if (sistemdekiSirket != null) {
                kaydedilecekSirket = new Sirket(isYeriAdi.getText(), tamAdres.getText(), telefon.getText(),
                        faks.getText(), ePosta.getText());

                if (Ilanlar != null) {
                    Enumeration e = Ilanlar.elements();
                    while (e.hasMoreElements()) {
                        Ilan i = (Ilan) e.nextElement();

                        if (i.Sirket.Ad == sistemdekiSirket.Ad)
                            i.Sirket = kaydedilecekSirket;
                    }
                }

                Sirketler.remove(sistemdekiSirket.Ad);
                Sirketler.put(kaydedilecekSirket.Ad, kaydedilecekSirket);
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

            txtIsTanimi.setText("");
            txtAtananOzellikler.setText("");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("İLAN EKLENDİ");
            alert.setHeaderText("İlan eklemesi başarılı!");
            alert.setContentText("İlan sisteme başarı ile eklendi!");
            alert.showAndWait();
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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("İLAN SİLİNDİ");
            alert.setHeaderText("İlan kaldırma başarılı!");
            alert.setContentText("İlan sistemden başarı ile kaldırıldı!");
            alert.showAndWait();
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

            String ayrintilar = "";
            ayrintilar += "Ad: \t\t\t\t" + iseAlinan.Kisi.Ad + "\n" +
                    "Uygunluk: \t\t" + iseAlinan.Uygunluk + "\n" +
                    "Adres:\t\t\t" + iseAlinan.Kisi.Adres + "\n" +
                    "Telefon:\t\t\t" + iseAlinan.Kisi.Telefon + "\n" +
                    "Eposta:\t\t\t" + iseAlinan.Kisi.Eposta + "\n" +
                    "Uyruk:\t\t\t" + iseAlinan.Kisi.Uyruk + "\n" +
                    "DogumYeri:\t\t" + iseAlinan.Kisi.DogumYeri + "\n" +
                    "DogumTarihi:\t" + iseAlinan.Kisi.DogumTarihi + "\n" +
                    "MedeniDurum:\t" + iseAlinan.Kisi.MedeniDurum + "\n" +
                    "YabanciDil:\t\t" + iseAlinan.Kisi.YabanciDil + "\n" +
                    "IlgiAlanlari:\t\t" + iseAlinan.Kisi.IlgiAlanlari + "\n" +
                    "Referanslar:\t\t" + iseAlinan.Kisi.Referanslar + "\n";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BİLGİ");
            alert.setHeaderText("İşe Alınan Kişinin Bilgileri!");
            alert.setContentText(ayrintilar);
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

            String ayrintilar = "";
            ayrintilar += "Ad: \t\t\t\t" + iseAlinan.Kisi.Ad + "\n" +
                    "Uygunluk: \t\t" + iseAlinan.Uygunluk + "\n" +
                    "Adres:\t\t\t" + iseAlinan.Kisi.Adres + "\n" +
                    "Telefon:\t\t\t" + iseAlinan.Kisi.Telefon + "\n" +
                    "Eposta:\t\t\t" + iseAlinan.Kisi.Eposta + "\n" +
                    "Uyruk:\t\t\t" + iseAlinan.Kisi.Uyruk + "\n" +
                    "DogumYeri:\t\t" + iseAlinan.Kisi.DogumYeri + "\n" +
                    "DogumTarihi:\t" + iseAlinan.Kisi.DogumTarihi + "\n" +
                    "MedeniDurum:\t" + iseAlinan.Kisi.MedeniDurum + "\n" +
                    "YabanciDil:\t\t" + iseAlinan.Kisi.YabanciDil + "\n" +
                    "IlgiAlanlari:\t\t" + iseAlinan.Kisi.IlgiAlanlari + "\n" +
                    "Referanslar:\t\t" + iseAlinan.Kisi.Referanslar + "\n";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BİLGİ");
            alert.setHeaderText("İşe Alınan Kişinin Bilgileri!");
            alert.setContentText(ayrintilar);
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

            String ayrintilar = "";
            ayrintilar += "Adres:\t\t\t" + secilen.kisi.Adres + "\n" +
                    "Telefon:\t\t\t" + secilen.kisi.Telefon + "\n" +
                    "Eposta:\t\t\t" + secilen.kisi.Eposta + "\n" +
                    "Uyruk:\t\t\t" + secilen.kisi.Uyruk + "\n" +
                    "DogumYeri:\t\t" + secilen.kisi.DogumYeri + "\n" +
                    "DogumTarihi:\t" + secilen.kisi.DogumTarihi + "\n" +
                    "MedeniDurum:\t" + secilen.kisi.MedeniDurum + "\n" +
                    "YabanciDil:\t\t" + secilen.kisi.YabanciDil + "\n" +
                    "IlgiAlanlari:\t\t" + secilen.kisi.IlgiAlanlari + "\n" +
                    "Referanslar:\t\t" + secilen.kisi.Referanslar + "\n";


            if (secilen.Deneyimler != null)
                ayrintilar += "\nDeneyimler;\n\n" + secilen.Deneyimler.listele() + "\n";
            if (secilen.EgitimDurumu != null)
                ayrintilar += "\nEğitim Bilgileri;\n\n" + secilen.EgitimDurumu.listele() + "\n";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ayrıntılı Bilgiler");
            alert.setHeaderText(secilen.kisi.Ad + " için Ayrıntılı Bilgiler\n");
            alert.setContentText(ayrintilar);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("Eleman Seçme Hatası!");
            alert.setContentText("Ayrıntılarını görmek istediğiniz elemanı seçmelisiniz!");
            alert.showAndWait();
        }
    }

    public void ingilizceBilenleriListele() {
        if (listIlanlar.getSelectionModel().getSelectedItem() != null) {
            String[] ilanBilgileri = listIlanlar.getSelectionModel().getSelectedItem().toString().split(" \\| ");
            ilan = (Ilan) Ilanlar.get(Integer.valueOf(ilanBilgileri[0]));
            ObservableList<String> ingilizceBilenler = ilan.Basvuranlar.ingilizceBilenler();
            if (ingilizceBilenler != null){
                listOzel.setItems(ingilizceBilenler);
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("HATA");
                alert.setHeaderText("Listeleme Hatası!");
                alert.setContentText("İngilizce bilen kişi bulunamadı!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("Listeleme Hatası!");
            alert.setContentText("Öncelikle başvuruları listelenecek ilanı seçmelisiniz!");
            alert.showAndWait();
        }
    }

    public void notaGoreListele() {
        // TODO: notOrtalaması değişkeni 3.0'dan büyük olan kişileri listele
    }
}
