package uygulama.eleman;

public class Kisi {
    public String Ad;
    public String Adres;
    public String Telefon;
    public String Eposta;
    public String Uyruk;
    public String DogumYeri;
    public String DogumTarihi;
    public String MedeniDurum;
    public String YabanciDil;
    public String IlgiAlanlari;
    public String Referanslar;

    public Kisi() {
    }

    public Kisi(String ad) {
        Ad = ad;
    }

    public Kisi(String ad, String adres, String telefon, String eposta,
                String uyruk, String dogumYeri, String dogumTarihi,
                String medeniDurum, String yabanciDil, String ilgiAlanlari,
                String referanslar) {
        this.Ad = ad;
        this.Adres = adres;
        this.Telefon = telefon;
        this.Eposta = eposta;
        this.Uyruk = uyruk;
        this.DogumYeri = dogumYeri;
        this.DogumTarihi = dogumTarihi;
        this.MedeniDurum = medeniDurum;
        this.YabanciDil = yabanciDil;
        this.IlgiAlanlari = ilgiAlanlari;
        this.Referanslar = referanslar;
    }

    public String bilgileriGetir() {
        return (Ad + " | " + Adres + " | " + Telefon + " | " + Eposta + " | " +
                Uyruk + " | " + DogumYeri + " | " + DogumTarihi + " | " +
                MedeniDurum + " | " + YabanciDil + " | " + IlgiAlanlari + " | " + Referanslar);
    }
}
