package uygulama.veriYapilari.ikiliAramaAgaci;

/**
 * Created by Sefa on 10.05.2016.
 */
public class Dugum {
    public Object veri;
    public Dugum sol;
    public Dugum sag;


    public Dugum() {
    }

    public Dugum(Object veri) {
        this.veri = veri;
        this.sol = null;
        this.sag = null;
    }
}
