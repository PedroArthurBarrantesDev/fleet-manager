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

    @Test
    void shouldStartPlannedTripAndPutVehicleOnTrip() {
        Vehicle vehicle = new Vehicle(5, "FZA7928", "Jeep Renegade", 2018, 500);
        Driver driver = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);
        Trip trip = new Trip(1, vehicle, driver, "São Paulo", "Campinas", 100);

        trip.start();

        assertEquals(TripStatus.IN_PROGRESS, trip.getStatus());
        assertEquals(VehicleStatus.ON_TRIP, vehicle.getStatus());
    }

    @Test
    void shouldFinishTripAndMakeVehicleAvailableAgain() {
        Vehicle vehicle = new Vehicle(5, "FZA7928", "Jeep Renegade", 2018, 500);
        Driver driver = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);
        Trip trip = new Trip(1, vehicle, driver, "São Paulo", "Campinas", 100);

        trip.start();
        trip.finish();

        assertEquals(TripStatus.COMPLETED, trip.getStatus());
        assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
    }

    @Test
    void shouldRejectFinishingPlannedTrip() {
        Vehicle vehicle = new Vehicle(5, "FZA7928", "Jeep Renegade", 2018, 500);
        Driver driver = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);
        Trip trip = new Trip(1, vehicle, driver, "São Paulo", "Campinas", 100);

        assertThrows(IllegalStateException.class, trip::finish);
        assertEquals(TripStatus.PLANNED, trip.getStatus());
        assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
    }

    @Test
    void shouldRejectStartingTripTwice() {
        Vehicle vehicle = new Vehicle(5, "FZA7928", "Jeep Renegade", 2018, 500);
        Driver driver = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);
        Trip trip = new Trip(1, vehicle, driver, "São Paulo", "Campinas", 100);

        trip.start();

        assertThrows(IllegalStateException.class, trip::start);
        assertEquals(TripStatus.IN_PROGRESS, trip.getStatus());
    }

    @Test
    void shouldRejectRestartingOrRefinishingCompletedTrip() {
        Vehicle vehicle = new Vehicle(5, "FZA7928", "Jeep Renegade", 2018, 500);
        Driver driver = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);
        Trip trip = new Trip(1, vehicle, driver, "São Paulo", "Campinas", 100);

        trip.start();
        trip.finish();

        assertThrows(IllegalStateException.class, trip::start);
        assertThrows(IllegalStateException.class, trip::finish);
        assertEquals(TripStatus.COMPLETED, trip.getStatus());
        assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
    }

    @Test
    void shouldRejectStartingTripWhenVehicleIsAlreadyOnTrip() {
        Vehicle vehicle = new Vehicle(5, "FZA7928", "Jeep Renegade", 2018, 500);
        Driver driver = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);
        Trip firstTrip = new Trip(1, vehicle, driver, "São Paulo", "Campinas", 100);
        Trip secondTrip = new Trip(2, vehicle, driver, "São Paulo", "Santos", 80);

        firstTrip.start();

        assertThrows(IllegalStateException.class, secondTrip::start);
        assertEquals(TripStatus.PLANNED, secondTrip.getStatus());
        assertEquals(VehicleStatus.ON_TRIP, vehicle.getStatus());
    }

}
