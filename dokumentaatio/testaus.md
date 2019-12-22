# Testausdokumentti

Ohjelmaa on testattu yksikkötestien avulla JUnitilla sekä ohjelman toimintaa käytännössä kokeilemalla Windows- ja Linux-ympäristöissä.

## Yksikkö- ja integraatiotestaus

Sovellus koostuu kahdesta pakkauksesta, joista sovelluslogiikkaa on testattu ja käyttöliittymä on jätetty kaikenlaisen testauksen ulkopuolelle.

### Sovelluslogiikka

Automatisoidut testit muodostuvat pakkauksen [tetris.domain](https://github.com/luxville/ot-harjoitustyo/tree/master/Tetris/src/main/java/tetris/domain) luokkien metodeja testaavat yksikkö- ja integraatiotestit. Osa testeistä sisältää yksittäisten metodien testausta, kun taas toisissa käsitellään useampien peräkkäisten toimintojen toimintaa. Testiluokkia ovat [BoardTest](https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/src/test/java/tetris/domain/BoardTest.java), [HighScoreTest](https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/src/test/java/tetris/domain/HighScoreTest.java), [PointTest](https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/src/test/java/tetris/domain/PointTest.java) ja [ShapeTest](https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/src/test/java/tetris/domain/ShapeTest.java).

### Testikattavuus

Testikattavuudessa pyrittiin vaadittuun noin 70 prosenttiin. Rivikattavuus on 78% ja haaraumakattavuus 63%.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testikattavuus.png" width="800">

Testaamatta jäivät monimutkaisemmat kokonaisuudet pelin edetessä varsinkin luokan [Board](https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/src/main/java/tetris/domain/Board.java) osalta.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti käyttötesteillä. Käytännössä jotakuinkin kaiken on havaittu toimivan niin kuin peliltä odottaa sopii eikä mitään virheilmoituksia ole havaittu.

### Asennus

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) kuvaamissa tilanteissa Linux- ja Windows-ympäristöissä. Sovellus on toiminut molemmissa ympäristöissä sekä olemassaolevan tuloslistan kanssa että ilman sitä.

### Toiminnallisuudet

Kaikki [määrittelydokumentissa](https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md#käyttöliittymä-ja-toiminnallisuudet) ja käyttöohjeessa luetellut toiminnallisuudet on kokeiltu myös käytännössä ja havaittu toimiviksi. Ainoa syötekenttä on mahdollisuus nimen antamiseen parhaiden tulosten listalle päästäessä, ja se toimii oikein sekä tyhjällä syötteellä että ainakin vähän liian pitkillä syötteillä.

## Sovellukseen jääneet laatuongelmat

Sovelluksessa on havaittuja puutteita seuraavissa toiminnallisuuksissa:

- Parhaiden tulosten listan päivittäminen käyttäjän näkyviin välittömästi pelin jälkeen. Tuloslistanäkymä päivittyy ainoastaan sovelluksen käynnistyksen yhteydessä.
- Useamman rivin tuhoaminen kerralla siten, että kaikki tuhotut rivit eivät ole päällekkäin. Tällöin pisteitä saa vain päällekkäisten tuhottujen rivien mukaan.
- Joissakin tilanteissa pelissä käytössä olevan painovoiman toiminnallisuudesta johtuen alas syntyvä rivi ei tuhoudukaan vielä täyteen tultuaan. Tällöin rivi tuhoutuu seuraavan palikan päästyä alas asti, vaikka sillä ei saisikaan yhtään riviä valmiiksi.
