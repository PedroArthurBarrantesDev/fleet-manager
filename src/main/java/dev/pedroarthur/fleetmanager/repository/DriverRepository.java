package dev.pedroarthur.fleetmanager.repository;

import dev.pedroarthur.fleetmanager.model.Driver;

public class DriverRepository extends InMemoryRepository<Driver> {

    public DriverRepository() {
        super(Driver::getId);
    }
}
