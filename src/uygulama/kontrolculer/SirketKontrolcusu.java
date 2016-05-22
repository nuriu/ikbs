package uygulama.kontrolculer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uygulama.Main;
import uygulama.sirket.Sirket;

import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;


public class SirketKontrolcusu implements Initializable {
    public static Hashtable Sirketler;

    private Parent arayuz;
    private Sirket kaydedilecekSirket;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void KarsilamaEkraninaDon() throws Exception {
        // karşılama ekranını geri yükler ve geçiş yapar
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/karsilamaEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Karşılama Ekranına Geri Dönüldü.");
    }

    public void SistemeKaydet() throws Exception {



    }


}
