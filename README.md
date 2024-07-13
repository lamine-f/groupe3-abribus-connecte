# Projet SmartAutoBus

Description du projet SmartAutoBus.

## Table des Matières

1. [Introduction](#introduction)
2. [Structure des Dossiers](#structure-des-dossiers)

## Introduction

Le projet SmartAutoBus est une solution de gestion et de communication pour les autobus intelligents, intégrant divers capteurs et modules de communication pour améliorer l'expérience des passagers.

## Structure des Dossiers

```plaintext
.
├── code
│   ├── controller
│   │   └── smartautobus
│   │       ├── pom.xml
│   │       └── src
│   │           └── main
│   │               └── java
│   │                   ├── lord
│   │                   │   └── dic1
│   │                   │       └── iot
│   │                   │           └── autobus
│   │                   │               ├── communication
│   │                   │               │   └── mqtt
│   │                   │               │       ├── datas
│   │                   │               │       │   ├── BusPosition.java
│   │                   │               │       │   ├── BusStopMessage.java
│   │                   │               │       │   └── Data.java
│   │                   │               │       └── mqttclient
│   │                   │               │           ├── enums
│   │                   │               │           │   └── MqttClientType.java
│   │                   │               │           ├── exceptions
│   │                   │               │           │   ├── MqttClientManagerClientConnexionException.java
│   │                   │               │           │   ├── MqttClientManagerPortNotDefinedException.java
│   │                   │               │           │   └── MqttClientManagerServerNotDefinedException.java
│   │                   │               │           ├── Message.java
│   │                   │               │           ├── MqttGlobalClientImpl.java
│   │                   │               │           ├── MqttGlobalClient.java
│   │                   │               │           └── sensors
│   │                   │               │               ├── display
│   │                   │               │               │   ├── DisplayData.java
│   │                   │               │               │   └── MqttDisplaySensorClient.java
│   │                   │               │               ├── position
│   │                   │               │               │   ├── MqttPositionSensorClient.java
│   │                   │               │               │   └── PositionSensorData.java
│   │                   │               │               └── SensorData.java
│   │                   │               ├── configs
│   │                   │               │   ├── ConfigurationManager.java
│   │                   │               │   └── MqttConfigurationManager.java
│   │                   │               ├── entities
│   │                   │               │   ├── Bus.java
│   │                   │               │   ├── BusStop.java
│   │                   │               │   ├── Itinerary.java
│   │                   │               │   ├── Line.java
│   │                   │               │   └── NextBusInfo.java
│   │                   │               ├── itinerary
│   │                   │               │   └── ItineraryManagement.java
│   │                   │               ├── services
│   │                   │               │   ├── BusService.java
│   │                   │               │   ├── BusStopService.java
│   │                   │               │   └── impl
│   │                   │               │       ├── BusServiceImpl.java
│   │                   │               │       └── BusStopServiceImpl.java
│   │                   │               ├── simulator
│   │                   │               │   ├── autobus
│   │                   │               │   │   ├── AutoBusSimulatorImpl.java
│   │                   │               │   │   ├── AutoBusSimulator.java
│   │                   │               │   │   ├── ItineraryGenDatas.java
│   │                   │               │   │   └── PositionGenData.java
│   │                   │               │   └── busstop
│   │                   │               │       ├── BusStopCommunicationImpl.java
│   │                   │               │       └── BusStopCommunication.java
│   │                   │               ├── tools
│   │                   │               │   ├── Distance.java
│   │                   │               │   ├── FileManager.java
│   │                   │               │   └── Haversine.java
│   │                   │               └── utils
│   │                   │                   ├── Store.java
│   │                   │                   └── Utils.java
│   │                   ├── Main.java
│   │                   └── resources
│   │                       ├── config.properties
│   │                       ├── itinerary1.json
│   │                       └── mqttconfig.properties
│   └── electronic
│       └── esp32-bus1
│           └── esp32-busstop1.ino
├── documentation
│   ├── Documentation_du_Module_GPS_Geekstory_GT_U7.pdf
│   ├── Documentation_du_Module_GSM.pdf
│   └── Documentation_du_module_LILYGO__TTGO_T_SIM_A7670G.pdf
├── images
│   ├── 20240605_150513.jpg
│   ├── 20240703_124426.jpg
│   ├── 20240703_150525.jpg
│   ├── 20240705_131604.jpg
│   ├── 20240705_131824.jpg
│   ├── 20240705_140516.jpg
│   └── 20240705_140757.jpg
└── Rapport.pdf
```
### Description des Dossiers

- **code** : Contient tout le code source du projet.
  - **controller/smartautobus** : Contrôleur principal de l'application SmartAutoBus.
    - **pom.xml** : Fichier de configuration Maven.
    - **src/main/java** : Contient les fichiers Java sources.
      - **lord/dic1/iot/autobus** : Structure des packages Java.
        - **communication/mqtt** : Contient les classes de communication MQTT.
          - **datas** : Classes de données pour la communication MQTT.
            - **BusPosition.java** : Classe pour la position du bus.
            - **BusStopMessage.java** : Classe pour les messages des arrêts de bus.
            - **Data.java** : Classe de données générale.
          - **mqttclient** : Implémentation des clients MQTT.
            - **enums** : Enums utilisés par les clients MQTT.
              - **MqttClientType.java** : Enum pour les types de clients MQTT.
            - **exceptions** : Exceptions spécifiques aux clients MQTT.
              - **MqttClientManagerClientConnexionException.java** : Exception de connexion du client.
              - **MqttClientManagerPortNotDefinedException.java** : Exception de port non défini.
              - **MqttClientManagerServerNotDefinedException.java** : Exception de serveur non défini.
            - **Message.java** : Classe pour les messages MQTT.
            - **MqttGlobalClientImpl.java** : Implémentation globale du client MQTT.
            - **MqttGlobalClient.java** : Interface du client MQTT global.
            - **sensors** : Clients MQTT pour différents capteurs (affichage, position).
              - **display** : Capteurs d'affichage.
                - **DisplayData.java** : Classe de données pour l'affichage.
                - **MqttDisplaySensorClient.java** : Client MQTT pour les capteurs d'affichage.
              - **position** : Capteurs de position.
                - **MqttPositionSensorClient.java** : Client MQTT pour les capteurs de position.
                - **PositionSensorData.java** : Classe de données pour la position des capteurs.
              - **SensorData.java** : Classe de données générale pour les capteurs.
        - **configs** : Gestion de la configuration.
          - **ConfigurationManager.java** : Gestionnaire de configuration.
          - **MqttConfigurationManager.java** : Gestionnaire de configuration MQTT.
        - **entities** : Entités représentant les différents composants du système.
          - **Bus.java** : Classe représentant un bus.
          - **BusStop.java** : Classe représentant un arrêt de bus.
          - **Itinerary.java** : Classe représentant un itinéraire.
          - **Line.java** : Classe représentant une ligne de bus.
          - **NextBusInfo.java** : Classe représentant les informations sur le prochain bus.
        - **itinerary** : Gestion des itinéraires.
          - **ItineraryManagement.java** : Gestionnaire des itinéraires.
        - **services** : Services pour gérer les bus et arrêts de bus.
          - **BusService.java** : Service pour gérer les bus.
          - **BusStopService.java** : Service pour gérer les arrêts de bus.
          - **impl** : Implémentations des services.
            - **BusServiceImpl.java** : Implémentation du service de gestion des bus.
            - **BusStopServiceImpl.java** : Implémentation du service de gestion des arrêts de bus.
        - **simulator** : Simulateurs pour les bus et arrêts de bus.
          - **autobus** : Simulateurs pour les bus.
            - **AutoBusSimulatorImpl.java** : Implémentation du simulateur de bus.
            - **AutoBusSimulator.java** : Interface du simulateur de bus.
            - **ItineraryGenDatas.java** : Données de génération d'itinéraire.
            - **PositionGenData.java** : Données de génération de position.
          - **busstop** : Simulateurs pour les arrêts de bus.
            - **BusStopCommunicationImpl.java** : Implémentation de la communication des arrêts de bus.
            - **BusStopCommunication.java** : Interface de la communication des arrêts de bus.
        - **tools** : Outils utilitaires.
          - **Distance.java** : Calcul de distance.
          - **FileManager.java** : Gestionnaire de fichiers.
          - **Haversine.java** : Calcul de distance (formule de Haversine).
        - **utils** : Utilitaires généraux.
          - **Store.java** : Classe pour le stockage.
          - **Utils.java** : Utilitaires divers.
      - **Main.java** : Point d'entrée principal de l'application.
      - **resources** : Fichiers de ressources.
        - **config.properties** : Fichier de configuration.
        - **itinerary1.json** : Itinéraire en format JSON.
        - **mqttconfig.properties** : Configuration MQTT.
  - **electronic/esp32-bus1** : Code pour l'ESP32.
    - **esp32-busstop1.ino** : Code source Arduino pour l'ESP32.

- **documentation** : Contient la documentation des différents modules et composants.
  - **Documentation_du_Module_GPS_Geekstory_GT_U7.pdf** : Documentation du module GPS.
  - **Documentation_du_Module_GSM.pdf** : Documentation du module GSM.
  - **Documentation_du_module_LILYGO__TTGO_T_SIM_A7670G.pdf** : Documentation du module LILYGO TTGO T-SIM.

- **images** : Contient des images utilisées dans la documentation.
  - **20240605_150513.jpg** : Exemple d'image.

- **Rapport.pdf** : Rapport du projet.
