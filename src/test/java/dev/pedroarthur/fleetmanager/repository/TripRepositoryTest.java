package dev.pedroarthur.fleetmanager.repository;

import dev.pedroarthur.fleetmanager.model.Driver;
import dev.pedroarthur.fleetmanager.model.LicenseCategory;
import dev.pedroarthur.fleetmanager.model.Trip;
import dev.pedroarthur.fleetmanager.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TripRepositoryTest {

    private TripRepository repository;
    private Vehicle vehicle;
    private Driver driver;

    @BeforeEach
    void setUp() {
        repository = new TripRepository();
        vehicle = new Vehicle(1, "ABC1D23", "Volvo FH", 2022, 25000);
        driver = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.E);
    }

    @Test
    void shouldFindRegisteredTripById() {
        Trip trip = new Trip(1, vehicle, driver, "São Paulo", "Campinas", 95);
        repository.register(trip);

        assertSame(trip, repository.findById(1).orElseThrow());
    }

    @Test
    void shouldReturnEmptyWhenIdIsUnknown() {
        assertTrue(repository.findById(99).isEmpty());
    }

    @Test
    void shouldListAllRegisteredTrips() {
        Trip first = new Trip(1, vehicle, driver, "São Paulo", "Campinas", 95);
        Trip second = new Trip(2, vehicle, driver, "Campinas", "Santos", 160);
        repository.register(first);
        repository.register(second);

        assertEquals(List.of(first, second), repository.findAll());
    }

    @Test
    void shouldRejectDuplicateId() {
        repository.register(new Trip(1, vehicle, driver, "São Paulo", "Campinas", 95));

        assertThrows(IllegalArgumentException.class,
                () -> repository.register(new Trip(1, vehicle, driver, "Campinas", "Santos", 160)));
    }

    @Test
    void shouldRejectNullTrip() {
        assertThrows(NullPointerException.class, () -> repository.register(null));
    }
}
