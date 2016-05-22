package uygulama.sirket;

public class Sirket {
    public String Ad;
    public String Adres;
    public long Telefon;
    public long Faks;
    public String Eposta;

    public  Sirket(){

    }

    public Sirket(String ad){
        this.Ad = ad;
    }

    public Sirket(String ad, String adres, long telefon, long faks, String eposta){
        this.Ad = ad;
        this.Adres = adres;
        this.Telefon = telefon;
        this.Faks = faks;
        this.Eposta = eposta;
    }

    // TODO: Sisteme kayıt (işyeri bilgilerini ekleme)
    public void Kayit() {

    }

    // TODO: Sistemdeki bilgilerini günleme
    public void Guncelleme() {

    }

    // TODO: Yeni bir iş ilanı verme
    public void IlanVer() {

    }

    // TODO: İşe başvuran tüm adayların bilgilerini listeleme
    public void BasvurulariListele() {

    }

    // TODO: En uygun adayı işe alma (Bu kişi Heap’ten çekilecektir)
    public void EnUygunAdayiIseAl() {

    }
}
