package dev.pedroarthur.fleetmanager.model;

import java.util.Objects;

public class Trip {
    private final long id;
    private final Vehicle vehicle;
    private final Driver driver;
    private final String origin;
    private final String destination;
    private final double distanceKm;
    private TripStatus status;

    public Trip(long id, Vehicle vehicle, Driver driver, String origin, String destination, double distanceKm) {

        Objects.requireNonNull(vehicle, "vehicle must not be null");
        Objects.requireNonNull(driver, "driver must not be null");

        if (origin == null || origin.isBlank()) {
            throw new IllegalArgumentException("origin must not be blank");
        }

        if (destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("destination must not be blank");
        }

        if (distanceKm <= 0) {
            throw new IllegalArgumentException("distanceKm must be greter than zero");
        }

        this.id = id;
        this.vehicle = vehicle;
        this.driver = driver;
        this.origin = origin;
        this.destination = destination;
        this.distanceKm = distanceKm;
        this.status = TripStatus.PLANNED;
    }

    public long getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public TripStatus getStatus() {
        return status;
    }

}
