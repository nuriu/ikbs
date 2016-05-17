package uygulama.veriYapilari.obek;

public class Obek {
    private int boyut;
    private int maksimumBoyut;
    private Dugum[] obekDizisi;

    public boolean ekle(int veri) {
        if (boyut == maksimumBoyut)
            return false;

        Dugum eklenecekDugum = new Dugum(veri);
        obekDizisi[boyut] = eklenecekDugum;
        yukariTasi(boyut++);
        return true;
    }

    public void yukariTasi(int indis) {
        int ebeveyn = (indis - 1) / 2;
        Dugum alt = obekDizisi[indis];

        while (indis > 0 && obekDizisi[ebeveyn].veri < alt.veri) {
            obekDizisi[indis] = obekDizisi[ebeveyn];
            indis = ebeveyn;
            ebeveyn = (ebeveyn - 1) / 2;
        }

        obekDizisi[indis] = alt;
    }

    public Dugum enBuyuguSil() {
        Dugum kok = obekDizisi[0];
        obekDizisi[0] = obekDizisi[--boyut];
        asagiTasi(0);
        return kok;
    }

    public void asagiTasi(int indis) {
        int buyukCocuk;
        Dugum ust = obekDizisi[indis];

        while (indis < boyut / 2) {
            int solCocuk = 2 * indis + 1;
            int sagCocuk = solCocuk + 1;

            if (sagCocuk < boyut && obekDizisi[solCocuk].veri < obekDizisi[sagCocuk].veri)
                buyukCocuk = sagCocuk;
            else
                buyukCocuk = solCocuk;

            if (ust.veri >= obekDizisi[buyukCocuk].veri)
                break;

            obekDizisi[indis] = obekDizisi[buyukCocuk];
            indis = buyukCocuk;
        }
        obekDizisi[indis] = ust;
    }
}
