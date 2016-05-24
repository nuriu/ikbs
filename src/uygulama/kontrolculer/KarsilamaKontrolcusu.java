package uygulama.kontrolculer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import uygulama.Main;
import uygulama.eleman.Kisi;
import uygulama.sirket.Sirket;
import uygulama.veriYapilari.ikiliAramaAgaci.IkiliAramaAgaci;
import uygulama.veriYapilari.ikiliAramaAgaci.iAADugum;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class KarsilamaKontrolcusu implements Initializable {
    @FXML
    public ListView<String> kisiListesi;
    @FXML
    public ListView<String> sirketListesi;

    private ObservableList<String> sirketL = FXCollections.observableArrayList();
    private Parent arayuz;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            kisiAgaciniOlustur();
            sirketHashTablosuOlustur();
        } catch (IOException e) {
            e.printStackTrace();
        }

        kisiListesi.setItems(ElemanKontrolcusu.Kisiler.soldanSagaDolas());

        Enumeration e = SirketKontrolcusu.Sirketler.elements();
        while(e.hasMoreElements()){
            Sirket s = (Sirket) e.nextElement();
            sirketL.add(s.Ad);
        }
        sirketListesi.setItems(sirketL);
    }

    public void ElemanGirisi() throws Exception {
        if (kisiListesi.getSelectionModel().getSelectedItem() != null) {
            String[] sistemdekiKisininBilgileri = kisiListesi.getSelectionModel().getSelectedItem().split(" \\| ");
            ElemanKontrolcusu.SistemdekiKisi = ElemanKontrolcusu.Kisiler.kisiAra(sistemdekiKisininBilgileri[0]);

            arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/elemanEkrani.fxml"));
            Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi - Eleman Ekranı");
            Main.pencere.setScene(new Scene(arayuz, 1280, 700));
            System.out.println("Eleman Ekranına Geçildi.");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("Giriş Hatası!");
            alert.setContentText("Öncelikle giriş yapılacak elemanı seçmelisiniz!");
            alert.showAndWait();
        }
    }

    public void SirketGirisi() throws Exception {
        if(sirketListesi.getSelectionModel().getSelectedItem() != null){
            String anahtar = sirketListesi.getSelectionModel().getSelectedItem();
            SirketKontrolcusu.sistemdekiSirket = (Sirket) SirketKontrolcusu.Sirketler.get(anahtar);
            arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/sirketEkrani.fxml"));
            Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi - Şirket Ekranı");
            Main.pencere.setScene(new Scene(arayuz, 1280, 700));
            System.out.println("Şirket Ekranına Geçildi.");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("Giriş Hatası!");
            alert.setContentText("Öncelikle giriş yapılacak şirketi seçmelisiniz!");
            alert.showAndWait();
        }
    }

    public void ElemanKaydi() throws Exception {
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/elemanKayitEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi - Eleman Kayıt Ekranı");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Eleman Kayıt Ekranına Geçildi.");
    }

    public void SirketKaydi() throws Exception {
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/sirketKayitEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi - Şirket Kayıt Ekranı");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Şirket Kayıt Ekranına Geçildi.");
    }

    public void elemaniSil() {
        if (kisiListesi.getSelectionModel().getSelectedItem() != null) {
            String[] silinecekKisininBilgileri = kisiListesi.getSelectionModel().getSelectedItem().split(" \\| ");
            ElemanKontrolcusu.Kisiler.kisiSil(silinecekKisininBilgileri[0]);
            kisiListesi.setItems(ElemanKontrolcusu.Kisiler.soldanSagaDolas());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("HATA");
            alert.setHeaderText("Silme Hatası!");
            alert.setContentText("Öncelikle silinecek elemanı seçmelisiniz!");
            alert.showAndWait();
        }
    }

    private void kisiAgaciniOlustur() throws IOException {
        if (ElemanKontrolcusu.Kisiler == null || ElemanKontrolcusu.Kisiler.dugumSayisi() == 0) {
            String satir = null;
            Kisi eklenecekKisi = null;
            iAADugum d = new iAADugum();
            ElemanKontrolcusu.Kisiler = null;

            InputStream elemanDosyasi = new FileInputStream("eleman.txt");
            InputStreamReader eOkuyucu = new InputStreamReader(elemanDosyasi, Charset.forName("UTF-8"));
            BufferedReader okuyucu = new BufferedReader(eOkuyucu);

            int i = 0;
            while ((satir = okuyucu.readLine()) != null) {
                String[] eklenecekKisininBilgileri = satir.split(", ");
                eklenecekKisi = new Kisi(eklenecekKisininBilgileri[0],
                        eklenecekKisininBilgileri[1], eklenecekKisininBilgileri[2],
                        eklenecekKisininBilgileri[3], eklenecekKisininBilgileri[4],
                        eklenecekKisininBilgileri[5], eklenecekKisininBilgileri[6],
                        eklenecekKisininBilgileri[7], eklenecekKisininBilgileri[8],
                        eklenecekKisininBilgileri[9], eklenecekKisininBilgileri[10]);
                if (i == 0) {
                    d.kisi = eklenecekKisi;
                    ElemanKontrolcusu.Kisiler = new IkiliAramaAgaci(d, null, null);
                } else {
                    ElemanKontrolcusu.Kisiler.kisiEkle(eklenecekKisi, null, null);
                }
                i++;
            }
        }
    }

    private void sirketHashTablosuOlustur() throws IOException {
        if(SirketKontrolcusu.Sirketler == null) {
            String satir = null;
            Sirket eklenecekSirket = null;
            SirketKontrolcusu.Sirketler = null;
            InputStream sirketDosyasi = new FileInputStream("sirket.txt");
            InputStreamReader eOkuyucu = new InputStreamReader(sirketDosyasi, Charset.forName("UTF-8"));
            BufferedReader okuyucu = new BufferedReader(eOkuyucu);

            int i = 0;
            while ((satir = okuyucu.readLine()) != null) {
                String[] eklenecekSirketinBilgileri = satir.split(", ");
                eklenecekSirket = new Sirket(eklenecekSirketinBilgileri[0],
                        eklenecekSirketinBilgileri[1], eklenecekSirketinBilgileri[2],
                        eklenecekSirketinBilgileri[3], eklenecekSirketinBilgileri[4]);
                if (i == 0) {
                    SirketKontrolcusu.Sirketler = new Hashtable();
                    SirketKontrolcusu.Sirketler.put(eklenecekSirket.Ad,eklenecekSirket);
                } else {
                    SirketKontrolcusu.Sirketler.put(eklenecekSirket.Ad,eklenecekSirket);
                }
                i++;
            }
        }
    }
}
