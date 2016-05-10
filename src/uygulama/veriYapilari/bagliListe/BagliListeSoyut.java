package uygulama.veriYapilari.bagliListe;

/**
 * Created by Sefa on 9.05.2016.
 */
public abstract class BagliListeSoyut {
    public Dugum Bas;
    public int Boyut = 0;
    public abstract void basaEkle(int deger);
    public abstract void sonaEkle(int deger);
    public abstract void pozisyonaEkle(int pozisyon, int deger);
    public abstract void basiSil();
    public abstract void sonuSil();
    public abstract void pozisyonuSil(int pozisyon);
    public abstract Dugum elemanGetir(int pozisyon);
    public abstract String elemanlariListele();
}
