package uygulama.kontrolculer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import uygulama.Main;
import uygulama.eleman.Kisi;
import uygulama.veriYapilari.ikiliAramaAgaci.Dugum;
import uygulama.veriYapilari.ikiliAramaAgaci.IkiliAramaAgaci;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

public class KarsilamaKontrolcusu implements Initializable {
    @FXML
    public ListView<String> kisiListesi;
    @FXML
    public ListView<String> sirketListesi;
    private Parent arayuz;

    public KarsilamaKontrolcusu() {
    }

    @Override   // başlangıçta çağrılan fonksiyon
    public void initialize(URL location, ResourceBundle resources) {
        try {
            kisiAgaciniOlustur();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // penceredeki kişi listesine alfabetik sırayla yazdır
        kisiListesi.setItems(ElemanKontrolcusu.Kisiler.soldanSagaDolas());
    }

    public void ElemanGirisi(ActionEvent actionEvent) throws Exception {
        // eleman ekranını yükler ve geçiş yapar
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/elemanEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi - Eleman Ekranı");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Eleman Ekranına Geçildi.");
    }

    public void SirketGirisi(ActionEvent actionEvent) throws Exception {
        // şirket ekranını yükler ve geçiş yapar
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/sirketEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi - Şirket Ekranı");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Şirket Ekranına Geçildi.");
    }

    public void ElemanKaydi(ActionEvent actionEvent) throws Exception {
        // eleman kayıt ekranını yükler ve geçiş yapar
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/elemanKayitEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi - Eleman Kayıt Ekranı");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Eleman Kayıt Ekranına Geçildi.");
    }

    public void SirketKaydi(ActionEvent actionEvent) throws Exception {
        // şirket kayıt ekranını yükler ve geçiş yapar
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/sirketKayitEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi - Şirket Kayıt Ekranı");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Şirket Kayıt Ekranına Geçildi.");
    }

    private void kisiAgaciniOlustur() throws IOException {
        // açılışta dosyadan kişileri çekip sistemdeki ağaca kaydeder
        String satir = null;                // dosyadaki satır
        Kisi eklenecekKisi = null;          // sisteme eklenecek kişi
        Dugum d = new Dugum();              // ağaç için kök düğüm
        ElemanKontrolcusu.Kisiler = null;   // sistemdeki ağacımız
        // dosyayı yükle
        InputStream elemanDosyasi = new FileInputStream("eleman.txt");
        InputStreamReader eOkuyucu = new InputStreamReader(elemanDosyasi, Charset.forName("UTF-8"));
        BufferedReader okuyucu = new BufferedReader(eOkuyucu);

        int i = 0;
        while ((satir = okuyucu.readLine()) != null) {
            eklenecekKisi = new Kisi(satir);
            if (i == 0) {                   // ilk satırsa ağacı ve kökü oluştur
                d.kisi = eklenecekKisi;
                ElemanKontrolcusu.Kisiler = new IkiliAramaAgaci(d, null, null);
            } else {                        // ilk satır değilse ağaca ekle
                ElemanKontrolcusu.Kisiler.kisiEkle(eklenecekKisi, null, null);
            }
            i++;
        }
    }
}
