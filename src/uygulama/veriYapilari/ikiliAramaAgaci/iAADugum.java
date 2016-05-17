package uygulama.veriYapilari.ikiliAramaAgaci;

import uygulama.eleman.Kisi;
import uygulama.veriYapilari.bagliListe.BagliListe;

public class iAADugum {
    public Kisi kisi;

    public BagliListe Deneyimler;
    public BagliListe EgitimDurumu;

    public iAADugum sol;
    public iAADugum sag;


    public iAADugum() {
    }

    // sadece kişi bilgisini içeren düğüm
    public iAADugum(Kisi kisi) {
        this.kisi = kisi;
        this.Deneyimler = null;
        this.EgitimDurumu = null;
        this.sol = null;
        this.sag = null;
    }

    // tüm bilgileri içeren düğüm
    public iAADugum(Kisi kisi, BagliListe deneyimler, BagliListe egitimDurumu) {
        this.kisi = kisi;
        this.Deneyimler = deneyimler;
        this.EgitimDurumu = egitimDurumu;
        this.sol = null;
        this.sag = null;
    }
}
