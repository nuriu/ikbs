package uygulama.sirket;

import uygulama.veriYapilari.obek.Obek;

import java.util.Random;

public class Ilan {
    public String IsTanimi;
    public String ArananOzellikler;
    public Sirket Sirket;
    public int IlanNo;
    public Obek Basvuranlar;

    private Random r;

    public Ilan(){
        Basvuranlar = new Obek();
        r = new Random();
        this.IlanNo = r.nextInt(1000);
    }

    public Ilan(String tanim, String ozellikler, Sirket s){
        Basvuranlar = new Obek();
        r = new Random();
        this.IsTanimi = tanim;
        this.ArananOzellikler = ozellikler;
        this.Sirket = s;
        this.IlanNo = r.nextInt(1000);
    }
}
