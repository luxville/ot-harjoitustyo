
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate kassa;
    int edulliset;
    int maukkaat;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        edulliset = 0;
        maukkaat= 0;
        kortti = new Maksukortti(200);
    }

    @Test
    public void kassassaRahaaOikeinAluksi() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisiaLounaitaMyytyOikeinAluksi() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
        
    @Test
    public void maukkaitaLounaitaMyytyOikeinAluksi() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassassaOikeaRahamaaraKateisostonJalkeenEdullisesti() {
        kassa.syoEdullisesti(200);
        kassa.syoEdullisesti(300);
        kassa.syoEdullisesti(400);
        assertEquals(100480, kassa.kassassaRahaa());
    }
        
    @Test
    public void kassassaOikeaRahamaaraKateisostonJalkeenMaukkaasti() {
        kassa.syoMaukkaasti(200);
        kassa.syoMaukkaasti(300);
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }
     
            
    @Test
    public void kassassaOikeaRahamaaraKateisostonJalkeenLiianVahanRahaa() {
        kassa.syoMaukkaasti(200);
        kassa.syoMaukkaasti(300);
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void palautaVaihtorahaOikeinKateisostonJalkeenEdullisesti() {
        assertEquals(160, kassa.syoEdullisesti(400));
    }
    
    @Test
    public void palautaVaihtorahaOikeinKateisostonJalkeenEdullisestiTasaraha() {
        assertEquals(0, kassa.syoEdullisesti(240));
    }
        
    @Test
    public void palautaVaihtorahaOikeinKateisostonJalkeenEdullisestiLiianVahan() {
        assertEquals(190, kassa.syoEdullisesti(190));
    }
            
    @Test
    public void palautaVaihtorahaOikeinKateisostonJalkeenMaukkaasti() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }
    
    @Test
    public void palautaVaihtorahaOikeinKateisostonJalkeenMaukkaastiTasaraha() {
        assertEquals(0, kassa.syoMaukkaasti(400));
    }
        
    @Test
    public void palautaVaihtorahaOikeinKateisostonJalkeenMaukkaastiLiianVahan() {
        assertEquals(390, kassa.syoMaukkaasti(390));
    }
    
    @Test
    public void myytyjenLounaidenMaaraKasvaaOikeinEdullisesti() {
        kassa.syoMaukkaasti(200);
        kassa.syoMaukkaasti(300);
        kassa.syoMaukkaasti(400);
        kassa.syoEdullisesti(200);
        kassa.syoEdullisesti(300);
        kassa.syoEdullisesti(400);
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
        
    @Test
    public void myytyjenLounaidenMaaraKasvaaOikeinMaukkaasti() {
        kassa.syoMaukkaasti(200);
        kassa.syoMaukkaasti(300);
        kassa.syoMaukkaasti(400);
        kassa.syoEdullisesti(200);
        kassa.syoEdullisesti(300);
        kassa.syoEdullisesti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
        
    @Test
    public void myytyjenLounaidenMaaraKasvaaOikeinEdullisestiLiianVahanRahaa() {
        kassa.syoMaukkaasti(200);
        kassa.syoMaukkaasti(300);
        kassa.syoMaukkaasti(400);
        kassa.syoEdullisesti(200);
        kassa.syoEdullisesti(100);
        kassa.syoEdullisesti(40);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
        
    @Test
    public void myytyjenLounaidenMaaraKasvaaOikeinMaukkaastiLiianVahanRahaa() {
        kassa.syoMaukkaasti(200);
        kassa.syoMaukkaasti(300);
        kassa.syoMaukkaasti(100);
        kassa.syoEdullisesti(200);
        kassa.syoEdullisesti(300);
        kassa.syoEdullisesti(400);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaTarpeeksiRahaaVeloitaEdullisesti() {
        kortti.lataaRahaa(200);
        assertTrue(kassa.syoEdullisesti(kortti));
    }
        
    @Test
    public void kortillaTarpeeksiRahaaVeloitaMaukkaasti() {
        kortti.lataaRahaa(200);
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    
        @Test
    public void kortillaTarpeeksiRahaaVeloitaEdullisestiMyytyjenMaaraKasvaaOikein() {
        kortti.lataaRahaa(2000);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(5, kassa.edullisiaLounaitaMyyty());
    }
        
    @Test
    public void kortillaTarpeeksiRahaaVeloitaMaukkaastiMyytyjenMaaraKasvaaOikein() {
        kortti.lataaRahaa(2000);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(3, kassa.maukkaitaLounaitaMyyty());
    }
             
    @Test
    public void kortillaEiTarpeeksiRahaaKortinRahamaaraEiMuutuEdullisesti() {
        kassa.syoEdullisesti(kortti);
        assertEquals(200, kortti.saldo());
    }
        
    @Test
    public void kortillaEiTarpeeksiRahaaKortinRahamaaraEiMuutuMaukkaasti() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(200, kortti.saldo());
    }   
    @Test
    public void kortillaEiTarpeeksiRahaaVeloitaEdullisesti() {
        assertFalse(kassa.syoEdullisesti(kortti));
    }
        
    @Test
    public void kortillaEiTarpeeksiRahaaVeloitaMaukkaasti() {
        assertFalse(kassa.syoMaukkaasti(kortti));
    }
    
        @Test
    public void kortillaEiTarpeeksiRahaaVeloitaEdullisestiMyytyjenMaaraEiKasva() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
        
    @Test
    public void kortillaEiTarpeeksiRahaaVeloitaMaukkaastiMyytyjenMaaraEiKasva() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaMaksettaessa() {
        kortti.lataaRahaa(30000);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortilleKassanRahamaaraMuuttuu() {
        kassa.lataaRahaaKortille(kortti, 12000);
        assertEquals(112000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaNegatiivinenMaaraRahaaKortilleKassanRahamaaraEiMuutu() {
        kassa.lataaRahaaKortille(kortti, -12000);
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
