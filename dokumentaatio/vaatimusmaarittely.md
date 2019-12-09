# Vaatimusmäärittely

## Sovelluksen tarkoitus

Tarkoituksena on tehdä vielä yksi maailmaa tuskin kovin paljoa järisyttävä toteutus legendaarisen palikkapelistä. Perustoimintona on varsinainen peli ja tallennettava parhaiden tulosten lista, mikäli aika riittää olisi tarkoituksena tarjota mahdollisuus muokata pelikokemusta ainakin vetovoiman osalta. Vetovoimalla tarkoitan tässä yhteydessä sitä, vaikuttaako rivin täyteen saaminen sen yläpuolella olevien palikoiden asemaan muuten kuin poistamalla ainoastaan valmiiksi tulleen rivin. Tämänhetkisessä toteutuksessa ruudut putoavat sarakkeittain niin alas kuin niillä on tilaa aina kun jokin välittömästi niiden alapuolella ollut ruutu poistuu välistä rivin täyttymisen yhteydessä.

Suomenkielisen Wikipedian mukaan *"Tetriksessä on tarkoitus kerätä pisteitä pudottelemalla neljästä laatikosta koostuvia palikoita (tetrominoja, joita on seitsemän erilaista) ja koota niistä vaakasuoria rivejä. Kun rivin saa kokonaiseksi, se poistetaan ja ylempänä olevat rivit putoavat yhden alemmaksi. Täyttämällä kaksi tai kolme riviä kerralla saa enemmän pisteitä. Tämä toimenpide neljälle riville samanaikaisesti on nimeltään tetris."* (Viitattu 08.11.2019.)

## Käyttäjät

Sovelluksesta on tarkoitus saada aikaiseksi versio, jolla pelaaminen onnistuu ja parhaiden tulosten listalle päästessään sinne voi jättää nimensä. Erillisten käyttäjäprofiilien toteuttaminen ei ole tämänkaltaisessa pelissä mielekästä.

## Käyttöliittymäluonnos

Sovellus koostuu neljästä eri näkymästä, joita ovat valikko, pelitila, ohjeet ja parhaat tulokset.

Aloitusnäkymässä on valikko, josta voi aloittaa uuden pelin, katsoa parhaat tulokset (*nappi vie sivulle, jolle on tarkoitus toteuttaa parhaiden tulosten lista*), tutustua peliohjeisiin tai sulkea sovelluksen. Peli voidaan milloin tahansa keskeyttää ja siitä päästä takaisin valikkoon, valikosta pääsee myös jatkamaan kesken olevaa peliä, vaikka kävisikin välillä tutustumassa ohjeisiin tai parhaisiin tuloksiin. Käytännössä aloitussivu on sama kuin pelin keskeytyssivu.

Pelinäkymässä pelataan ja pelin päätyttyä pelaajalta kysytään nimi, jos hänen tuloksensa oikeuttaa parhaiden tulosten listalle. Tämän jälkeen palataan aloitusnäkymään. Peli kerää tiedot pudotetuista riveistä ja kokonaispistemäärästä.

Peliohjenäkymässä kerrotaan Tetriksen säännöt lyhyesti, käytettävät näppäimet ja niiden toiminnot.

## Jatkokehitysideoita

Perustoiminnallisuuksien toteuttamisen jälkeen järjestelmää kehitetään mahdollisesti esimerkiksi seuraavilla lisätoiminnallisuuksilla:

- vetovoiman toteuttaminen siten, että rivin täyteen saaminen pudottaa yläpuolella olevia palikoita vain pudonneiden rivien verran. 
- seuraavana tulevan palikan näyttäminen.
- esimerkiksi toinen leveyden ja korkeuden osalta poikkeava kenttää, jolla on oma tulostaulunsa.
- mahdollisuus aloittaa peli tilanteesta, jossa kentän pohjalla on jo jonkin verran hajanaisesti sijoitettuja palasia, jotka voi poistaa saamalla rivit täyteen tippuvilla palikoilla.

