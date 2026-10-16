package dev.pedroarthur.fleetmanager.model;

public class Vehicle {

    private final long id;
    private final String plate;
    private final String model;
    private final int year;
    private final double capacityKg;
    private VehicleStatus status;

    public Vehicle(long id, String plate, String model, int year, double capacityKg) {

        if (plate == null || plate.isBlank()) {
            throw new IllegalArgumentException("plate must not be blank");
        }

        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("model must not be blank");
        }

        if (capacityKg <= 0 ) {
            throw new IllegalArgumentException("capacityKg must be greater than 0");
        }

        this.id = id;
        this.plate = plate;
        this.model = model;
        this.year = year;
        this.capacityKg = capacityKg;
        this.status = VehicleStatus.AVAILABLE;
    }

    public long getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getCapacityKg() {
        return capacityKg;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    void startTrip() {
        if (status != VehicleStatus.AVAILABLE) {
            throw new IllegalStateException("vehicle must be AVAILABLE to start a trip, but is " + status);
        }
        this.status = VehicleStatus.ON_TRIP;
    }

    void finishTrip() {
        if (status != VehicleStatus.ON_TRIP) {
            throw new IllegalStateException("vehicle must be ON_TRIP to finish a trip, but is " + status);
        }
        this.status = VehicleStatus.AVAILABLE;
    }
}
