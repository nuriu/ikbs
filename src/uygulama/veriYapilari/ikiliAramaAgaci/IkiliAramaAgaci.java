package uygulama.veriYapilari.ikiliAramaAgaci;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import uygulama.eleman.Kisi;
import uygulama.veriYapilari.bagliListe.BagliListe;

public class IkiliAramaAgaci {
    public ObservableList<String> dugumler;
    private iAADugum kok;

    public IkiliAramaAgaci() {
    }

    public IkiliAramaAgaci(iAADugum kok) {
        this.kok = kok;
    }

    public IkiliAramaAgaci(iAADugum iAADugum, BagliListe deneyim, BagliListe egitim) {
        this.kok = iAADugum;
        this.kok.Deneyimler = deneyim;
        this.kok.EgitimDurumu = egitim;
    }

    // TODO: eleman sayısını test et
    public int dugumSayisi() {
        return dugumSayisi(kok);
    }

    private int dugumSayisi(iAADugum iAADugum) {
        int sayac = 0;
        if (iAADugum != null) {
            sayac = 1;
            sayac += dugumSayisi(iAADugum.sol);
            sayac += dugumSayisi(iAADugum.sag);
        }
        return sayac;
    }

    // TODO: yaprak düğümlerin sayısına gerek yok ise sil
    public int yaprakSayisi() {
        return yaprakSayisi(kok);
    }

    private int yaprakSayisi(iAADugum iAADugum) {
        int sayac = 0;
        if (iAADugum != null) {
            if ((iAADugum.sol == null) && (iAADugum.sag == null))
                sayac = 1;
            else
                sayac = sayac + yaprakSayisi(iAADugum.sol) + yaprakSayisi(iAADugum.sag);
        }
        return sayac;
    }

    public void kisiEkle(Kisi kisi, BagliListe deneyimler, BagliListe egitimDurumu) {
        iAADugum ebeveyn = new iAADugum();
        iAADugum arama = kok;

        while (arama != null) {
            ebeveyn = arama;
            // kişinin adı zaten varsa ekleme
            if (kisi.Ad.compareTo(arama.kisi.Ad) == 0)
                return;
                // kişinin adı alfabetik olarak önce geliyorsa
            else if (kisi.Ad.compareTo(arama.kisi.Ad) < 0)
                arama = arama.sol;
                // kişinin adı alfabetik olarak sonra geliyorsa
            else
                arama = arama.sag;
        }

        if (kok.kisi == null) {
            if (kok == null)
                kok = new iAADugum();
            kok.kisi = kisi;
            kok.Deneyimler = deneyimler;
            kok.EgitimDurumu = egitimDurumu;
        } else if (kisi.Ad.compareTo(ebeveyn.kisi.Ad) < 0) {
            ebeveyn.sol = new iAADugum();
            ebeveyn.sol.kisi = kisi;
            ebeveyn.sol.Deneyimler = deneyimler;
            ebeveyn.sol.EgitimDurumu = egitimDurumu;
        } else {
            ebeveyn.sag = new iAADugum();
            ebeveyn.sag.kisi = kisi;
            ebeveyn.sag.Deneyimler = deneyimler;
            ebeveyn.sag.EgitimDurumu = egitimDurumu;
        }
    }

    public ObservableList<String> dugumListesi() {
        return dugumler;
    }

    private void ziyaret(iAADugum iAADugum) {
        if (dugumler == null) {
            dugumler = FXCollections.observableArrayList(iAADugum.kisi.bilgileriGetir());
        } else {
            if (iAADugum.kisi != null)
                dugumler.add(iAADugum.kisi.bilgileriGetir());
        }
    }

    public ObservableList<String> koktenSagaDolas() {
        dugumler = null;
        koktenSaga(kok);
        return this.dugumler;
    }

    private void koktenSaga(iAADugum iAADugum) {
        if (iAADugum == null)
            return;
        ziyaret(iAADugum);
        koktenSaga(iAADugum.sol);
        koktenSaga(iAADugum.sag);
    }

    public ObservableList<String> soldanSagaDolas() {
        dugumler = null;
        soldanSaga(kok);
        return this.dugumler;
    }

    private void soldanSaga(iAADugum iAADugum) {
        if (iAADugum == null)
            return;
        soldanSaga(iAADugum.sol);
        ziyaret(iAADugum);
        soldanSaga(iAADugum.sag);
    }

    public ObservableList<String> soldanKokeDolas() {
        dugumler = null;
        soldanKoke(kok);
        return this.dugumler;
    }

    private void soldanKoke(iAADugum iAADugum) {
        if (iAADugum == null)
            return;
        soldanKoke(iAADugum.sol);
        soldanKoke(iAADugum.sag);
        ziyaret(iAADugum);
    }

    public iAADugum kisiAra(String kisininIsmi) {
        return aramaYap(kok, kisininIsmi);
    }

    private iAADugum aramaYap(iAADugum iAADugum, String kisininIsmi) {
        if (iAADugum == null)
            return null;
        else if (kisininIsmi.compareTo(iAADugum.kisi.Ad) == 0)
            return iAADugum;
        else if (kisininIsmi.compareTo(iAADugum.kisi.Ad) < 0)
            return aramaYap(iAADugum.sol, kisininIsmi);
        else
            return aramaYap(iAADugum.sag, kisininIsmi);
    }

    private iAADugum successor(iAADugum iAADugum) {
        iAADugum ebeveyn = iAADugum;
        iAADugum successor = iAADugum;
        iAADugum simdiki = iAADugum.sag;

        while (simdiki != null) {
            ebeveyn = successor;
            successor = simdiki;
            simdiki = simdiki.sol;
        }
        if (successor != iAADugum.sag) {
            ebeveyn.sol = successor.sag;
            successor.sag = iAADugum.sag;
        }
        return successor;
    }

    public boolean kisiSil(String kisiAdi) {
        iAADugum simdiki = kok;
        iAADugum ebeveyn = kok;
        boolean solMu = true;

        while (kisiAdi.compareTo(simdiki.kisi.Ad) != 0) {
            ebeveyn = simdiki;
            // kişi adı alfabetik olarak küçükse
            if (kisiAdi.compareTo(simdiki.kisi.Ad) < 0) {
                solMu = true;
                simdiki = simdiki.sol;
            } else {
                solMu = false;
                simdiki = simdiki.sag;
            }
            if (simdiki == null)
                return false;
        }

        // yaprak düğüm ise
        if (simdiki.sol == null && simdiki.sag == null) {
            if (simdiki == kok)
                kok = null;
            else if (solMu)
                ebeveyn.sol = null;
            else
                ebeveyn.sag = null;
        } else if (simdiki.sag == null) {   // sağı boş ise
            if (simdiki == kok)
                kok = simdiki.sol;
            else if (solMu)
                ebeveyn.sol = simdiki.sol;
            else
                ebeveyn.sag = simdiki.sol;
        } else if (simdiki.sol == null) {   // solu boş işse
            if (simdiki == kok)
                kok = simdiki.sag;
            else if (solMu)
                ebeveyn.sol = simdiki.sag;
            else
                ebeveyn.sag = simdiki.sag;
        } else {                            // iki çocuğuda dolu ise
            iAADugum successor = successor(simdiki);
            if (simdiki == kok)
                kok = successor;
            else if (solMu)
                ebeveyn.sol = successor;
            else
                ebeveyn.sag = successor;

            successor.sol = simdiki.sol;
        }
        return true;
    }
}