# Demo PowerMock

Moquer les comportements de méthode est une pratique courante et reconnu lors de la réalisation de tests unitaires. 

*Comment moquer des méthodes privées, des méthodes statiques... ?*

## Qu'est-ce que PowerMockito ?
PowerMockito est une API d'extension de PowerMock compatible avec Mockito. Il fournit des outils pour travailler 
avec l'API Java Reflection d'une manière simple pour surmonter les problèmes de Mockito, tels que le manque de capacité 
à moquer des méthodes finales, statiques ou privées.

PowerMock effectue ces astuces en modifiant les octets du code au moment de l'exécution lorsque les tests sont en cours d'exécution.

    - les méthodes "static" : méthodes qui s'appellent sur une classe et non une instance de classe
    - les méthodes "final" : ne peuvent pas être modifiée, leur redéfinition grâce à l'héritage est interdite.
    - les méthodes "private" : ne sont accessibles qu'à partir du fichier où elle sont définis


## Preparation des Tests avec PowerMockito
### Installer les dépendances

    <dependency>
        <groupId>org.powermock</groupId>
        <artifactId>powermock-module-junit4</artifactId>
        <version>${powermock.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.powermock</groupId>
        <artifactId>powermock-api-mockito2</artifactId>
        <version>${powermock.version}</version>
        <scope>test</scope>
    </dependency>

*NB: Certaines version de Mockito et PowerMockito ne sont pas compatibles
=> https://github.com/powermock/powermock/wiki/Mockito#supported-versions*

### Initialiser le test avec 2 annotations :

    @RunWith(PowerMockRunner.class)
    @PrepareForTest(fullyQualifiedNames = "sf.demo.sampleApp.util.*")

`@PrepareForTest` représente un tableau de noms complets de types que nous voulons simuler.

## Sources
* https://github.com/powermock/powermock/wiki
* https://www.baeldung.com/intro-to-powermock
* https://fr.wikibooks.org/wiki/Programmation_Java/R%C3%A9flexion


    