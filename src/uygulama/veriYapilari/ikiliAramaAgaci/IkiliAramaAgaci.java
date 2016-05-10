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

    public int DugumSayisi() {
        return DugumSayisi(kok);
    }

    public int DugumSayisi(Dugum dugum) {
        int sayac = 0;
        if (dugum != null){
            sayac = 1;
            sayac += DugumSayisi(dugum.sol);
            sayac += DugumSayisi(dugum.sag);
        }
        return sayac;
    }

    public int YaprakSayisi() {
        return YaprakSayisi(kok);
    }

    public int YaprakSayisi(Dugum dugum) {
        int sayac = 0;
        if (dugum != null){
            if ((dugum.sol == null) && (dugum.sag == null))
                sayac = 1;
            else
                sayac = sayac + YaprakSayisi(dugum.sol) + YaprakSayisi(dugum.sag);
        }
        return sayac;
    }

    public String DugumleriYazdir() {
        return dugumler;
    }

    private void Ziyaret(Dugum dugum){

    }

    public void PreOrder() {
        dugumler = "";
        PreOrderInt(kok);
    }

    private void PreOrderInt(Dugum dugum) {

    }

    public void InOrder() {

    }

    private void InOrderInt(Dugum dugum) {

    }

    public void PostOrder(){

    }

    private void PostOrder (Dugum dugum){

    }

    public void  Ekle(int deger){

    }

    public Dugum Ara(int anahtar){
        return  null;
    }

    private Dugum AraInt(Dugum dugum, int anahtar){
        return  null;
    }

    public Dugum MinDeger(){
        return null;
    }

    public Dugum MaxDeger(){
        return null;
    }

    private Dugum Successor(Dugum dugum){
        return null;
    }

    public boolean Sil(int deger){
        return true;
    }
}
