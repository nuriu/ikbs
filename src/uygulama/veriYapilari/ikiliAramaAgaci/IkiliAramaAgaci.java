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
        dugumler += dugum.veri + " ";
    }

    public void preOrder() {
        dugumler = "";
        preOrderInt(kok);
    }

    private void preOrderInt(Dugum dugum) {
        if (dugum == null)
            return;;
        ziyaret(dugum);
        preOrderInt(dugum.sol);
        preOrderInt(dugum.sag);
    }

    public void inOrder() {
        dugumler = "";
        inOrderInt(kok);
    }

    private void inOrderInt(Dugum dugum) {
        if (dugum == null)
            return;
        inOrderInt(dugum.sol);
        ziyaret(dugum);
        inOrderInt(dugum.sag);
    }

    public void postOrder(){
        dugumler = "";
        postOrderInt(kok);
    }

    private void postOrderInt (Dugum dugum){
        if (dugum == null)
            return;
        postOrderInt(dugum.sol);
        postOrderInt(dugum.sag);
        ziyaret(dugum);
    }

    public void  ekle(int deger){
        Dugum ebeveyn = new Dugum();
        Dugum arama = kok;

        while (arama != null){
            ebeveyn = arama;
            if (deger == (int)arama.veri)
                return;
            else if (deger < (int)arama.veri)
                arama = arama.sol;
            else
                arama = arama.sag;
        }

        Dugum eklenecek = new Dugum(deger);
        if(kok == null)
            kok = eklenecek;
        else if (deger < (int)ebeveyn.veri)
            ebeveyn.sol = eklenecek;
        else
            ebeveyn.sag = eklenecek;
    }

    public Dugum ara(int anahtar){
        return  araInt(kok, anahtar);
    }

    private Dugum araInt(Dugum dugum, int anahtar){
        if (dugum == null)
            return  null;
        else if ((int)dugum.veri == anahtar)
            return  dugum;
        else if ((int)dugum.veri > anahtar)
            return araInt(dugum.sol, anahtar);
        else
            return araInt(dugum.sag, anahtar);
    }

    public Dugum minDeger(){
        Dugum gecici = kok;
        while (gecici.sol != null)
            gecici = gecici.sol;
        return gecici;
    }

    public Dugum maxDeger(){
        Dugum gecici = kok;
        while(gecici.sag != null)
            gecici = gecici.sag;
        return  gecici;
    }

    private Dugum successor(Dugum dugum){
        Dugum ebeveyn = dugum;
        Dugum successor = dugum;
        Dugum simdiki = dugum.sag;
        while (simdiki != null){
            ebeveyn = successor;
            successor = simdiki;
            simdiki = simdiki.sol;
        }
        if (successor != dugum.sag){
            ebeveyn.sol = successor.sag;
            successor.sag = dugum.sag;
        }
        return successor;
    }

    public boolean sil(int deger){
        Dugum simdiki = kok;
        Dugum ebeveyn = kok;
        boolean solMu = true;
        while ((int)simdiki.veri != deger){
            ebeveyn = simdiki;
            if (deger < (int)simdiki.veri){
                solMu = true;
                simdiki = simdiki.sol;
            }
            else{
                solMu = false;
                simdiki = simdiki.sag;
            }
            if (simdiki == null)
                return false;
        }

        //Durum 1
        if (simdiki.sol == null && simdiki.sag == null){
            if (simdiki == kok)
                kok = null;
            else if (solMu)
                ebeveyn.sol = null;
            else
                ebeveyn.sag = null;
        }

        //Durum 2
        else if (simdiki.sag == null){
            if (simdiki == kok)
                kok = simdiki.sol;
            else if (solMu)
                ebeveyn.sol = simdiki.sol;
            else
                ebeveyn.sag = simdiki.sol;
        }
        else if (simdiki.sol == null){
            if (simdiki == kok)
                kok = simdiki.sag;
            else if (solMu)
                ebeveyn.sol = simdiki.sag;
            else
                ebeveyn.sag = simdiki.sag;
        }

        //Durum 3
        else {
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
