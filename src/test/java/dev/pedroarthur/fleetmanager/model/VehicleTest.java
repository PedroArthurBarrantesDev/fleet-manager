package dev.pedroarthur.fleetmanager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void shouldCreateVehicleAsAvailable() {
        Vehicle vehicle = new Vehicle(3, "FXZ3395", "Volkswagen Up", 2017, 400);
        assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
    }

    @Test
    void shouldRejectBlankPlate() {
        assertThrows(IllegalArgumentException.class, () -> new Vehicle(1, "", "Up", 2017, 400));
    }
}