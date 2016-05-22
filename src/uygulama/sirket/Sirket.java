package uygulama.sirket;

public class Sirket {
    public String Ad;
    public String Adres;
    public String Telefon;
    public String Faks;
    public String Eposta;

    public  Sirket(){

    }

    public Sirket(String ad){
        this.Ad = ad;
    }

    public Sirket(String ad, String adres, String telefon, String faks, String eposta){
        this.Ad = ad;
        this.Adres = adres;
        this.Telefon = telefon;
        this.Faks = faks;
        this.Eposta = eposta;
    }
}
