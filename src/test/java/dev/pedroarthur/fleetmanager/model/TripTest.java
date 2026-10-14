package dev.pedroarthur.fleetmanager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {

    @Test
    void shouldCreateValidVehicle() {
        Vehicle vehicle = new Vehicle(1, "NMV5798","Toyota Hilux SW4", 2016, 600);

        assertEquals(1, vehicle.getId());
        assertEquals("NMV5798", vehicle.getPlate());
        assertEquals("Toyota Hilux SW4", vehicle.getModel());
        assertEquals(2016, vehicle.getYear());
        assertEquals(600, vehicle.getCapacityKg());
    }

    @Test
    void shouldRejectNullVehicle() {
        Driver driver = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);

        assertThrows(NullPointerException.class, () -> new Trip(1, null, driver, "São José do Rio Preto", "Santo André", 450));
    }

    @Test
    void shouldRejectBlankDestination() {
        Vehicle vehicle = new Vehicle(5, "FZA7928", "Jeep Renegade", 2018, 500);
        Driver driver = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);

        assertThrows(IllegalArgumentException.class, () -> new Trip(7, vehicle, driver, "São José do Rio Preto", "", 900));
    }

    @Test
    void shouldRejectBlankOrigin() {
        Vehicle vehicle = new Vehicle(5, "FZA7928", "Jeep Renegade", 2018, 500);
        Driver driver = new Driver(2, "Jonathan Silva", "2871", LicenseCategory.B);

        assertThrows(IllegalArgumentException.class, () -> new Trip(49, vehicle, driver, "", "São Paulo", 736));
    }

    @Test
    void shouldRejectZeroDistance() {
        Vehicle vehicle = new Vehicle(5, "FZA7928", "Jeep Renegade", 2018, 500);
        Driver driver = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);

        assertThrows(IllegalArgumentException.class, () -> new Trip(23, vehicle, driver, "Santo Amaro", "São Bernardo do Campo", 0));
    }

}