#include <ArduinoJson.h>
#include <Wire.h>
#include <hd44780.h>
#include <hd44780ioClass/hd44780_I2Cexp.h> // include i/o class header
#include <WiFi.h>
#include <PubSubClient.h>

  // LCD Screen
hd44780_I2Cexp lcd; // declare lcd object: auto locate & config display for hd44780 chip
const int LCD_COLS = 16;   // Nombre de colonnes de l'écran LCD
const int LCD_ROWS = 2;    // Nombre de lignes de l'écran LCD

 // WiFi 
const char * ssid = "[WIFI NAME]"; // Entrez votre SSID WiFi  
const char * password = "[WIFI PASSWORD]"; // Entrez votre mot de passe WiFi 

// MQTT Broker 
const char * mqtt_broker = "[BROCKER IP]";
const char * topic = "[BUS STOP TOPIC]";
const char * mqtt_username = "";
const char * mqtt_password = "";
const int mqtt_port = 1883; // PORT DU SERVICE MQTT

WiFiClient espClient;
PubSubClient client(espClient);

DynamicJsonDocument byteJsonParse(byte* str) {
  // Créer un objet JSON pour stocker les données
  DynamicJsonDocument doc(512); // Taille du document JSON, ajustez selon vos besoins

  // Désérialiser le JSON
  DeserializationError error = deserializeJson(doc, str);

  // Vérifier les erreurs de désérialisation
  if (error) {
    Serial.print("Erreur lors de la désérialisation : ");
    Serial.println(error.c_str());
  }

  // Extraire les valeurs nécessaires
  return doc;

}

DynamicJsonDocument charJsonParse(const char* str) {
  // Créer un objet JSON pour stocker les données
  DynamicJsonDocument doc(512); // Taille du document JSON, ajustez selon vos besoins

  // Désérialiser le JSON
  DeserializationError error = deserializeJson(doc, str);

  // Vérifier les erreurs de désérialisation
  if (error) {
    Serial.print("Erreur lors de la désérialisation : ");
    Serial.println(error.c_str());
  }

  // Extraire les valeurs nécessaires
  return doc;

}


void setup() {
    //Mise de la vitesse de transmission à 115200; 
    Serial.begin(115200);

    // initialize LCD with number of columns and rows:
    lcd.begin(LCD_COLS, LCD_ROWS);
    // Print a message to the LCD
    lcd.print("Arret de bus 1 I1");

    // Connecting to a Wi-Fi network 
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED) {
      delay(500);
      Serial.println("Connecting to WiFi..");
    }
    Serial.println("Connected to the Wi-Fi network");
    //connexion au broker MQTT  
    client.setServer(mqtt_broker, mqtt_port);
    client.setCallback(callback);
    while (!client.connected()) {
      String client_id = "esp32-client-";
      client_id += String(WiFi.macAddress());
      Serial.printf("The client %s connects to the public MQTT brokern \n", client_id.c_str());
      if (client.connect(client_id.c_str(), mqtt_username, mqtt_password)) {
        Serial.println("Public EMQX MQTT broker connected");
      } else {
        Serial.print("failed with state ");
        Serial.print(client.state());
        delay(2000);
      }
    }
    // Publish et subscribe 
    //client.publish(topic, "Hi, I'm ESP32");
    client.subscribe(topic);

}
  // Reception du message MQTT 
void callback(char * topic, byte * payload, unsigned int length) {
    Serial.print("Message arrived in topic: ");Serial.println(topic);
    Serial.print("=>");
    for (int i = 0; i < length; i++) {
      Serial.print((char) payload[i]);
    }
    Serial.println("");

    // Extraire les valeurs nécessaires
    DynamicJsonDocument payloadDoc = byteJsonParse(payload);
    const char* value = payloadDoc["value"];
    DynamicJsonDocument valueDoc = charJsonParse(value);

    // Extraire les valeurs nécessaires
    const char* message = valueDoc["message"];
    const char* busId = valueDoc["id"];

    // Afficher les valeurs extraites
    Serial.print("Message : ");Serial.println(message);
    Serial.println("-----------------------");
    
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("BUS "); lcd.print(busId);
    lcd.print(" A "); lcd.print(message); lcd.print("KM"); 
    lcd.setCursor(0, 1);
    lcd.print("Arrive dans "); lcd.print(message); lcd.print("min"); 


}

void loop() {
  client.loop();

}
