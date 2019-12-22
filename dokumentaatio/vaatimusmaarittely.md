# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on yksi maailmaa tuskin kovin paljoa järisyttävä toteutus legendaarisen palikkapelistä nimeltä TETRIS. Varsinaisen pelin lisäksi omaa tulostasoaan voi seurata parhaiden tulosten listan avulla.

Suomenkielisen Wikipedian mukaan *"Tetriksessä on tarkoitus kerätä pisteitä pudottelemalla neljästä laatikosta koostuvia palikoita (tetrominoja, joita on seitsemän erilaista) ja koota niistä vaakasuoria rivejä. Kun rivin saa kokonaiseksi, se poistetaan ja ylempänä olevat rivit putoavat yhden alemmaksi. Täyttämällä kaksi tai kolme riviä kerralla saa enemmän pisteitä. Tämä toimenpide neljälle riville samanaikaisesti on nimeltään tetris."* (Viitattu 08.11.2019.)

## Käyttäjät

Sovelluksessa ei ole erillisiä käyttäjätilejä tai -profiileja. Pelaaja voi jättää jälkensä peliin tekemällä suorituksen, joka oikeuttaa paikkaan parhaiden tulosten listalle, jonne mahtuu 10 eniten pisteitä saanutta pelisuoritusta.

## Käyttöliittymä ja toiminnallisuudet

Sovellus koostuu viidestä eri näkymästä, joita ovat aloitusnäkymä, pelitila, pelinpäättymisnäkymä, parhaat tulokset ja ohjeet. Näkymästä toiseen pääsee nappia painamalla. Aloitusnäkymässä välilyönnin painaminen aiheuttaa kesken olevan pelin jatkumisen. Kaikista muista näkymistä pääsee takaisin aloitusnäkymään joko siihen tarkoitettua nappia tai välilyöntiä painamalla. 

### Aloitusnäkymä

Aloitusnäkymässä on valikko, josta voi jatkaa kesken olevaa peliä, aloittaa uuden pelin, katsoa parhaat tulokset, tutustua peliohjeisiin tai sulkea sovelluksen. Peli voidaan milloin tahansa keskeyttää painamalla välilyöntiä ja siitä päästä takaisin aloitusnäkymään, josta pääsee myös jatkamaan kesken olevaa peliä, vaikka kävisikin välillä tutustumassa ohjeisiin tai parhaisiin tuloksiin. Aloitusnäkymä on sama kuin pelin keskeytysnäkymä. Kerran päättynyttä peliä ei voi enää jatkaa.

### Pelitila

Pelinäkymässä pelataan tavoitteena saada mahdollisimman paljon pisteitä. Pisteitä saa järjestämällä palikat niin, että niistä muodostuu täysiä rivejä, jotka sitten tuhoutuvat. Pisteitä saa nopeammin, kun saa kerralla täyteen mahdollisimman monta riviä, myös taso jolla pelaaja on vaikuttaa kustakin rivin tuhoamisesta saatavaan pistemäärään. Peli kerää tiedot kokonaispistemäärästä, saavutetusta tasosta ja pudotetuista riveistä, jotka näkyvät koko pelin ajan pelialueen vasemmalla puolella. Peli päättyy, kun pelialueen yläreunaan ei enää mahdu lisää palikoita.

### Pelinpäättymisnäkymä

Tiedot pisteistä, tasosta ja tuhotuista riveistä näytetään pelaajalle pelin päätyttyä aina. Lisäksi pelaajalta kysytään nimi, jos hänen tuloksensa oikeuttaa parhaiden tulosten listalle.

### Parhaat tulokset

Parhaat tulokset päivittyvät tuloslistalle, mutta tuloslistan päivittäminen näkyville tulevaan muotoon kesken avoimena olevan istunnon osoittautui tällä kertaa ylivoimaiseksi tehtäväksi. Tuloslista esitetään siis sellaisena, joka se on ollut ohjelmaa edellisen kerran käynnistettäessä, mikä myös ilmoitetaan tuloslistan yhteydessä.

### Peliohjeet

Peliohjenäkymässä kerrotaan lyhyesti Tetriksen säännöt, käytettävät näppäimet ja niiden toiminnot.

## Jatkokehitysideoita

Toteutettua Tetris-versiota olisi mahdollista kehittää esimerkiksi seuraavilla lisätoiminnallisuuksilla:

- vetovoiman toteuttaminen siten, että rivin täyteen saaminen pudottaa yläpuolella olevia palikoita vain pudonneiden rivien verran. 
- seuraavana tulevan palikan näyttäminen.
- esimerkiksi toinen leveyden ja korkeuden osalta poikkeava kenttää, jolla on oma tulostaulunsa.
- mahdollisuus aloittaa peli tilanteesta, jossa kentän pohjalla on jo jonkin verran hajanaisesti sijoitettuja palasia, jotka voi poistaa saamalla rivit täyteen tippuvilla palikoilla.
