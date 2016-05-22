package uygulama.kontrolculer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uygulama.Main;
import uygulama.sirket.Sirket;

import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;


public class SirketKontrolcusu implements Initializable {
    public static Hashtable Sirketler;

    public static Sirket sistemdekiSirket;

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
        if (sistemdekiSirket != null) {
            // sistemde kişi varsa alanları kişinin bilgileri ile doldur
            isYeriAdi.setText(sistemdekiSirket.Ad);
            tamAdres.setText(sistemdekiSirket.Adres);
            telefon.setText(sistemdekiSirket.Telefon);
            faks.setText(sistemdekiSirket.Faks);
            ePosta.setText(sistemdekiSirket.Eposta);
        }
    }

    public void KarsilamaEkraninaDon() throws Exception {
        // karşılama ekranını geri yükler ve geçiş yapar
        arayuz = FXMLLoader.load(getClass().getResource("../ekranlar/karsilamaEkrani.fxml"));
        Main.pencere.setTitle("İnsan Kaynakları Bilgi Sistemi");
        Main.pencere.setScene(new Scene(arayuz, 1280, 700));
        System.out.println("Karşılama Ekranına Geri Dönüldü.");
    }

    public void SistemeKaydet() throws Exception {
        if(!isYeriAdi.getText().isEmpty() && !tamAdres.getText().isEmpty() && !telefon.getText().isEmpty() &&
                !faks.getText().isEmpty() && !ePosta.getText().isEmpty()){
            if(sistemdekiSirket != null){
                sistemdekiSirket = null;
                sistemdekiSirket = new Sirket(isYeriAdi.getText(),tamAdres.getText(), telefon.getText(),
                        faks.getText(), ePosta.getText());
                Sirketler.remove(sistemdekiSirket.Ad);
                Sirketler.put(sistemdekiSirket.Ad, sistemdekiSirket);
            }
            else{
                kaydedilecekSirket = new Sirket(isYeriAdi.getText(),tamAdres.getText(), telefon.getText(), faks.getText(), ePosta.getText());
                if(Sirketler == null){
                    Sirketler = new Hashtable();
                    Sirketler.put(kaydedilecekSirket.Ad, kaydedilecekSirket);
                }
                else{
                    Sirketler.put(kaydedilecekSirket.Ad, kaydedilecekSirket);
                }
            }
            KarsilamaEkraninaDon();
        }
        else {
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
}
