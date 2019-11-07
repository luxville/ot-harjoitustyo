package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());      
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(990);
        assertEquals("saldo: 10.0", kortti.toString());      
    }
        
    @Test
    public void saldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.lataaRahaa(990);
        kortti.otaRahaa(350);
        kortti.otaRahaa(250);
        assertEquals("saldo: 4.0", kortti.toString());      
    }
            
    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(350);
        kortti.otaRahaa(250);
        assertEquals("saldo: 0.10", kortti.toString());      
    }
                
    @Test
    public void otaRahaaPalauttaaTrueOikein() {
        assertTrue(kortti.otaRahaa(10));     
    }
                    
    @Test
    public void otaRahaaPalauttaaFalseOikein() {
        assertFalse(kortti.otaRahaa(100));     
    }
    
    @Test
    public void saldoToimiiOikein() {
        kortti.lataaRahaa(990);
        kortti.otaRahaa(350);
        kortti.otaRahaa(250);
        assertEquals(400, kortti.saldo());      
    }
}
