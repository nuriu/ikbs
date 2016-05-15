package uygulama.veriYapilari.ikiliAramaAgaci;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import uygulama.eleman.Kisi;
import uygulama.veriYapilari.bagliListe.BagliListe;

public class IkiliAramaAgaci {
    public ObservableList<String> dugumler;
    private Dugum kok;

    public IkiliAramaAgaci() {
    }

    public IkiliAramaAgaci(Dugum kok) {
        this.kok = kok;
    }

    public IkiliAramaAgaci(Dugum dugum, BagliListe deneyim, BagliListe egitim) {
        this.kok = dugum;
        this.kok.Deneyimler = deneyim;
        this.kok.EgitimDurumu = egitim;
    }

    // TODO: eleman sayısını test et
    public int dugumSayisi() {
        return dugumSayisi(kok);
    }

    private int dugumSayisi(Dugum dugum) {
        int sayac = 0;
        if (dugum != null) {
            sayac = 1;
            sayac += dugumSayisi(dugum.sol);
            sayac += dugumSayisi(dugum.sag);
        }
        return sayac;
    }

    // TODO: yaprak düğümlerin sayısına gerek yok ise sil
    public int yaprakSayisi() {
        return yaprakSayisi(kok);
    }

    private int yaprakSayisi(Dugum dugum) {
        int sayac = 0;
        if (dugum != null) {
            if ((dugum.sol == null) && (dugum.sag == null))
                sayac = 1;
            else
                sayac = sayac + yaprakSayisi(dugum.sol) + yaprakSayisi(dugum.sag);
        }
        return sayac;
    }

    public void kisiEkle(Kisi kisi, BagliListe deneyimler, BagliListe egitimDurumu) {
        Dugum ebeveyn = new Dugum();
        Dugum arama = kok;

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
                kok = new Dugum();
            kok.kisi = kisi;
            kok.Deneyimler = deneyimler;
            kok.EgitimDurumu = egitimDurumu;
        } else if (kisi.Ad.compareTo(ebeveyn.kisi.Ad) < 0) {
            ebeveyn.sol = new Dugum();
            ebeveyn.sol.kisi = kisi;
            ebeveyn.sol.Deneyimler = deneyimler;
            ebeveyn.sol.EgitimDurumu = egitimDurumu;
        } else {
            ebeveyn.sag = new Dugum();
            ebeveyn.sag.kisi = kisi;
            ebeveyn.sag.Deneyimler = deneyimler;
            ebeveyn.sag.EgitimDurumu = egitimDurumu;
        }
    }

    public ObservableList<String> dugumListesi() {
        return dugumler;
    }

    private void ziyaret(Dugum dugum) {
        if (dugumler == null) {
            dugumler = FXCollections.observableArrayList(dugum.kisi.Ad);
        } else {
            if (dugum.kisi != null)
                dugumler.add(dugum.kisi.Ad);
        }
    }

    public ObservableList<String> koktenSagaDolas() {
        dugumler = null;
        koktenSaga(kok);
        return this.dugumler;
    }

    private void koktenSaga(Dugum dugum) {
        if (dugum == null)
            return;
        ziyaret(dugum);
        koktenSaga(dugum.sol);
        koktenSaga(dugum.sag);
    }

    public ObservableList<String> soldanSagaDolas() {
        dugumler = null;
        soldanSaga(kok);
        return this.dugumler;
    }

    private void soldanSaga(Dugum dugum) {
        if (dugum == null)
            return;
        soldanSaga(dugum.sol);
        ziyaret(dugum);
        soldanSaga(dugum.sag);
    }

    public ObservableList<String> soldanKokeDolas() {
        dugumler = null;
        soldanKoke(kok);
        return this.dugumler;
    }

    private void soldanKoke(Dugum dugum) {
        if (dugum == null)
            return;
        soldanKoke(dugum.sol);
        soldanKoke(dugum.sag);
        ziyaret(dugum);
    }

    public Kisi kisiAra(Kisi kisi) {
        return aramaYap(kok, kisi);
    }

    private Kisi aramaYap(Dugum dugum, Kisi kisi) {
        if (dugum == null)
            return null;
        else if (dugum.kisi == kisi)
            return dugum.kisi;
        else if (kisi.Ad.compareTo(dugum.kisi.Ad) < 0)
            return aramaYap(dugum.sol, kisi);
        else
            return aramaYap(dugum.sag, kisi);
    }

    private Dugum successor(Dugum dugum) {
        Dugum ebeveyn = dugum;
        Dugum successor = dugum;
        Dugum simdiki = dugum.sag;

        while (simdiki != null) {
            ebeveyn = successor;
            successor = simdiki;
            simdiki = simdiki.sol;
        }
        if (successor != dugum.sag) {
            ebeveyn.sol = successor.sag;
            successor.sag = dugum.sag;
        }
        return successor;
    }

    public boolean kisiSil(Kisi kisi) {
        Dugum simdiki = kok;
        Dugum ebeveyn = kok;
        boolean solMu = true;

        while (simdiki.kisi != kisi) {
            ebeveyn = simdiki;
            // kişi adı alfabetik olarak küçükse
            if (kisi.Ad.compareTo(simdiki.kisi.Ad) < 0) {
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
            Dugum successor = successor(simdiki);
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