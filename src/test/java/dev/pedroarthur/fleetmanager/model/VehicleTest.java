package dev.pedroarthur.fleetmanager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void shouldCreateVehicleAsAvailable() {
        Vehicle veiculo1 = new Vehicle(1, "FXZ3395", "Up", 2017, 400);
        assertEquals(VehicleStatus.AVAILABLE, veiculo1.getStatus());
    }

    @Test
    void shouldRejectBlankPlate() {
        assertThrows(IllegalArgumentException.class, () -> new Vehicle(1, "", "Up", 2017, 400));
    }
}