//
// Copyright 2015 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

// FirebaseDemo_ESP8266 is a sample that demo the different functions
// of the FirebaseArduino API.

#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

#include <Time.h>

#include "DHT.h"
#define DHTPIN 4     // what digital pin the DHT22 is conected to
#define DHTTYPE DHT22   // there are multiple kinds of DHT sensors

DHT dht(DHTPIN, DHTTYPE); 



// Set these to run example.
#define FIREBASE_HOST "iot-trashcan-project.firebaseio.com"
#define FIREBASE_AUTH "fh6q9MrCPzjl6CfTutblRBbIYierAf1q6qfhED6N"

#define WIFI_SSID "UCInet Mobile Access"
#define WIFI_PASSWORD ""


//#define WIFI_SSID "iPhone"
//#define WIFI_PASSWORD "nm6ae3i18f6cv"

//#define WIFI_SSID "EC4851"
//#define WIFI_PASSWORD "5BUL7BA332958"

int trigPin = 13;    //Trig - green Jumper
int echoPin = 12;    //Echo - yellow Jumper
long duration, cm, inches;


void setup() {
  Serial.begin(9600);

  Serial.println(WiFi.macAddress());
  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  

  //init time
  //configTime(3 * 3600, 0, "pool.ntp.org", "time.nist.gov");
  configTime(1 * 3600, 0, "pool.ntp.org", "time.nist.gov");
  Serial.println("\nWaiting for time");
  while (!time(nullptr)) {
    Serial.print(".");
    delay(1000);
  }

  //Define ultrasonic inputs and outputs  
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);

}

int n = 0;


//set to :921600
void loop() {
  // The sensor is triggered by a HIGH pulse of 10 or more microseconds.
  // Give a short LOW pulse beforehand to ensure a clean HIGH pulse:
  digitalWrite(trigPin, LOW);
  delayMicroseconds(5);
  digitalWrite(trigPin, HIGH);
//  delayMicroseconds(10);
//  digitalWrite(trigPin, LOW);
// 
  // Read the signal from the sensor: a HIGH pulse whose
  // duration is the time (in microseconds) from the sending
  // of the ping to the reception of its echo off of an object.
  pinMode(echoPin, INPUT);
  duration = pulseIn(echoPin, HIGH, 5000);
 
  // convert the time into a distance
  cm = (duration/2) / 29.1;
  inches = (duration/2) / 74; 

  if(cm == 0)
    return;
//  
  Serial.print("cm:");
  Serial.println(cm);


  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& root = jsonBuffer.createObject();
  
  int distance = cm;
  root["distance"] = distance;

  //high pass filter
  if(cm > 70)
    cm = 70;
  int transform = map(cm, 0, 70, 0, 100);
  int full = 100 - (transform);
  
  String path = "iot_final/";
  String id = String(random(0, 5));
  path += id;
  root["full"] = full;

  //21 - 13 = 8
  
  time_t now = time(nullptr);
  now = now - (8 * 3600); // move to pacific time
  String time = String(ctime(&now));
//  Serial.println(time);
  root["time"] = time;

  float humidity = dht.readHumidity();
  float temperature = dht.readTemperature();

//  Serial.print("humidity:");
//  Serial.println(humidity);
//  Serial.print("temperature:");
//  Serial.println(temperature);
  root["humidity"] = humidity;
  root["temperature"] = temperature;


  String name = Firebase.push(path, root);
  // handle error
  if (Firebase.failed()) {
      //Serial.print("Firebase Pushing /sensor/ultrasonic failed:");
      Serial.println(Firebase.error()); 
      
      digitalWrite(BUILTIN_LED,HIGH); 
      delay(2000);             
      return;
  }else{
      Serial.print("Success to ");
      Serial.print(path);
      Serial.print("   ");
      Serial.println(name);
   
      delay(200);
  }
//  delay(2000);
  
}
