package dev.pedroarthur.fleetmanager.repository;

import dev.pedroarthur.fleetmanager.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRepositoryTest {

    private VehicleRepository repository;

    @BeforeEach
    void setUp() {
        repository = new VehicleRepository();
    }

    @Test
    void shouldFindRegisteredVehicleById() {
        Vehicle vehicle = new Vehicle(1, "ABC1D23", "Volvo FH", 2022, 25000);
        repository.register(vehicle);

        assertSame(vehicle, repository.findById(1).orElseThrow());
    }

    @Test
    void shouldReturnEmptyWhenIdIsUnknown() {
        assertTrue(repository.findById(99).isEmpty());
    }

    @Test
    void shouldListVehiclesInRegistrationOrder() {
        Vehicle first = new Vehicle(2, "ABC1D23", "Volvo FH", 2022, 25000);
        Vehicle second = new Vehicle(1, "XYZ9K87", "Scania R", 2020, 30000);
        repository.register(first);
        repository.register(second);

        assertEquals(List.of(first, second), repository.findAll());
    }

    @Test
    void shouldReturnEmptyListWhenNothingIsRegistered() {
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void shouldRejectDuplicateId() {
        repository.register(new Vehicle(1, "ABC1D23", "Volvo FH", 2022, 25000));

        assertThrows(IllegalArgumentException.class,
                () -> repository.register(new Vehicle(1, "XYZ9K87", "Scania R", 2020, 30000)));
    }

    @Test
    void shouldRejectNullVehicle() {
        assertThrows(NullPointerException.class, () -> repository.register(null));
    }

    @Test
    void shouldNotAllowChangingTheListedVehicles() {
        repository.register(new Vehicle(1, "ABC1D23", "Volvo FH", 2022, 25000));

        List<Vehicle> vehicles = repository.findAll();

        assertThrows(UnsupportedOperationException.class, vehicles::clear);
    }
}
