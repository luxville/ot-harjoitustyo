# Arkkitehtuurikuvaus

## Rakenne

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkaukset.png" width="160">

Ohjelma on jaettu kolmeen pakkaustasoon, joista *tetrisgame* hoitaa ainoastaan ohjelman käynnistämisen, *tetris.ui* sisältää JavaFX:llä toteutetun käyttöliittymän, joka kutsuu pakkauksessa *tetris.domain* sijaitsevaa sovelluslogiikkaa, joka huolehtii ohjelmassa tarvittavista toiminnallisuuksista.

## Käyttöliittymä

Käyttöliittymässä on viisi erillistä näkymää
- aloitusnäkymä
- pelitila
- pelinpäättymisnäkymä
- parhaat tulokset ja
- ohjeet.

Näkymät on toteutettu [BorderPane](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/BorderPane.html)-olioilla (aloitusnäkymää ja pelitila) sekä [VBox](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/VBox.html)-olioilla, jotka on sijoitettu yksi kerrallaan pelitilan päälle [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-olioon, joka puolestaan on sovelluksen [stagessa](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html). Ohjelmallisesti käyttöliittymä on toteutettu luokassa [tetris.ui.TetrisGame](https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/src/main/java/tetris/ui/TetrisGame.java).

Käyttöliittymä ja sovelluslogiikka on pyritty eristämään täysin toisistaan omiin pakkauksiinsa ja toiminnot toteutetaan käyttöliittymän esittämien sovelluslogiikkaa ohjaavien metodikutsujen avulla. Metodeja kutsutaan kaikista sovelluslogiikan luokista.

Pääasiallinen toiminnallisuus sovelluksessa on pelitilan ylläpitäminen ja sovelluslogiikan toteuttaminen reaaliaikaisesti. Muissa näkymissä lähinnä valitaan, mitä tehdään tai luetaan seuraavaksi. Pelitilaa päivitetään jatkuvasti kutsumalla sovelluksen metodia [paint](https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/src/main/java/tetris/ui/TetrisGame.java#86), joka yhteistyössä metodin [createTransition]((https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/src/main/java/tetris/ui/TetrisGame.java#194) kanssa ylläpitää pelinäkymää niin kauan kuin peli jatkuu.

## Sovelluslogiikka

Sovelluksessa pelivälineenä ovat palikat, joilla on muoto eli [Shape](https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/src/main/java/tetris/domain/Shape.java)-oliot, jotka muodostuvat ruuduista eli [Point](https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/src/main/java/tetris/domain/Point.java)-olioista. Kussakin palikassa on neljä ruutua. Peliä pelataan pelialueella, jonka muodostaa [Board](https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/src/main/java/tetris/domain/Board.java)-olio. Alla olevassa luokkakaaviossa on esitetty luokan *Board* tärkeimmät muuttujat ja luokkien *Shape* ja *Point* kaikki muuttujat.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/luokat.png" width="480">

Toiminnallisia kokonaisuuksia esiintyy monella tasolla. Luokka *Point* käsittelee yksittäisiä ruutuja, jotka kuitenkin liittyvät aina toisiin ruutuihin palikoissa, joita käsitellään luokassa *Shape* tai jo pelialueella ennestään oleviin ruutuhin, joita käsitellään luokassa *Board*. *Point*-luokan olioita voidaan liikuttaa tai niiden sijainti tai tyyppi selvittää esimerkiksi metodeilla
- void modX(int mod)
- void modY(int mod)
- int getX()
- int getType()

*Shape*-luokan metodeja ovat käytetään neljästä *Point*-oliosta muodostuvien kokonaisuuksien luomiseen, kääntämiseen ja siirtämiseen sekä *Point*-olioiden muuttuneiden sijaintien ylläpitoon kääntämisen jälkeen. Näitä ovat esimerkiksi
- void createL()
- void rotateTypeI()
- void moveLeft()
- List<Point> getRotatedPoints()
  
*Board*-luokassa luodaan pelialue ja hallinnoidaan siinä tapahtuvia toimintoja. *Board*-olio ylläpitää muunmuassa tietoa pelin päättymisestä, pelin kannalta olennaisista tiedoista pisteisiin, riveihin ja tasoon liittyen sekä pelialueella olevista *Point*-olioista ja niiden sijainneista. Näitä käsitteleviä metodeja ovat esimerkiksi
- boolean closeTotopBorder()
- int calculateCurrentScore(int num)
- void updateSpeed()
- List<Point> getPoints()
- boolean hasPointsDown()
- boolean canRotate()
  
Luokan *Board* ja muun sovelluslogiikan suhdetta kuvaa seuraava luokka/pakkauskaavio:
  
<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/luokka_pakkauskaavio.png" width="480">
  
## Tietojen pysyväistallennus

Sovellus tallentaa tietoa ainoastaan [HighScore](https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/src/main/java/tetris/domain/HighScore.java)-olioina tiedostoon, jossa säilytetään tiedot parhaista tuloksista. 

### Tiedosto

Tiedosto luodaan sovelluksen käynnistyksen yhteydessä, mikäli sellaista ei löydy hakemistosta, jossa ohjelma suoritetaan. Tallennuskohteeksi on määritelty *HighScores.dat*-niminen tiedosto. *HighScore*-olio sisältää tiedon saavutetusta tasosta, tuhotuista riveistä, saaduista pisteistä ja ilmoitetusta nimestä. Tietoa ei ole tallennettu selkokielisenä tiedostoon eikä sen tarkastelu ilman yhteensopivaa sovellusta ole käytännössä kovin mielekästä. Tiedoston sisältö puretaan tuloslistaksi sovelluksessa ja uudet parhaat tulokset tallennetaan tarvittaessa tiedostoon *HighScore*-olioina.

## Päätoiminnallisuuksia

### Uuden pelin aloittaminen

Valikosta uuden pelin valitsemisen jälkeen sovellus aloittaa toimintansa seuraavasti:

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio1.png" width="480">

Käyttöliittymä pyytää itseään aloittamaan uuden pelin, hakee pelilaudalta tiedon palikoiden putoamisnopeudesta, piirtää pelilaudan sekä aloittaa palikoiden pudottamisen ja jää sen jälkeen odottamaan käyttäjältä ohjeita, mitä kulloinkin putoamassa olevalle palikalle tehdään.

### Muut toiminnallisuudet

Ohjelmassa on paljon toiminnallisuuksia, joita olisi ollut hyvä avata enemmän sekvenssikaavioiden avulla. Todellisuus iski kuitenkin väliin ja ne jäivät toteuttamatta. Yleisenä periaatteena kuitenkin on, että ohjelman suoritus alkaa ja ajan kuluessa palikat putoilevat alaspäin odottaen käyttäjältä jonkinlaisia ohjeita, mitä palikoille tulisi tehdä. Mikäli rivi tulee täyteen, kutsutaan metodia, joka laskee kuinka moni rivi tulikaan kerralla täyteen ja antaa sen perusteella pisteitä sekä poistaa täyteen tulleet rivit pelialueelta. Kun toimenpiteet on saatu valmiiksi, jää ohjelma jälleen odottamaan käyttäjän ohjeita.

Yksinkertaisempia tapauksia ovat näkymästä toiseen siirtymiset, joissa käytännössä kaikki toiminnallisuus on luotu valmiiksi jo ohjelman käynnistyksen yhteydessä.

## Ohjelman rakenteeseen jääneet heikkoudet

### Käyttöliittymä

Käyttöliittymän toteutuksessa on mietitty vain vähän laadukasta metodirakennetta, joten se muodostuu monista pitkistä metodeista ja on muutenkin yhdessä luokassa toteutettuna melko kömpelö kehitettäväksi. 

Näkymien toteutus samanlaisissa rakenteissa olisi helpottanut niiden käsittelyä.

### Sovelluslogiikka

Tiedon pysyväistallennus olisi kannattanut toteuttaa omassa pakkauksessaan, nyt se on hieman irrallinen osa kokonaisuutta ja tekee *HighScore*-luokasta sekavamman.

Luokka *Board* on turhan pitkä ja jaettu osittain teennäisesti lyhyempiin metodeihin. Paremmalla suunnittelulla tämä olisi hyvä toteuttaa useampaan luokkaan jaettuna.
