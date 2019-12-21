# Käyttöohje

Lataa tiedosto [tetris.jar](https://github.com/luxville/ot-harjoitustyo/releases/tag/tetris_vk6/tetris.jar).

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

<code>java -jar tetris.jar</code>

Tämän jälkeen näytölle avautuu aloitusnäkymänä valikko, josta voi valita seuraavat toiminnot.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/valikko.png">

**Jatka peliä** jatkaa käynnissä olevaa peliä. Uusi peli käynnistyy taustalle, kun ohjelma käynnistetään (kuvassa valikon ollessa näkyvissä ensimmäinen palikka näkyy taustalla), joten peliä voi jatkaa kaikissa muissa tilanteissa paitsi päättyneen pelin jälkeen.

**Uusi peli** aloittaa uuden pelin, jossa siis ensimmäisenä taustalla näkyvä palikka vaihtuu joko muuksi tai samaksi. Pelialueen vasemmalla puolella yläreunassa näkyy siihen mennessä saavutetut pisteet, taso ja tuhotut rivit.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/peli.png">

Peli päättyy, kun pelialueen ylimmälle riville tulee palikka, joka ei mahdu enää putoamaan alaspäin. Jos saavutettu tulos oikeuttaa 10 parhaan tuloksen joukkoon, pyydetään käyttäjää kertomaan nimensä, josta tallennetaan parhaiden tulosten listalle 10 ensimmäistä merkkiä.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/listalle.png">

Mikäli nimeä pyydettäessä painaa lopuksi *Cancel*, ei tulos tallennu parhaiden tulosten listalle.

Pelin päätyttyä ilmoitetaan vielä saavutetut pisteet, taso ja tuhottujen rivien määrä.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/gameover.png">

**Parhaat tulokset** näyttää 10 parasta tulosta. Näkymä tuloslistasta päivittyy vain sulkemalla peli ja käynnistämällä se sitten uudelleen. Tulokset päivittyvät kuitenkin oikein.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/top10.png">

**Ohje** kertoo lyhyesti pelin tarkoituksen ja selittää, miten palikoita ohjataan.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/ohje.png">

Käytössä on kaksi tapaa ohjata palikoita, joko näppäimillä *W*, *A*, *S* ja *D* tai nuolinäppäimillä. *Välilyönti* keskeyttää pelin. Palikat voivat liikkua vain vielä vapaana olevalla pelialueella. Näppäintoiminnot ovat seuraavat:

*W tai nuoli ylös*: palikka kääntyy, jos sillä on tilaa kääntyä. Kääntyminen edellyttää, ettei palikka ole liian lähellä pelialueen reunaa tai jo pelialueella olevia palikoita. Palikkaa voi halutessaan kääntää niin monta kertaa kuin vain ehtii. Yksi näppäimenpainallus kääntää palikkaa yhden kerran.

*A tai nuoli vasemmalle*: palikka siirtyy vasemmalle, ellei pelialueen reuna tai jo pelialueella olevat palikat estä siirtymistä. Pitämällä näppäintä pohjassa palikka siirtyy niin paljon vasemmalle kuin pelialueella on vapaata tilaa.

*D tai nuoli oikealle*: palikka siirtyy oikealle, ellei pelialueen reuna tai jo pelialueella olevat palikat estä siirtymistä. Pitämällä näppäintä pohjassa palikka siirtyy niin paljon oikealle kuin pelialueella on vapaata tilaa.

*S tai nuoli alas*: palikan putoaminen nopeutuu niin pitkään kuin näppäimen pitää painettuna.

*Välilyönti*: peli keskeytyy ja näkyviin tulee aloitusvalikko, josta voi valita mitä tekee seuraavaksi. Kesken pelin voi siis halutessaan käydä tutustumassa ohjeisiin tai parhaiden tulosten listaan.

**Lopeta Tetris** sulkee ohjelman välittömästi.
