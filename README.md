# Calculateur d'empreinte carbone en Java et JavaFX 

Ce projet a été créé en utilisant Eclipse et le SDK JavaFX 19. Il utilise également la bibliothèque Gson 2.8.6.

## Prérequis
Eclipse avec le plugin JavaFX

Le SDK JavaFX 19 (téléchargeable https://gluonhq.com/products/javafx/)

## Structure du projet
- Le répertoire src contient les fichiers source du projet

- Le répertoire bin contient les fichiers binaires générés par Eclipse

- Les répertoires lib contiennent les bibliothèques JavaFX utilisées dans le projet

- Le répertoire jar_files contient la bibliothèque Gson utilisée dans le projet

## Configuration du projet dans Eclipse
Ouvrez Eclipse et sélectionnez "File > Import" dans le menu

Sélectionnez "Existing Projects into Workspace" et cliquez sur "Next"

Cliquez sur "Browse" et sélectionnez le répertoire du projet

Sélectionnez le projet dans la liste et cliquez sur "Finish"

Le projet devrait maintenant être importé dans Eclipse et prêt à être utilisé.

## Exécution du projet
Pour exécuter le projet, ouvrez le fichier source Java "Main.java" pour éxécuter le code en console ou "App.java" pour éxécuter l'interface graphique et cliquez sur le bouton "Run" dans la barre d'outils d'Eclipse. Vous pouvez également utiliser la commande "Run As > Java Application" dans le menu "Run" du menu contextuel du fichier source.

## Tests
Le projet inclut des tests unitaires JUnit qui peuvent être exécutés en utilisant la commande "Run As > JUnit Test" dans le menu "Run" du menu contextuel du fichier de test.

## Remarques
Assurez-vous d'avoir configuré le classpath du projet correctement dans Eclipse avant de compiler ou d'exécuter le projet.
Le module empreinte_c a les dépendances suivantes :
- junit
- javafx.base
- javafx.graphics
- javafx.fxml
- javafx.controls
- javafx.media
- com.google.gson
- org.junit.jupiter.api

Il exporte les packages vue, controleur et consoCarbone et les ouvre pour l'accès par réflexion.

