package uygulama.veriYapilari;

import uygulama.veriYapilari.bagliListe.BagliListeSoyut;
import uygulama.veriYapilari.bagliListe.Dugum;

/**
 * Created by Sefa on 9.05.2016.
 */
public class BagliListe extends BagliListeSoyut {
    @Override
    public void BasaEkle(int deger) {
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
    public void SonaEkle(int deger) {
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
    public void PozisyonaEkle(int pozisyon, int deger) {
        Dugum gecici = new Dugum(deger);

        if (pozisyon > Boyut || pozisyon < 0){
            //Uyarı ver.
        }
        else if (pozisyon == 1)
            BasaEkle(deger);
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
    public void BasiSil() {
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
    public void SonuSil() {

    }

    @Override
    public void PozisyonuSil(int pozisyon) {

    }

    @Override
    public Dugum ElemanGetir(int pozisyon) {
        return null;
    }

    @Override
    public String ElemanlariListele() {
        return null;
    }
}
