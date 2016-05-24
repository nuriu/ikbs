package uygulama.veriYapilari.obek;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import uygulama.eleman.Kisi;

public class Obek {
    private int boyut;
    private int maksimumBoyut;
    private oDugum[] obekDizisi;

    public boolean ekle(Kisi kisi) {
        if (boyut == maksimumBoyut)
            return false;

        oDugum eklenecekODugum = new oDugum(kisi);
        obekDizisi[boyut] = eklenecekODugum;
        yukariTasi(boyut++);
        return true;
    }

    public void yukariTasi(int indis) {
        int ebeveyn = (indis - 1) / 2;
        oDugum alt = obekDizisi[indis];

        while (indis > 0 && obekDizisi[ebeveyn].Uygunluk < alt.Uygunluk) {
            obekDizisi[indis] = obekDizisi[ebeveyn];
            indis = ebeveyn;
            ebeveyn = (ebeveyn - 1) / 2;
        }

        obekDizisi[indis] = alt;
    }

    public oDugum enBuyuguSil() {
        oDugum kok = obekDizisi[0];
        obekDizisi[0] = obekDizisi[--boyut];
        asagiTasi(0);
        return kok;
    }

    public void asagiTasi(int indis) {
        int buyukCocuk;
        oDugum ust = obekDizisi[indis];

        while (indis < boyut / 2) {
            int solCocuk = 2 * indis + 1;
            int sagCocuk = solCocuk + 1;

            if (sagCocuk < boyut && obekDizisi[solCocuk].Uygunluk < obekDizisi[sagCocuk].Uygunluk)
                buyukCocuk = sagCocuk;
            else
                buyukCocuk = solCocuk;

            if (ust.Uygunluk >= obekDizisi[buyukCocuk].Uygunluk)
                break;

            obekDizisi[indis] = obekDizisi[buyukCocuk];
            indis = buyukCocuk;
        }
        obekDizisi[indis] = ust;
    }

    public boolean KisiAra(Kisi kisi){
        boolean durum = false;
        for (int i = 0; i < boyut; i++){
            if (obekDizisi[i].Kisi.Ad == kisi.Ad){
                durum = true;
                break;
            }
        }
        return durum;
    }

    public ObservableList<String> KisileriListele(){
        ObservableList<String> basvuranlar = FXCollections.observableArrayList();
        for (int i = 0; i < boyut; i++){
            if (obekDizisi[i] != null){
                basvuranlar.add(obekDizisi[i].Uygunluk + " | " + obekDizisi[i].Kisi.Ad);
            }
        }
        return basvuranlar;
    }
}
