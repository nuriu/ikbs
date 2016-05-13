package uygulama.veriYapilari.ikiliAramaAgaci;

import uygulama.eleman.Kisi;
import uygulama.veriYapilari.bagliListe.BagliListe;

/**
 * Created by Sefa on 10.05.2016.
 */

public class Dugum {
    public Kisi kisi;
    public BagliListe Deneyimler;
    public BagliListe EgitimDurumu;
    public Dugum sol;
    public Dugum sag;


    public Dugum() {
    }

    public Dugum(Kisi kisi) {
        this.kisi = kisi;
        this.Deneyimler = null;
        this.EgitimDurumu = null;
        this.sol = null;
        this.sag = null;
    }

    public Dugum(Kisi kisi, BagliListe deneyimler, BagliListe egitimDurumu) {
        this.kisi = kisi;
        this.Deneyimler = deneyimler;
        this.EgitimDurumu = egitimDurumu;
        this.sol = null;
        this.sag = null;
    }
}
