package uygulama.veriYapilari.obek;

import uygulama.eleman.Kisi;

import java.util.Random;

public class oDugum {
    public Kisi Kisi;
    public double Uygunluk;

    private Random r;
    public oDugum(Kisi kisi) {
        r = new Random();
        this.Kisi = kisi;
        Uygunluk = r.nextDouble() * 10;
    }
    public  oDugum(){}
}
