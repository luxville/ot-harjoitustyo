# Arkkitehtuurikuvaus

## Rakenne

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/luokka_pakkauskaavio.jpg">

Ohjelma on jaettu kolmeen pakkaustasoon, joista *tetris.ui* sisältää JavaFX:llä toteutetun käyttöliittymän, *tetris.domain* sovelluslogiikan ja vielä toteuttamaton (ei näy vielä myöskään luokka-/pakkauskaaviossa) *tetris.dao* pysyväistallennuksen koodin.

## Päätoiminnallisuuksia

### Uuden pelin aloittaminen

Valikosta uuden pelin valitsemisen jälkeen sovellus aloittaa toimintansa seuraavasti:

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio_uusi_peli.jpg">

Käyttöliittymä pyytää itseään aloittamaan uuden pelin, hakee pelilaudalta tiedon palikoiden putoamisnopeudesta, piirtää pelilaudan (tämän metodin esittäminen kokonaisuudessaan tarkasti sekvenssikaaviona olisi ollut minulle mahdoton tehtävä käytettävissä olevassa ajassa) sekä aloittaa palikoiden pudottamisen ja jää sen jälkeen odottamaan käyttäjältä ohjeita, mitä kulloinkin putoamassa olevalle palikalle tehdään.
