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
    void shouldCreateValidVehicle() {
        Vehicle vehicle = new Vehicle(1, "NMV5798", "Toyota Hilux SW4", 2016, 600);

        assertEquals(1, vehicle.getId());
        assertEquals("NMV5798", vehicle.getPlate());
        assertEquals("Toyota Hilux SW4", vehicle.getModel());
        assertEquals(2016, vehicle.getYear());
        assertEquals(600, vehicle.getCapacityKg());
    }

    @Test
    void shouldRejectBlankPlate() {
        assertThrows(IllegalArgumentException.class, () -> new Vehicle(1, "", "Up", 2017, 400));
    }

    @Test
    void shouldRejectNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new Vehicle(1, "ASD3999", "Volkswagen Polo", 2010, -1));
    }

    @Test
    void shouldRejectBlankModel() {
        assertThrows(IllegalArgumentException.class, () -> new Vehicle(5, "FZA7928", "", 2018, 500));
    }

    @Test
    void shouldRejectNullPlate() {
        assertThrows(IllegalArgumentException.class, () -> new Vehicle(5, null, "Jeep Renegade", 2018, 500));
    }

    @Test
    void shouldRejectInvalidPlate() {
        assertThrows(IllegalArgumentException.class, () -> new Vehicle(5, " ", "Jeep Renegade", 2018, 500));
    }

}