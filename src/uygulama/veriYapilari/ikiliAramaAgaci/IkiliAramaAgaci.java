package uygulama.veriYapilari.ikiliAramaAgaci;

/**
 * Created by Sefa on 10.05.2016.
 */

public class IkiliAramaAgaci{

    private Dugum kok;
    private String dugumler;

    public IkiliAramaAgaci() {
    }

    public IkiliAramaAgaci(Dugum kok) {
        this.kok = kok;
    }

    public int dugumSayisi() {
        return dugumSayisi(kok);
    }

    public int dugumSayisi(Dugum dugum) {
        int sayac = 0;
        if (dugum != null){
            sayac = 1;
            sayac += dugumSayisi(dugum.sol);
            sayac += dugumSayisi(dugum.sag);
        }
        return sayac;
    }

    public int yaprakSayisi() {
        return yaprakSayisi(kok);
    }

    public int yaprakSayisi(Dugum dugum) {
        int sayac = 0;
        if (dugum != null){
            if ((dugum.sol == null) && (dugum.sag == null))
                sayac = 1;
            else
                sayac = sayac + yaprakSayisi(dugum.sol) + yaprakSayisi(dugum.sag);
        }
        return sayac;
    }

    public String dugumleriYazdir() {
        return dugumler;
    }

    private void ziyaret(Dugum dugum){
        dugumler += dugum.kisi.Ad + " ";
    }

    public void kisiEkle(Kisi kisi){
        Dugum ebeveyn = new Dugum();
        Dugum arama = kok;

        while (arama != null){
            ebeveyn = arama;
            // Aynı ada sahip kişi var ise
            if (kisi.Ad.compareTo(arama.kisi.Ad) == 0)
                return;
            // Adı daha küçükse (alfabetik olarak) (TODO: testler yapılacak)
            else if (kisi.Ad.compareTo(arama.kisi.Ad) < 0)
                arama = arama.sol;
            else
                arama = arama.sag;
        }

        if(kok.kisi == null)
        {
            if (kok == null)
                kok = new Dugum();

            kok.kisi = kisi;
        }
        else if (kisi.Ad.compareTo(ebeveyn.kisi.Ad) < 0)
        {
            ebeveyn.sol = new Dugum();
            ebeveyn.sol.kisi = kisi;
        }
        else
        {
            ebeveyn.sag = new Dugum();
            ebeveyn.sag.kisi = kisi;
        }
    }

    public String koktenSagaDolas() {
        dugumler = "";
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

    public String soldanSagaDolas() {
        dugumler = "";
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

    public String soldanKokeDolas(){
        dugumler = "";
        soldanKoke(kok);
        return this.dugumler;
    }

    private void soldanKoke(Dugum dugum){
        if (dugum == null)
            return;
        soldanKoke(dugum.sol);
        soldanKoke(dugum.sag);
        ziyaret(dugum);
    }

    public Kisi kisiAra(Kisi kisi){
        return aramaYap(kok, kisi);
    }

    private Kisi aramaYap(Dugum dugum, Kisi kisi){
        if (dugum == null)
            return null;
        else if (dugum.kisi == kisi)
            return dugum.kisi;
        else if (kisi.Ad.compareTo(dugum.kisi.Ad) < 0)
            return aramaYap(dugum.sol, kisi);
        else
            return aramaYap(dugum.sag, kisi);
    }
}
