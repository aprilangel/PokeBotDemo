#PokeBotDemo
[![Build Status](https://travis-ci.org/aprilangel/PokeBotDemo.png?branch=master)](https://travis-ci.org/aprilangel/PokeBotDemo/)

Bot pokemon de démonstration contruit en utilisant TwitterUserStreamEasy et Twitter4J. Pour le rendre fonctionnel,
il faut compléter le fichier `src/main/resource/twitter4j.properties` avec les clef que vous trouverez sur la page
https://dev.twitter.com/apps après avoir créé une application.

Sujet : http://bit.ly/iut-5

Git on Windows : http://code.google.com/p/msysgit/downloads/list

Commandes utiles a l'IUT :
```
wget https://raw.github.com/pcreux/git_config/master/gitconfig -O ~/.gitconfig
wget https://raw.github.com/pcreux/git_config/master/githelpers -O ~/.githelpers
git config --global credential.helper 'cache --timeout=3600'
```
  
TODO :  
 - Etienne : Corriger le StatCell : `#statAttack #Puissance #ILikeTrains` renvoie des erreurs bizarres.
 - Angel : Bug PokeCentenr
 - Alexandre : Faire/Finir un script d'initialisation pour le SQL
 - `FIGHTING` se bloque après la mort
 - Corriger des violations Sonar
 - TESTER ! TESTER ! TESTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEER !!!!!!!!!!!!!
 - Come with me bitch, we're gonna have a nice time in da PokeCenter !
 - StatCell : Remplacer `bot.getAtk1()` par `skill` dans ces lignes : `return "@"+question.getScreenName()+" "+bot.getAtk1()+" - Puissance : "+puissance;`


