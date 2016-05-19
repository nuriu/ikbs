package uygulama.veriYapilari.bagliListe;

import uygulama.eleman.EkBilgi;

public abstract class BagliListeSoyut {
    public bLDugum Bas;
    public int Boyut = 0;

    public abstract void basaEkle(EkBilgi deger);

    public abstract void sonaEkle(EkBilgi deger);

    public abstract void pozisyonaEkle(int pozisyon, EkBilgi deger);
    public abstract void basiSil();
    public abstract void sonuSil();
    public abstract void pozisyonuSil(int pozisyon);

    public abstract String elemanGetir(int pozisyon);
}
