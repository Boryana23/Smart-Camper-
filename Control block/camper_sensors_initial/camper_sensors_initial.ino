#include <DHTesp.h>
#include <ThingsBoard.h>
#include <WiFi.h>
#include <WiFiClient.h>
#include <WebServer.h>
#include <ESPmDNS.h>   

#define COUNT_OF(x) ((sizeof(x)/sizeof(0[x])) / ((size_t)(!(sizeof(x) % sizeof(0[x])))))


#define WIFI_AP_NAME       "BB"
#define WIFI_PASSWORD       "bb2304bb"
#define TOKEN               "St9imww2ZkCVmxUT4dwd"
#define THINGSBOARD_SERVER   "zaimov.eu"
#define SERIAL_DEBUG_BAUD    115200

WiFiClient espClient;
ThingsBoard tb(espClient);

int status = WL_IDLE_STATUS;
int gpioState[] = {0, 0, 0, 0};
uint8_t leds_control[] = { 23, 19, 22, 21 };
#define DHT_PIN 27
DHTesp dht;

unsigned long lastSend;
#define ANALOG_PIN 26
float volt;

int quant = 20;
int led_delay = 1000;
int send_delay = 2000;

int led_passed = 0;
int send_passed = 0;

bool subscribed = false;
int current_led = 0;

RPC_Response processGetGpioState(const RPC_Data &data){
  
  String payload;
  char buff[5];
  StaticJsonDocument<200> doc;

  Serial.println("Received the set GPIO RPC method");
  
 for (int i = 0; i < COUNT_OF(leds_control); i++) {
    doc[String(i)] = gpioState[i];
  }
  serializeJson(doc, payload);
  return RPC_Response(NULL, payload.c_str());  
}

RPC_Response processSetGpioState(const RPC_Data &data){
  Serial.println("Received the get GPIO RPC method");

  int pin = data["pin"];
  bool enabled = data["enabled"];

  if (pin < COUNT_OF(leds_control)) {
    Serial.print("Setting LED ");
    Serial.print(pin);
    Serial.print(" to state ");
    Serial.println(enabled);

    digitalWrite(leds_control[pin], !enabled);
    gpioState[pin] = enabled;
  }

  return RPC_Response(data["pin"], (bool)data["enabled"]);
}

RPC_Callback callbacks[] = {
  { "setGpioStatus",    processSetGpioState },
  { "getGpioStatus",    processGetGpioState }
};

void setup() {

  Serial.begin(SERIAL_DEBUG_BAUD);
  WiFi.begin(WIFI_AP_NAME, WIFI_PASSWORD);
  void InitWiFi();
  dht.setup(DHT_PIN, DHTesp::DHT11);

  for (size_t i = 0; i < COUNT_OF(leds_control); ++i) {
    pinMode(leds_control[i], OUTPUT);
    digitalWrite(leds_control[i], HIGH);
  }

  pinMode(ANALOG_PIN, INPUT_PULLUP);
  lastSend = 0;

}

void loop() {
  delay(quant);

  led_passed += quant;
  send_passed += quant;

  if (WiFi.status() != WL_CONNECTED) {
    void reconnect();
    return;
  }

  if ( millis() - lastSend > 1000 ) { 
    getVoltage();
    lastSend = millis();
  }

  if (!tb.connected()) {
    subscribed = false;
    Serial.print("Connecting to: ");
    Serial.print(THINGSBOARD_SERVER);
    Serial.print(" with token ");
    Serial.println(TOKEN);
    if (!tb.connect(THINGSBOARD_SERVER, TOKEN)) {
      Serial.println("Failed to connect");
      return;
    }
  }

  if (!subscribed) {
    Serial.println("Subscribing for RPC...");
      if (!tb.RPC_Subscribe(callbacks, COUNT_OF(callbacks))) {
      Serial.println("Failed to subscribe for RPC");
      return;
    }

    Serial.println("Subscribe done");
    subscribed = true;
  }

  if (send_passed > send_delay) {
    Serial.println("Sending data...");

    TempAndHumidity lastValues = dht.getTempAndHumidity();    
    if (isnan(lastValues.humidity) || isnan(lastValues.temperature)) {
      Serial.println("Failed to read from DHT sensor!");
    } else {
      tb.sendTelemetryFloat("Temperature", lastValues.temperature);
      tb.sendTelemetryFloat("Humidity", lastValues.humidity);
    }   

    send_passed = 0;
  }

  tb.loop();
}

void  getVoltage(){
  volt = analogRead(ANALOG_PIN);
  double voltage = map(volt,0,1023, 0, 2500);
  voltage /=100;
  Serial.print("Voltage: ");
  Serial.print(voltage);
  Serial.println("V");
  tb.sendTelemetryFloat("Voltage", voltage);
}

void InitWiFi()
{
  Serial.println("Connecting to Local network ...");
  WiFi.begin(WIFI_AP_NAME, WIFI_PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("Connected to Local network!");
}

void reconnect() {
  status = WiFi.status();
  if ( status != WL_CONNECTED) {
    WiFi.begin(WIFI_AP_NAME, WIFI_PASSWORD);
    while (WiFi.status() != WL_CONNECTED) {
      delay(500);
      Serial.print(".");
    }
    Serial.println("Connected to Local network!");
  }
}