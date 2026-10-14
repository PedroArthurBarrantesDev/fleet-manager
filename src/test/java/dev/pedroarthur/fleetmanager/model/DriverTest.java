package dev.pedroarthur.fleetmanager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    @Test
    void shouldCreateValidDriver() {
        Driver driver = new Driver(1, "Rafael Augusto", "3397", LicenseCategory.B);

        assertEquals(1, driver.getId());
        assertEquals("Rafael Augusto", driver.getName());
        assertEquals("3397", driver.getLicenseNumber());
        assertEquals(LicenseCategory.B, driver.getLicenseCategory());
    }

    @Test
    void shouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Driver(1, "", "3397", LicenseCategory.B));
    }

    @Test
    void shouldRejectBlankLicenseNumber() {
        assertThrows(IllegalArgumentException.class, () -> new Driver(1, "Rafael Augusto", "", LicenseCategory.B));
    }

    @Test
    void shouldRejectNullCategory() {
        assertThrows(NullPointerException.class, () -> new Driver(1, "Rafael Augusto", "3397", null));
    }

}