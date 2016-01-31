# Personal-Training-Project
Projet en groupe de 2

## Nom des éléves
* Maxime Cocquempot
* Arthur Veys

## Librairies Supplémentaires
* Google API
* Jackson JSON

## Tâches réalisées
* Splashscreen dans le datastore et dans le memcache (**Arthur**)
* Ajout d'un plan d'entrainement avec gestion de Queue(**Maxime**)
* Recherche (**Arthur**)
* Affichage du flux RSS de la section sport de la BBC dans la recherche (**Arthur**)
* Affichage des exercices d'un plan recherché (**Arthur**)
* Authentification avec google (**Arthur**)
* Affichage des exercices effectué par un utilisateur (page personnelle) (**Maxime**)
* Enregistrement de la reussite ou l'échec d'un exercice dans le datastore (**Maxime**)

## Tâches non réalisées
* Gerer le timer durant l'éxécution des exercices
* Recherche par domaine
* Page de statistiques
* Chat avec un Coach

## URL
http://personal-training-project.appspot.com/

## Commentaire
* **attention**, la recherche est aboslue : il faut rentrer exactement le nom du plan ou de l'exercice pour le trouver.
  C'est une limitation de la recherche de Google App Engine qui n'a pas d'opérateur LIKE pour le datastore. il aurait fallu utiliser un document search)
* Les validations des auth token de Google se font coté serveur avec la librairie Google adaptée 
* Certaines requêtes AJAX peuvent mettre du temps à s'effectuer car il faut parfois attendre le chargememnt des librairies Google

##  Schéma d'architecture

TODO
