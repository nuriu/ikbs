package uygulama.veriYapilari.obek;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import uygulama.eleman.Kisi;

public class Obek {
    private int boyut = 0;
    private int maksimumBoyut = 100;
    private oDugum[] obekDizisi;

    public Obek() {
        obekDizisi = new oDugum[maksimumBoyut];
    }

    public Obek(int maksimumBoyut) {
        this.maksimumBoyut = maksimumBoyut;
        obekDizisi = new oDugum[maksimumBoyut];
    }

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

    public boolean KisiAra(Kisi kisi) {
        boolean durum = false;
        for (int i = 0; i < boyut; i++) {
            if (obekDizisi[i].Kisi.Ad == kisi.Ad) {
                durum = true;
                break;
            }
        }
        return durum;
    }

    public oDugum adaGoreKisiAra(String ad) {
        oDugum kisi = null;
        for (int i = 0; i < boyut; i++) {
            if (obekDizisi[i].Kisi.Ad.equals(ad)) {
                kisi = obekDizisi[i];
            }
        }
        return kisi;
    }

    public void kisiGuncelle(String ad, Kisi kisi) {
        for (int i = 0; i < boyut; i++) {
            if (obekDizisi[i].Kisi.Ad.equals(ad)) {
                obekDizisi[i].Kisi = kisi;
            }
        }
    }

    public ObservableList<String> KisileriListele() {
        ObservableList<String> basvuranlar = FXCollections.observableArrayList();
        for (int i = 0; i < boyut; i++) {
            if (obekDizisi[i] != null) {
                String uygunluk = String.format("%1.2f", obekDizisi[i].Uygunluk);
                basvuranlar.add(uygunluk + " | " + obekDizisi[i].Kisi.Ad);
            }
        }
        return basvuranlar;
    }

    public ObservableList<String> ingilizceBilenler() {
        ObservableList<String> ingilizceBilenler = FXCollections.observableArrayList();
        for (int i = 0; i < boyut; i++) {
            if (obekDizisi[i] != null) {
                if (obekDizisi[i].Kisi.YabanciDil.toLowerCase().contains("ingilizce")) {
                    String uygunluk = String.format("%1.2f", obekDizisi[i].Uygunluk);
                    ingilizceBilenler.add(uygunluk + " | " + obekDizisi[i].Kisi.Ad);
                }
            }
        }
        return ingilizceBilenler;
    }

    public void adaGoreKisiSil(String ad) {
        for (int i = 0; i < boyut; i++) {
            if (obekDizisi[i].Kisi.Ad.equals(ad)) {
                obekDizisi[i] = null;
            }
        }
    }
}
