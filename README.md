# 🌱 Automated Greenhouse Management System (AGMS)

A **Microservices-based Smart Agriculture Platform** designed to automate greenhouse monitoring and control using real-time sensor data.

---

## 📌 Overview

AGMS is a distributed system that:

- Collects **temperature & humidity** data
- Evaluates conditions using a **rule engine**
- Automatically triggers actions like:
  - 🌬️ Turn Fan ON
  - 🔥 Turn Heater ON
- Tracks crops and greenhouse zones

---

## 🏗️ Architecture

This system follows a **Microservices Architecture** using Spring Boot and Spring Cloud.

```

```
            ┌───────────────┐
            │ API Gateway   │ (Port: 8765)
            └──────┬────────┘
                   │
    ┌──────────────┼──────────────┐
    │              │              │
```

┌─────────────┐ ┌──────────────┐ ┌──────────────┐
│ Zone Service│ │ Sensor Service│ │ Automation   │
│   (8081)    │ │   (8082)     │ │   (8083)     │
└─────────────┘ └──────────────┘ └──────────────┘
│
┌──────────────┐
│ Crop Service │ (8084)
└──────────────┘

```
    ┌──────────────┐
    │ Eureka Server│ (8761)
    └──────────────┘

    ┌──────────────┐
    │ Config Server│
    └──────────────┘
```

```

---

## ⚙️ Technologies Used

- Spring Boot
- Spring Cloud Gateway
- Spring Cloud Eureka (Service Discovery)
<img width="1192" height="1403" alt="Screenshot 2026-03-27 031042" src="https://github.com/user-attachments/assets/266677d1-3a89-4198-8f4e-7a4682e406ea" />

- Spring Cloud Config
- OpenFeign (Inter-service communication)
- Resilience4j (Circuit Breaker + Retry)
<img width="336" height="484" alt="Screenshot 2026-03-27 031016" src="https://github.com/user-attachments/assets/9476938d-f09a-4927-ac36-e0cfe73a81ce" />

- RabbitMQ (Cloud Bus - optional)
<img width="1191" height="1111" alt="Screenshot 2026-03-27 031058" src="https://github.com/user-attachments/assets/89c94740-b050-4a6b-be22-20121ec41c79" />

- 
- MySQL / H2 (Database)
- Lombok
- Zipkin
<img width="1190" height="1193" alt="Screenshot 2026-03-27 031115" src="https://github.com/user-attachments/assets/fc20f01a-1fc1-4e89-bd8d-c359b99ea61a" />

  
---

## 🚀 Microservices Description

### 1️⃣ Zone Management Service (Port: 8081)
- Manages greenhouse zones
- Stores:
  - `minTemp`
  - `maxTemp`
- Provides data for automation decisions

**APIs**
```

POST   /api/zones
GET    /api/zones/{id}
PUT    /api/zones/{id}
DELETE /api/zones/{id}

````

---

### 2️⃣ Sensor Telemetry Service (Port: 8082)
- Simulates sensor data (every 10 seconds)
- Sends data to Automation Service

**Logic**
```java
@Scheduled(fixedRate = 10000)
````

**Flow**

```
Sensor → Automation Service
```

---

### 3️⃣ Automation & Control Service (Port: 8083)

* Core **Rule Engine**

**Rules**

```
IF temp > maxTemp → TURN_FAN_ON
IF temp < minTemp → TURN_HEATER_ON
ELSE → NO_ACTION
```

**APIs**

```
POST /api/automation/process
GET  /api/automation/logs
```

---

### 4️⃣ Crop Inventory Service (Port: 8084)

* Manages crop lifecycle

**States**

```
SEEDLING → VEGETATIVE → HARVESTED
```

**APIs**

```
POST /api/crops
PUT  /api/crops/{id}/status
GET  /api/crops
```

---

## 🧠 Key Features Implemented

* ✅ **Service Discovery:** All services registered in Eureka Server
* ✅ **API Gateway:** Single entry point: `http://localhost:8765/api/**`
* ✅ **Inter-Service Communication:** Using OpenFeign
* ✅ **Fault Tolerance:** Resilience4j

  * Circuit Breaker
  * Retry Mechanism
  * Fallback support
* ✅ **Centralized Configuration:** Using Spring Cloud Config
* ✅ **Scheduled Processing:** Sensor data fetched every 10 seconds

---

## 🔁 System Flow (End-to-End)

1. Sensor Service generates data
2. Sends → Automation Service
3. Automation calls → Zone Service
4. Applies rules
5. Saves action log
6. User views logs via API Gateway

---

## 🧪 Testing (Postman)

### 🔹 Test Automation

**POST**
`http://localhost:8083/api/automation/process`

**Body**

```json
{
  "deviceId": "test-device",
  "zoneId": "zone-1",
  "value": {
    "temperature": 35,
    "humidity": 60
  }
}
```

### 🔹 Get Logs

```
GET http://localhost:8083/api/automation/logs
```

### 🔹 Gateway Test

```
GET http://localhost:8765/api/automation/logs
```

---

## ⚠️ Known Issues & Fixes

### ❌ 500 Internal Server Error

**Cause:** Zone service not running or invalid `zoneId`
**Fix:** Ensure Zone Service is UP and create zone before testing

### ❌ 401 Unauthorized

**Cause:** JWT enabled but token missing
**Fix:** Disable security or add token

### ❌ Circuit Breaker Fallback Triggered

**Cause:** Service unavailable in Eureka
**Fix:** Start all services and check Eureka dashboard

---

## 🔌 Optional: Cloud Bus (RabbitMQ)

Used for dynamic config refresh.

Run RabbitMQ:

```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

---

## ▶️ How to Run

1. Start **Config Server** and **Eureka Server**
2. Start Microservices:

   * Zone Service
   * Sensor Service
   * Automation Service
   * Crop Service
3. Start **API Gateway**

---

## 📊 Monitoring

* Circuit Breaker Status: `http://localhost:8083/actuator/health`
* Refresh Config: `POST /actuator/bus-refresh`

---

## 📁 Project Structure

```
agms/
│
├── api-gateway/
├── eureka-server/
├── config-server/
├── zone-management-service/
├── sensor-telemetry-service/
├── automation-control-service/
├── crop-inventory-service/
│
├── postman_collection.json
└── README.md
```

---

## 📌 Author

**Namal Dilmith**
Software Engineering Student | IJSE

---

## ⭐ Final Notes

* Fully working microservices system
* Demonstrates real-world distributed architecture
* Includes fault tolerance & scalability patterns
