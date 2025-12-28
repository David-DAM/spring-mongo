# Spring Mongo: Financial Events

Proyecto de demostración de **Spring Boot 4.0** con **MongoDB Sharded Cluster** para el manejo de eventos financieros en
tiempo real.

## 🛠️ Tecnologías

- **Java 25**
- **Spring Boot 4.0.1**
- **Spring Data MongoDB**
- **Spring MVC**
- **Lombok**
- **MapStruct 1.6.2**
- **Spring Boot Actuator**
- **Docker** (para el clúster de MongoDB)

## 🚀 Arquitectura MongoDB

Este proyecto implementa un **clúster de MongoDB con sharding** que incluye:

- **1 Config Server** (`configsvr:27019`)
- **2 Shards**:
    - `shard1` (Puerto 27018) - Zona **EU** para moneda EUR
    - `shard2` (Puerto 27020) - Zona **US** para moneda USD
- **1 Mongos Router** (Puerto 27017)

### Estrategia de Sharding

- **Base de datos**: `finance`
- **Colección**: `events`
- **Shard Key**: `{currencyId: 1, timestamp: 1}`
- **Zone Sharding**:
    - `EUR` → Shard1 (Zona EU)
    - `USD` → Shard2 (Zona US)

## ⚙Características

### 1. Carga de Eventos Financieros (Scheduler)

Tarea programada que genera eventos financieros cada 30 segundos con datos aleatorios de tipo BUY/SELL para monedas EUR
y USD.

### 2. Change Stream Listener

Escucha cambios en la colección `events` en tiempo real usando MongoDB Change Streams con soporte para **Resume Tokens
** (recuperación ante fallos).

## 📋 Requisitos Previos

- Java 25+
- Docker & Docker Compose
- Maven 3.9+

## 🏃 Ejecución

### 1. Iniciar el clúster de MongoDB

```bash 
cd docker docker-compose up -d
``` 

El script `init.sh` se encargará de:

- Inicializar los Replica Sets
- Configurar los shards
- Habilitar sharding en la base de datos
- Crear las zonas y rangos de distribución

### 2. Ejecutar la aplicación

```bash 
./mvnw spring-boot:run
```

## Pruebas de API

Importa el archivo `postman_collection.json` en Postman para probar los endpoints de la API.

## Monitoreo

La aplicación incluye **Spring Boot Actuator** para monitoreo y métricas.

Endpoints disponibles:

- `/actuator/health` - Estado de salud
- `/actuator/info` - Información de la aplicación
- `/actuator/metrics` - Métricas

## 📝 Licencia

Este proyecto es de demostración y uso educativo.
