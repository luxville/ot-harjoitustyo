# Ohjelmistotekniikka, harjoitustyö

## Tetris

Sovelluksessa käyttäjän tarkoituksena on järjestellä ruudun ylälaidasta putoavia erimuotoisia neljän ruudun kokoisia palikoita siten, että niistä muodostuu täysiä rivejä. Kustakin täydestä rivistä saa pisteen ja rivi katoaa tehden tilaa uusille riveille ja palikoille. Tavoitteena on saada mahdollisimman monta riviä täyteen ja katoamaan.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Julkaisut

[Viikko 5](https://github.com/luxville/ot-harjoitustyo/releases/tag/tetris_vk5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla 

<code>mvn test</code>

Testikattavuusraportti luodaan komennolla 

<code>mvn jacoco:report</code>

ja sitä voi tarkastella selaimella avaamalla tiedosto *target/site/jacoco/index.html*

### Suoritettavan jar-tiedoston generointi

Suoritettava jar-tiedosto *Tetris-1.0-SNAPSHOT.jar* luodaan kansioon *target* komennolla

<code>mvn package</code>

### Checkstyle

Tiedoston [checkstyle.xml](https://github.com/luxville/ot-harjoitustyo/blob/master/Tetris/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla 

<code>mvn jxr:jxr checkstyle:checkstyle</code>

Mahdollisia virheilmoituksia voi tarkastella selaimella avaamalla tiedosto*target/site/checkstyle.html*

## Missä mennään

Ohjelman suorittaminen onnistuu Netbeansissa sekä myös komentoriviltä komennolla <code>mvn compile exec:java -Dexec.mainClass=tetrisgame.Main</code>. Tällä hetkellä peli on pelattavassa kunnossa, mutta parhaiden tulosten listaa ei ole vielä toteutettu. Viime viikolla vaivannut ongelma, jossa palikat liikkuivat vasemmalta oikealle ja logiikka tuntui muutenkin olevan hukassa, on ratkaistu ja Tetristä pääsee harjoittelemaan omaksi ilokseen, kun parhaat tulokset eivät vielä tallennu minnekään jälkipolvia varten. Testitkin on lopulta saatu alkuun, tavoitetahdissa ei kuitenkaan vieläkään olla.

