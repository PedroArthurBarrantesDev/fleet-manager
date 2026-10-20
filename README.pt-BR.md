# Fleet Manager

[English](README.md) · **Português (Brasil)**

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-build-C71A36?style=flat&logo=apachemaven&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162?style=flat&logo=junit5&logoColor=white)
![Status](https://img.shields.io/badge/status-em_desenvolvimento-yellow?style=flat)

O Fleet Manager é uma aplicação de console em Java puro para cadastrar veículos e motoristas, controlar viagens e gerar relatórios. Ele foi feito sem frameworks de propósito, para praticar os fundamentos: orientação a objetos, encapsulamento, validação, coleções, streams e testes automatizados.

> **Status:** em desenvolvimento. O modelo de domínio e os repositórios em memória estão prontos, com testes unitários. Veja o [roteiro](#roteiro).

## Funcionalidades

**Implementadas**
- Modelo de domínio para veículos, motoristas e viagens.
- Validação nos construtores, para que um objeto nunca exista em estado inválido.
- Repositórios em memória (`VehicleRepository`, `DriverRepository`, `TripRepository`) para cadastrar, buscar por id e listar. Cadastrar um id repetido lança `IllegalArgumentException`.
- Testes unitários com JUnit 5.
- Regras de negócio para iniciar e finalizar viagens: `Trip.start()` e `Trip.finish()` movem a viagem e o veículo entre os status e rejeitam transições inválidas.


**Planejadas**
- Relatórios com Java Streams (por exemplo, quilômetros totais por veículo).
- Menu de console.

## Modelo de domínio

| Classe | Campos | Observações |
|---|---|---|
| `Vehicle` | `id`, `plate`, `model`, `year`, `capacityKg`, `status` | `status` é um `VehicleStatus`: `AVAILABLE`, `ON_TRIP` ou `MAINTENANCE`. Todo veículo novo começa como `AVAILABLE`. |
| `Driver` | `id`, `name`, `licenseNumber`, `licenseCategory` | `licenseCategory` é um `LicenseCategory`: `B`, `C`, `D` ou `E`. |
| `Trip` | `id`, `vehicle`, `driver`, `origin`, `destination`, `distanceKm`, `status` | `status` é um `TripStatus`: `PLANNED`, `IN_PROGRESS` ou `COMPLETED`. Toda viagem nova começa como `PLANNED`. |

## Regras de validação

| Classe | Regra | Exceção |
|---|---|---|
| `Vehicle` | `plate` e `model` não podem ser nulos nem vazios | `IllegalArgumentException` |
| `Vehicle` | `capacityKg` deve ser maior que zero | `IllegalArgumentException` |
| `Driver` | `name` e `licenseNumber` não podem ser nulos nem vazios | `IllegalArgumentException` |
| `Driver` | `licenseCategory` não pode ser nula | `NullPointerException` |
| `Trip` | `vehicle` e `driver` não podem ser nulos | `NullPointerException` |
| `Trip` | `origin` e `destination` não podem ser nulos nem vazios | `IllegalArgumentException` |
| `Trip` | `distanceKm` deve ser maior que zero | `IllegalArgumentException` |

## Requisitos

- JDK 21
- Maven 3.9+ (ou o Maven que vem com o IntelliJ IDEA)

## Como começar

```bash
git clone https://github.com/PedroArthurBarrantesDev/fleet-manager.git
cd fleet-manager
mvn test
```

## Estrutura do projeto

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

## Roteiro

- [x] Modelo de domínio com validação
- [x] Testes unitários do modelo
- [x] Repositórios em memória
- [x] Regras de negócio: iniciar e finalizar viagens
- [ ] Relatórios com Streams
- [ ] Menu de console

## Autor

**Pedro Arthur Barrantes**, desenvolvedor Java júnior.
[LinkedIn](https://linkedin.com/in/pedro-arthur-barrantes) · [GitHub](https://github.com/PedroArthurBarrantesDev)

## Licença

Veja o arquivo [LICENSE](LICENSE).
