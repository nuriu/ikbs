package uygulama.veriYapilari.bagliListe;

public abstract class BagliListeSoyut {
    public bLDugum Bas;
    public int Boyut = 0;

    public abstract void basaEkle(Object deger);

    public abstract void sonaEkle(Object deger);

    public abstract void pozisyonaEkle(int pozisyon, Object deger);
    public abstract void basiSil();
    public abstract void sonuSil();
    public abstract void pozisyonuSil(int pozisyon);

    public abstract bLDugum elemanGetir(int pozisyon);
}
