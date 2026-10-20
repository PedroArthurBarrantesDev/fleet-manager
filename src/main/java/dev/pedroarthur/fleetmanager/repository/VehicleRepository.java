package dev.pedroarthur.fleetmanager.repository;

import dev.pedroarthur.fleetmanager.model.Vehicle;

public class VehicleRepository extends InMemoryRepository<Vehicle> {

    public VehicleRepository() {
        super(Vehicle::getId);
    }
}
