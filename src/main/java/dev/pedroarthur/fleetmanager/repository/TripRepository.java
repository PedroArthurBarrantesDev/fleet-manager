package dev.pedroarthur.fleetmanager.repository;

import dev.pedroarthur.fleetmanager.model.Trip;

public class TripRepository extends InMemoryRepository<Trip> {

    public TripRepository() {
        super(Trip::getId);
    }
}
