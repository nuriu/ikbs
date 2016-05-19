package uygulama.kontrolculer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import uygulama.Main;
import uygulama.eleman.Kisi;
import uygulama.veriYapilari.ikiliAramaAgaci.IkiliAramaAgaci;
import uygulama.veriYapilari.ikiliAramaAgaci.iAADugum;

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

    public void ElemanGirisi() throws Exception {
        if (kisiListesi.getSelectionModel().getSelectedItem() != null) {
            String[] sistemdekiKisininBilgileri = kisiListesi.getSelectionModel().getSelectedItem().split(" \\| ");
            ElemanKontrolcusu.SistemdekiKisi = ElemanKontrolcusu.Kisiler.kisiAra(sistemdekiKisininBilgileri[0]);

            // eleman ekranını yükle ve geçiş yap
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
        // şirket ekranını yükle ve geçiş yap
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/sirketEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi - Şirket Ekranı");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Şirket Ekranına Geçildi.");
    }

    public void ElemanKaydi() throws Exception {
        // eleman kayıt ekranını yükle ve geçiş yap
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/elemanKayitEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi - Eleman Kayıt Ekranı");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Eleman Kayıt Ekranına Geçildi.");
    }

    public void SirketKaydi() throws Exception {
        // şirket kayıt ekranını yükle ve geçiş yap
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/sirketKayitEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi - Şirket Kayıt Ekranı");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Şirket Kayıt Ekranına Geçildi.");
    }

    private void kisiAgaciniOlustur() throws IOException {
        if (ElemanKontrolcusu.Kisiler == null || ElemanKontrolcusu.Kisiler.dugumSayisi() == 0) {
            // açılışta dosyadan kişileri çekip sistemdeki ağaca kaydet
            String satir = null;                // dosyadaki satır
            Kisi eklenecekKisi = null;          // sisteme eklenecek kişi
            iAADugum d = new iAADugum();        // ağaç için kök düğüm
            ElemanKontrolcusu.Kisiler = null;   // sistemdeki ağacımız
            // dosyayı yükle
            InputStream elemanDosyasi = new FileInputStream("eleman.txt");
            InputStreamReader eOkuyucu = new InputStreamReader(elemanDosyasi, Charset.forName("UTF-8"));
            BufferedReader okuyucu = new BufferedReader(eOkuyucu);

            int i = 0;
            while ((satir = okuyucu.readLine()) != null) {
                // TODO: deneyim ve eğitim bilgileri de dosyadan alınabilir
                String[] eklenecekKisininBilgileri = satir.split(", ");
                eklenecekKisi = new Kisi(eklenecekKisininBilgileri[0],
                        eklenecekKisininBilgileri[1], eklenecekKisininBilgileri[2],
                        eklenecekKisininBilgileri[3], eklenecekKisininBilgileri[4],
                        eklenecekKisininBilgileri[5], eklenecekKisininBilgileri[6],
                        eklenecekKisininBilgileri[7], eklenecekKisininBilgileri[8],
                        eklenecekKisininBilgileri[9], eklenecekKisininBilgileri[10]);
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
}
