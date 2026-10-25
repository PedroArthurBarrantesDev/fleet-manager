package dev.pedroarthur.fleetmanager.repository;

import dev.pedroarthur.fleetmanager.model.Driver;
import dev.pedroarthur.fleetmanager.model.LicenseCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverRepositoryTest {

    private DriverRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DriverRepository();
    }

    @Test
    void shouldFindRegisteredDriverById() {
        Driver driver = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);
        repository.register(driver);

        assertSame(driver, repository.findById(1).orElseThrow());
    }

    @Test
    void shouldReturnEmptyWhenIdIsUnknown() {
        assertTrue(repository.findById(99).isEmpty());
    }

    @Test
    void shouldListAllRegisteredDrivers() {
        Driver rafael = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);
        Driver ana = new Driver(2, "Ana Souza", "4410", LicenseCategory.E);
        repository.register(rafael);
        repository.register(ana);

        assertEquals(List.of(rafael, ana), repository.findAll());
    }

    @Test
    void shouldRejectDuplicateId() {
        repository.register(new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B));

        assertThrows(IllegalArgumentException.class,
                () -> repository.register(new Driver(1, "Ana Souza", "4410", LicenseCategory.E)));
    }

    @Test
    void shouldReplaceDriverWithSameId() {
        // prepare: a registered driver (same as the original)
        Driver original = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);
        repository.register(original);

        // act: create another Driver with the SAME id and different data, and call update
        Driver updated = new Driver(1, "Julia Alves", "3958", LicenseCategory.B);
        repository.update(updated);

        // check: what should findById(1) return now, original or updated?
        assertSame(updated, repository.findById(1).orElseThrow());
    }


    @Test
    void shouldRejectUpdateOfUnknownId() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.update(new Driver(1, "Ana Souza", "4410", LicenseCategory.E)));
    }


    @Test
    void shouldRejectNullDriver() {
        assertThrows(NullPointerException.class, () -> repository.register(null));
    }
}
