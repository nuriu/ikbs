package uygulama.veriYapilari.ikiliAramaAgaci;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import uygulama.eleman.Kisi;
import uygulama.veriYapilari.bagliListe.BagliListe;

public class IkiliAramaAgaci {
    public ObservableList<String> dugumler;
    private iAADugum kok;
    private int duzey = 0;

    public IkiliAramaAgaci() { }

    public IkiliAramaAgaci(iAADugum kok) {
        this.kok = kok;
    }

    public IkiliAramaAgaci(iAADugum iAADugum, BagliListe deneyim, BagliListe egitim) {
        this.kok = iAADugum;
        this.kok.Deneyimler = deneyim;
        this.kok.EgitimDurumu = egitim;
    }

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

    public int derinlik() { return  derinlik(kok); }

    private int derinlik(iAADugum iAADugum) {
        if (iAADugum == null)
            return -1;

        int solDerinlik = derinlik(iAADugum.sol);
        int sagDerinlik = derinlik(iAADugum.sag);

        if (solDerinlik > sagDerinlik)
            return solDerinlik + 1;

        return sagDerinlik + 1;
    }

    private int seviyeBul(iAADugum iAADugum, String ad, int seviye) {
        if (iAADugum == null)
            return 0;

        if (iAADugum.kisi.Ad.equals(ad))
            return seviye;

        int seviyeDus = seviyeBul(iAADugum.sol, ad, seviye + 1);

        if (seviyeDus != 0)
            return seviyeDus;

        seviyeDus = seviyeBul(iAADugum.sag, ad, seviye + 1);
        return seviyeDus;
    }

    public int seviyeyiGetir(iAADugum iAADugum, String ad) {
        return seviyeBul(iAADugum, ad, 0);
    }

    public void kisiEkle(Kisi kisi, BagliListe deneyimler, BagliListe egitimDurumu) {
        iAADugum ebeveyn = new iAADugum();
        iAADugum arama = kok;

        while (arama != null) {
            ebeveyn = arama;
            if (kisi.Ad.compareTo(arama.kisi.Ad) == 0)
                return;
            else if (kisi.Ad.compareTo(arama.kisi.Ad) < 0)
                arama = arama.sol;
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
            dugumler = FXCollections.observableArrayList(iAADugum.kisi.bilgileriGetir() + " | Düzey: " + seviyeyiGetir(kok, iAADugum.kisi.Ad));
        } else {
            if (iAADugum.kisi != null)
                dugumler.add(iAADugum.kisi.bilgileriGetir() + " | Düzey: " + seviyeyiGetir(kok, iAADugum.kisi.Ad));
        }
    }

    public ObservableList<String> koktenSagaDolas() {
        dugumler = null;
        duzey = 0;
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
        duzey = 0;
        soldanSaga(kok);
        return this.dugumler;
    }

    private void soldanSaga(iAADugum iAADugum) {
        if (iAADugum == null)
            return;
        soldanSaga(iAADugum.sol);
        ziyaret(iAADugum);
        duzey++;
        soldanSaga(iAADugum.sag);
    }

    public ObservableList<String> soldanKokeDolas() {
        dugumler = null;
        duzey = 0;
        soldanKoke(kok);
        return this.dugumler;
    }

    private void soldanKoke(iAADugum iAADugum) {
        if (iAADugum == null)
            return;
        soldanKoke(iAADugum.sol);
        soldanKoke(iAADugum.sag);
        ziyaret(iAADugum);
        duzey++;
    }

    public void kisiGuncelle(String kisininIsmi, iAADugum yeniBilgiler) {
        guncelle(kok, kisininIsmi, yeniBilgiler);
    }

    private void guncelle(iAADugum iAADugum, String kisininIsmi, iAADugum yeniBilgiler) {
        if (iAADugum == null)
            return;
        else if (kisininIsmi.compareTo(iAADugum.kisi.Ad) == 0)
            iAADugum = yeniBilgiler;
        else if (kisininIsmi.compareTo(iAADugum.kisi.Ad) < 0)
            guncelle(iAADugum.sol, kisininIsmi, yeniBilgiler);
        else
            guncelle(iAADugum.sag, kisininIsmi, yeniBilgiler);
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

        if (simdiki.sol == null && simdiki.sag == null) {
            if (simdiki == kok)
                kok = null;
            else if (solMu)
                ebeveyn.sol = null;
            else
                ebeveyn.sag = null;
        } else if (simdiki.sag == null) {
            if (simdiki == kok)
                kok = simdiki.sol;
            else if (solMu)
                ebeveyn.sol = simdiki.sol;
            else
                ebeveyn.sag = simdiki.sol;
        } else if (simdiki.sol == null) {
            if (simdiki == kok)
                kok = simdiki.sag;
            else if (solMu)
                ebeveyn.sol = simdiki.sag;
            else
                ebeveyn.sag = simdiki.sag;
        } else {
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