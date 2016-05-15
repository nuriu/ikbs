package uygulama.veriYapilari.ikiliAramaAgaci;

import uygulama.eleman.Kisi;
import uygulama.veriYapilari.bagliListe.BagliListe;

public class Dugum {
    public Kisi kisi;

    public BagliListe Deneyimler;
    public BagliListe EgitimDurumu;

    public Dugum sol;
    public Dugum sag;


    public Dugum() {
    }

    // sadece kişi bilgisini içeren düğüm
    public Dugum(Kisi kisi) {
        this.kisi = kisi;
        this.Deneyimler = null;
        this.EgitimDurumu = null;
        this.sol = null;
        this.sag = null;
    }

    // tüm bilgileri içeren düğüm
    public Dugum(Kisi kisi, BagliListe deneyimler, BagliListe egitimDurumu) {
        this.kisi = kisi;
        this.Deneyimler = deneyimler;
        this.EgitimDurumu = egitimDurumu;
        this.sol = null;
        this.sag = null;
    }
}
