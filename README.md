# Ohjelmistotekniikka, harjoitustyö

## Tetris

Sovelluksessa käyttäjän tarkoituksena on järjestellä ruudun ylälaidasta putoavia erimuotoisia neljän ruudun kokoisia palikoita siten, että niistä muodostuu täysiä rivejä. Kustakin täydestä rivistä saa pisteen ja rivi katoaa tehden tilaa uusille riveille ja palikoille. Tavoitteena on saada mahdollisimman monta riviä täyteen ja katoamaan.

## Dokumentaatio

[Käyttöohje](https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Julkaisut

[Viikko 5](https://github.com/luxville/ot-harjoitustyo/releases/tag/tetris_vk5)

[Viikko 6](https://github.com/luxville/ot-harjoitustyo/releases/tag/tetris_vk6)

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

Mahdollisia virheilmoituksia voi tarkastella selaimella avaamalla tiedosto *target/site/checkstyle.html*

### JavaDoc

JavaDoc generoidaan komennolla

<code>mvn javadoc:javadoc</code>

JavaDocia voi tarkastella avaamalla selaimella tiedosto *target/site/apidocs/index.html*

## Missä mennään

Tällä hetkellä peli on pelattavassa kunnossa. Parhaiden tulosten lista päivittyy vasta kun pelin sammuttaa ja viimeisimmät tulokset ovat siis nähtävillä vasta pelin uudelleenkäynnistyksen jälkeen.

