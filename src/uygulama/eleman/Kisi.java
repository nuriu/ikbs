package uygulama.eleman;

import java.util.Date;
import java.util.List;

public class Kisi {
    public String Ad;
    public String Adres;
    public Long Telefon;
    public String Eposta;
    public String Uyruk;
    public String DogumYeri;
    public Date DogumTarihi;
    public String MedeniDurum;
    public String YabanciDil;
    public String IlgiAlanlari;
    public List<Kisi> Referanslar;

    public Kisi(String ad) {
        Ad = ad;
    }

    // TODO: Sisteme kayıt (kendi kişisel bilgilerini girme)
    public void Kayit(String ad, String adres, Long telefon, String eposta,
                      String uyruk, String dogumYeri, Date dogumTarihi,
                      String medeniDurum, String yabanciDil, String ilgiAlanlari,
                      List<Kisi> referanslar) {
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

    // TODO: Sistemdeki bilgilerini güncelleme
    public void Guncelle(String ad, String adres, Long telefon, String eposta,
                         String uyruk, String dogumYeri, Date dogumTarihi,
                         String medeniDurum, String yabanciDil, String ilgiAlanlari,
                         List<Kisi> referanslar) {
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

    // TODO: Sistemden çıkma (bilgilerini silme)
    public void Sil() {

    }

    // TODO: Sistemdeki bir işe başvurma (başvurusu bulunan tekrar başvuramamalı)
    public void Basvur() {

    }
}
