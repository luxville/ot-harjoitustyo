# Käyttöohje

Lataa tiedosto [tetris.jar](https://github.com/luxville/ot-harjoitustyo/releases/tag/tetris_vk6/tetris.jar).

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

<code>java -jar tetris.jar</code>

Tämän jälkeen näytölle avautuu aloitusnäkymänä valikko, josta voi valita seuraavat toiminnot.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/valikko.png">

**Jatka peliä** jatkaa käynnissä olevaa peliä. Käytännössä vielä nykyisellä ohjelmaversiolla uuttakin (kuvassa valikon ollessa näkyvissä ensimmäinen palikka näkyy taustalla) tai jo päättynyttä peliä voi jatkaa. Riittävän hyvällä tuloksella voi niin halutessaan siis täyttää tuloslistan kaikki saavutettua tulosta huonommat sijat.

**Uusi peli** aloittaa uuden pelin, jossa siis ensimmäisenä taustalla näkyvä palikka vaihtuu joko muuksi tai samaksi. Pelialueen vasemmalla puolella yläreunassa näkyy pisteet, taso ja tuhotut rivit siihen mennessä.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/peli.png">

Peli päättyy, kun pelialueen ylimmälle riville tulee palikka, joka ei mahdu enää putoamaan alaspäin. Jos saavutettu tulos oikeuttaa 10 parhaan tuloksen joukkoon, pyydetään käyttäjää kertomaan nimensä, josta tallennetaan parhaiden tulosten listalle 10 ensimmäistä merkkiä.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/listalle.png">

Mikäli nimeä pyydettäessä painaa lopuksi *Cancel*, ei tulos tallennu parhaiden tulosten listalle.

Pelin päätyttyä ilmoitetaan vielä saavutetut pisteet, taso ja tuhottujen rivien määrä.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/gameover.png">

**Parhaat tulokset** näyttää 10 parasta tulosta. Tällä hetkellä näkyviin tuleva tuloslista päivittyy vain sulkemalla peli ja käynnistämällä se sitten uudelleen.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/top10.png">

**Ohje** kertoo lyhyesti pelin tarkoituksen ja selittää, miten palikoita ohjataan.

<img src="https://github.com/luxville/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/ohje.png">

Käytössä on kaksi tapaa ohjata palikoita, joko näppäimillä *W*, *A*, *S* ja *D* tai nuolinäppäimillä. *Välilyönti* keskeyttää pelin. Palikat voivat liikkua vain vielä vapaana olevalla pelialueella. Näppäintoiminnot ovat seuraavat:

*W tai nuoli ylös*: palikka kääntyy, jos sillä on tilaa kääntyä. Kääntyminen edellyttää, ettei palikka ole liian lähellä pelialueen reunaa tai jo pelialueella olevia palikoita. Palikkaa voi halutessaan kääntää niin monta kertaa kuin vain ehtii.

*A tai nuoli vasemmalle*: palikka siirtyy vasemmalle, ellei pelialueen reuna tai jo pelialueella olevat palikat estä siirtymistä.

*D tai nuoli oikealle*: palikka siirtyy oikealle, ellei pelialueen reuna tai jo pelialueella olevat palikat estä siirtymistä.

*S tai nuoli alas*: palikan putoaminen nopeutuu niin pitkään kuin näppäimen pitää painettuna.

*Välilyönti*: peli keskeytyy ja näkyviin tulee aloitusvalikko, josta voi valita mitä tekee seuraavaksi. Kesken pelin voi siis halutessaan käydä tutustumassa ohjeisiin tai parhaiden tulosten listaan.

**Lopeta Tetris** sulkee ohjelman välittömästi.
