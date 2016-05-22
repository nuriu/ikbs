package uygulama.sirket;

import java.util.Random;

public class Ilan {
    public String IsTanimi;
    public String ArananOzellikler;
    public Sirket Sirket;
    public int IlanNo;


    public Ilan(){

    }

    public Ilan(String tanim, String ozellikler, Sirket s){
        Random r = new Random();
        this.IsTanimi = tanim;
        this.ArananOzellikler = ozellikler;
        this.Sirket = s;
        this.IlanNo = r.nextInt(1000);
    }
}
