package uygulama.eleman;

import java.util.Date;
import java.util.List;

/**
 * Paket:           uygulama.eleman
 * Oluşturan:       Nuri UZUNOĞLU
 * Oluşturma:       08.05.2016 | 19:31
 */

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
    public void Kayit() {

    }

    // TODO: Sistemdeki bilgilerini güncelleme
    public void Guncelle() {

    }

    // TODO: Sistemden çıkma (bilgilerini silme)
    public void Sil() {

    }

    // TODO: Sistemdeki bir işe başvurma (başvurusu bulunan yere tekrar başvuramamalı)
    public void Basvur() {

    }
}
