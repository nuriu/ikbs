package uygulama.veriYapilari.bagliListe;

import uygulama.veriYapilari.bagliListe.BagliListeSoyut;
import uygulama.veriYapilari.bagliListe.Dugum;

/**
 * Created by Sefa on 9.05.2016.
 */
public class BagliListe extends BagliListeSoyut {
    @Override
    public void basaEkle(int deger) {
        Dugum gecici = new Dugum(deger);

        if(Bas == null)
            Bas = gecici;
        else{
            gecici.Sonraki = Bas;
            Bas = gecici;
        }
        Boyut++;
    }

    @Override
    public void sonaEkle(int deger) {
        Dugum gecici = new Dugum(deger);

        if(Bas == null)
            Bas = gecici;
        else{
            Dugum sayac = Bas;
            while(sayac.Sonraki != null)
                sayac = sayac.Sonraki;
            sayac.Sonraki = gecici;
        }
        Boyut++;
    }

    @Override
    public void pozisyonaEkle(int pozisyon, int deger) {
        Dugum gecici = new Dugum(deger);

        if (pozisyon > Boyut || pozisyon < 0){
            throw new IndexOutOfBoundsException("Hatalı Pozisyon!");
        }
        else if (pozisyon == 1)
            basaEkle(deger);
        else {
            Dugum sayac = Bas;
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
        if (Bas != null){
            Dugum BasSonraki = Bas.Sonraki;
            if(BasSonraki == null)
                Bas = null;
            else
                Bas = BasSonraki;
            Boyut--;
        }
    }

    @Override
    public void sonuSil() {
        Dugum sayac = Bas;
        while (sayac != null){
            if (sayac.Sonraki.Sonraki == null){
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
        if (pozisyon > Boyut || pozisyon < 0){
            //Hata ver
        }
        else if (pozisyon == 1)
            basiSil();
        else{
            Dugum sayac = Bas;
            Dugum gecici = new Dugum();
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
    public Dugum elemanGetir(int pozisyon) {
        Dugum sayac = Bas;
        if (pozisyon > Boyut || pozisyon < 0){
            //Hata ver
        }
        else{
            for (int i = 1; i < pozisyon; i++){
                if (sayac.Sonraki != null)
                    sayac = sayac.Sonraki;
            }
        }
        return  sayac;
    }

    @Override
    public String elemanlariListele() {
        String gecici = "";
        Dugum eleman = Bas;
        while (eleman != null){
            gecici += "-" + eleman.Veri;
            eleman = eleman.Sonraki;
        }
        return  gecici;
    }
}
