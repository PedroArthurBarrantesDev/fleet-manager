package dev.pedroarthur.fleetmanager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {


    @Test
    void shouldCreateValidTrip() {
        Vehicle availableVehicle = new Vehicle(3, "FXZ3984", "Volkswagen Up", 2017, 400);
        assertEquals(VehicleStatus.AVAILABLE, availableVehicle.getStatus());

        Driver licensedDriver = new Driver(100, "Janaina Ferreira", "7839", LicenseCategory.D);

        Trip validTrip = new Trip(1, availableVehicle, licensedDriver, "São Bernardo do Campo", "Natal", 2500);
        assertEquals(TripStatus.PLANNED, validTrip.getStatus());
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

    @Test
    void shouldRejectNullDriver() {
        Vehicle availableVehicle = new Vehicle(99, "ABC0123", "Nissan Kicks", 2018, 500);

        assertThrows(NullPointerException.class, () -> new Trip(45, availableVehicle, null, "São Paulo", "São Mateus", 200));
    }

}