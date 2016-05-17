package uygulama.veriYapilari.bagliListe;

// TODO: silme işlemi için arama fonksiyonu gerekli.

public class BagliListe extends BagliListeSoyut {
    @Override
    public void basaEkle(Object deger) {
        bLDugum gecici = new bLDugum(deger);

        if(Bas == null)
            Bas = gecici;
        else {
            gecici.Sonraki = Bas;
            Bas = gecici;
        }
        Boyut++;
    }

    @Override
    public void sonaEkle(Object deger) {
        bLDugum gecici = new bLDugum(deger);

        if(Bas == null)
            Bas = gecici;
        else {
            bLDugum sayac = Bas;
            while(sayac.Sonraki != null)
                sayac = sayac.Sonraki;
            sayac.Sonraki = gecici;
        }
        Boyut++;
    }

    @Override
    public void pozisyonaEkle(int pozisyon, Object deger) {
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
            for (int i = 1; i < pozisyon - 1; i++){
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
    public bLDugum elemanGetir(int pozisyon) {
        bLDugum sayac = Bas;
        if (pozisyon > Boyut || pozisyon < 0) {
            throw new IndexOutOfBoundsException("Hatalı Pozisyon!");
        } else {
            for (int i = 1; i < pozisyon; i++) {
                if (sayac.Sonraki != null)
                    sayac = sayac.Sonraki;
            }
        }
        return sayac;
    }
}
