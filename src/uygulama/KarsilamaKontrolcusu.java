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

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
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
        try {
            kisiAgaciniOlustur();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void kisiAgaciniOlustur() throws IOException {
        String satir = null;
        Kisi eklenecekKisi = null;
        Dugum d = new Dugum();
        IkiliAramaAgaci kisiAgaci = null;

        InputStream elemanDosyasi = new FileInputStream("eleman.txt");
        InputStreamReader eOkuyucu = new InputStreamReader(elemanDosyasi, Charset.forName("UTF-8"));
        BufferedReader okuyucu = new BufferedReader(eOkuyucu);

        int i = 0;
        while ((satir = okuyucu.readLine()) != null) {
            eklenecekKisi = new Kisi(satir);
            if (i == 0) {
                d.kisi = eklenecekKisi;
                kisiAgaci = new IkiliAramaAgaci(d);
            } else {
                kisiAgaci.kisiEkle(eklenecekKisi);
            }
            i++;
        }
        kisiListesi.setItems(kisiAgaci.soldanSagaDolas());
    }
}
