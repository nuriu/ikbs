package uygulama.veriYapilari.bagliListe;

import uygulama.eleman.Deneyim;
import uygulama.eleman.Egitim;
import uygulama.eleman.EkBilgi;

public class BagliListe extends BagliListeSoyut {
    @Override
    public void basaEkle(EkBilgi deger) {
        bLDugum gecici = new bLDugum(deger);
        
        gecici.Sonraki = Bas;
        Bas = gecici;
   
        Boyut++;
    }

    @Override
    public void sonaEkle(EkBilgi deger) {
        bLDugum gecici = new bLDugum(deger);

        if (Bas == null)
            Bas = gecici;
        else {
            bLDugum sayac = Bas;
            while (sayac.Sonraki != null)
                sayac = sayac.Sonraki;
            sayac.Sonraki = gecici;
        }
        Boyut++;
    }

    @Override
    public void pozisyonaEkle(int pozisyon, EkBilgi deger) {
        bLDugum gecici = new bLDugum(deger);

        if (pozisyon > Boyut || pozisyon < 0) {
            throw new IndexOutOfBoundsException("Hatalı Pozisyon!");
        }
        else if (pozisyon == 1)
            basaEkle(deger);
        else {
            bLDugum sayac = Bas;
            for (int i = 1; i < pozisyon - 1; i++) {
                if (sayac.Sonraki != null)
                    sayac = sayac.Sonraki;
            }
            gecici.Sonraki = sayac.Sonraki;
            sayac.Sonraki = gecici;
        }
        Boyut++;
    }

    @Override
    public void basiSil() {
        if (Bas != null) {
            bLDugum BasSonraki = Bas.Sonraki;
            if(BasSonraki == null)
                Bas = null;
            else
                Bas = BasSonraki;
            Boyut--;
        }
    }

    @Override
    public void sonuSil() {
        bLDugum sayac = Bas;
        while (sayac != null) {
            if (sayac.Sonraki.Sonraki == null) {
                sayac.Sonraki = null;
                break;
            }
            else
                sayac = sayac.Sonraki;
        }
        Boyut--;
    }

    @Override
    public void pozisyonuSil(int pozisyon) {
        if (pozisyon > Boyut || pozisyon < 0) {
            throw new IndexOutOfBoundsException("Hatalı Pozisyon!");
        }
        else if (pozisyon == 1)
            basiSil();
        else {
            bLDugum sayac = Bas;
            bLDugum gecici = new bLDugum();
            for (int i = 1; i < pozisyon - 1; i++) {
                if (sayac.Sonraki != null)
                    sayac = sayac.Sonraki;
            }
            gecici = sayac;
            sayac = sayac.Sonraki;
            gecici.Sonraki = sayac.Sonraki;
            sayac = null;
            Boyut--;
        }
    }

    @Override
    public String elemanGetir(int pozisyon) {
        bLDugum sayac = Bas;
        if (pozisyon > Boyut || pozisyon < 0) {
            throw new IndexOutOfBoundsException("Hatalı Pozisyon!");
        } else {
            for (int i = 1; i < pozisyon; i++) {
                if (sayac.Sonraki != null)
                    sayac = sayac.Sonraki;
            }
        }

        if (sayac.Veri instanceof Deneyim)
            return ((Deneyim) sayac.Veri).Ad + " - " + ((Deneyim) sayac.Veri).Adres;
        else if (sayac.Veri instanceof Egitim)
            return ((Egitim) sayac.Veri).Bitis + " - " + ((Egitim) sayac.Veri).Ad + " - " + ((Egitim) sayac.Veri).Bolum + " - " + ((Egitim) sayac.Veri).NotOrtalamasi;

        return null;
    }

    public String listele() {
        String sonuc = "";
        if (Bas != null) {
            for (int i = Boyut; i > 0; i--) {
                sonuc += elemanGetir(i) + "\n";
            }
        }
        return sonuc;
    }

    public boolean notOrtalamasi() {
        bLDugum sayac = Bas;
        if (sayac != null) {
            for (int i = Boyut; i > 0; i--) {
                if (sayac.Veri instanceof Egitim) {
                    if (((Egitim) sayac.Veri).NotOrtalamasi >= 3.0) {
                        return true;
                    } else return false;
                }
                sayac = sayac.Sonraki;
            }
        }
        return false;
    }
}
