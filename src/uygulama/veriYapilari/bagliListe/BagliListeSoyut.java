package uygulama.veriYapilari.bagliListe;

/**
 * Created by Sefa on 9.05.2016.
 */
public abstract class BagliListeSoyut {
    public Dugum Bas;
    public int Boyut = 0;
    public abstract void BasaEkle(int deger);
    public abstract void SonaEkle(int deger);
    public abstract void PozisyonaEkle(int pozisyon, int deger);
    public abstract void BasiSil();
    public abstract void SonuSil();
    public abstract void PozisyonuSil(int pozisyon);
    public abstract Dugum ElemanGetir(int pozisyon);
    public abstract String ElemanlariListele();
}
