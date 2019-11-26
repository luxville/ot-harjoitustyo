# Vaatimusmäärittely

## Sovelluksen tarkoitus

Tarkoituksena on tehdä vielä yksi maailmaa tuskin kovin paljoa järisyttävä toteutus legendaarisen palikkapelistä. Perustoimintona on varsinainen peli, mikäli aika riittää olisi tarkoituksena tarjota kirjautuneelle käyttäjälle myös tilastoja omasta pelihistoriastaan.

Suomenkielisen Wikipedian mukaan *"Tetriksessä on tarkoitus kerätä pisteitä pudottelemalla neljästä laatikosta koostuvia palikoita (tetrominoja, joita on seitsemän erilaista) ja koota niistä vaakasuoria rivejä. Kun rivin saa kokonaiseksi, se poistetaan ja ylempänä olevat rivit putoavat yhden alemmaksi. Täyttämällä kaksi tai kolme riviä kerralla saa enemmän pisteitä. Tämä toimenpide neljälle riville samanaikaisesti on nimeltään tetris."* (Viitattu 08.11.2019.)

## Käyttäjät

Aluksi sovellus on tarkoitus saada toimimaan ilman käyttäjäksi kirjautumista (*tehty*), jolloin pelaaminen onnistuu ja parhaiden tulosten listalle päästessään sinne voi jättää nimensä. Myöhemmässä vaiheessa mahdollisesti käyttäjät voivat kirjautua sovellukseen ja nähdä jotain tilastoja omasta pelaamisestaan.

## Käyttöliittymäluonnos

Sovellus koostuu kolmesta eri näkymästä.

Aloitusnäkymässä on valikko, josta voi aloittaa uuden pelin, tutustua peliohjeisiin tai sulkea sovelluksen. Lisäksi aloitussivulla näkyy tuloslista parhaista tuloksista.

Pelinäkymässä pelataan (*tehty*) ja pelin päätyttyä pelaajalta kysytään nimi, jos hänen tuloksensa oikeuttaa parhaiden tulosten listalle. Tämän jälkeen palataan aloitusnäkymään.

Peliohjenäkymässä kerrotaan Tetriksen säännöt lyhyesti, käytettävät näppäimet ja niiden toiminnot.

## Jatkokehitysideoita

Perustoiminnallisuuksien toteuttamisen jälkeen järjestelmää kehitetään mahdollisesti esimerkiksi seuraavilla lisätoiminnallisuuksilla:

- käyttäjätunnuksen luominen ja kirjautumistoiminnallisuus
- kirjautuneena käyttäjänä enemmän tilastoja, kuten omien parhaiden tulosten lista ja kaikki pudotetut rivit yhteensä
- esimerkiksi toinen leveyden ja korkeuden osalta poikkeava kenttää, jolla on oma tulostaulunsa.
- mahdollisuus aloittaa peli tilanteesta, jossa kentän pohjalla on jo jonkin verran hajanaisesti sijoitettuja palasia, jotka voi poistaa saamalla rivit täyteen tippuvilla palikoilla.

