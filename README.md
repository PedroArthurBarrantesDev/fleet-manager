# Fleet Manager

**English** · [Português (Brasil)](README.pt-BR.md)

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-build-C71A36?style=flat&logo=apachemaven&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162?style=flat&logo=junit5&logoColor=white)
![Status](https://img.shields.io/badge/status-work_in_progress-yellow?style=flat)

Fleet Manager is a plain Java console application to register vehicles and drivers, control trips and generate reports. It is built without frameworks on purpose, to practice the fundamentals: object-oriented design, encapsulation, validation, collections, streams and automated testing.

> **Status:** work in progress. The domain model and the in-memory repositories are done, with unit tests. See the [roadmap](#roadmap).

## Features

**Implemented**
- Domain model for vehicles, drivers and trips.
- Validation in the constructors, so an object can never exist in an invalid state.
- In-memory repositories (`VehicleRepository`, `DriverRepository`, `TripRepository`) to register, find by id and list. Registering a duplicate id throws `IllegalArgumentException`.
- Unit tests with JUnit 5.

**Planned**
- Business rules for starting and finishing trips.
- Reports with Java Streams (for example, total kilometers per vehicle).
- Console menu.

## Domain model

| Class | Fields | Notes |
|---|---|---|
| `Vehicle` | `id`, `plate`, `model`, `year`, `capacityKg`, `status` | `status` is a `VehicleStatus`: `AVAILABLE`, `ON_TRIP` or `MAINTENANCE`. New vehicles start as `AVAILABLE`. |
| `Driver` | `id`, `name`, `licenseNumber`, `licenseCategory` | `licenseCategory` is a `LicenseCategory`: `B`, `C`, `D` or `E`. |
| `Trip` | `id`, `vehicle`, `driver`, `origin`, `destination`, `distanceKm`, `status` | `status` is a `TripStatus`: `PLANNED`, `IN_PROGRESS` or `COMPLETED`. New trips start as `PLANNED`. |

## Validation rules

| Class | Rule | Exception |
|---|---|---|
| `Vehicle` | `plate` and `model` must not be null or blank | `IllegalArgumentException` |
| `Vehicle` | `capacityKg` must be greater than zero | `IllegalArgumentException` |
| `Driver` | `name` and `licenseNumber` must not be null or blank | `IllegalArgumentException` |
| `Driver` | `licenseCategory` must not be null | `NullPointerException` |
| `Trip` | `vehicle` and `driver` must not be null | `NullPointerException` |
| `Trip` | `origin` and `destination` must not be null or blank | `IllegalArgumentException` |
| `Trip` | `distanceKm` must be greater than zero | `IllegalArgumentException` |

## Requirements

- JDK 21
- Maven 3.9+ (or the Maven bundled with IntelliJ IDEA)

## Getting started

```bash
git clone https://github.com/PedroArthurBarrantesDev/fleet-manager.git
cd fleet-manager
mvn test
```

## Project structure

```
src
├── main/java/dev/pedroarthur/fleetmanager/model
│   ├── Vehicle.java
│   ├── VehicleStatus.java
│   ├── Driver.java
│   ├── LicenseCategory.java
│   ├── Trip.java
│   └── TripStatus.java
├── main/java/dev/pedroarthur/fleetmanager/repository
│   ├── InMemoryRepository.java
│   ├── VehicleRepository.java
│   ├── DriverRepository.java
│   └── TripRepository.java
├── test/java/dev/pedroarthur/fleetmanager/model
│   ├── VehicleTest.java
│   ├── DriverTest.java
│   └── TripTest.java
└── test/java/dev/pedroarthur/fleetmanager/repository
    ├── VehicleRepositoryTest.java
    ├── DriverRepositoryTest.java
    └── TripRepositoryTest.java
```

## Roadmap

- [x] Domain model with validation
- [x] Unit tests for the model
- [x] In-memory repositories
- [ ] Business rules: start and finish trips
- [ ] Reports with Streams
- [ ] Console menu

## Author

**Pedro Arthur Barrantes**, junior Java developer.
[LinkedIn](https://linkedin.com/in/pedro-arthur-barrantes) · [GitHub](https://github.com/PedroArthurBarrantesDev)

## License

See the [LICENSE](LICENSE) file.
