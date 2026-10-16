package dev.pedroarthur.fleetmanager.report;

import dev.pedroarthur.fleetmanager.model.Driver;
import dev.pedroarthur.fleetmanager.model.LicenseCategory;
import dev.pedroarthur.fleetmanager.model.Trip;
import dev.pedroarthur.fleetmanager.model.TripStatus;
import dev.pedroarthur.fleetmanager.model.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TripReportServiceTest {

    private final TripReportService reportService = new TripReportService();

    private final Vehicle kicks = new Vehicle(1, "ABC0123", "Nissan Kicks", 2018, 500);
    private final Vehicle renegade = new Vehicle(2, "FZA7928", "Jeep Renegade", 2018, 500);

    private final Driver janaina = new Driver(1, "Janaina Ferreira", "7839", LicenseCategory.D);
    private final Driver rafael = new Driver(2, "Rafael Augusto", "3397", LicenseCategory.B);

    private final List<Trip> trips = List.of(
            new Trip(1, kicks, janaina, "São Paulo", "Santos", 80),
            new Trip(2, kicks, rafael, "Santos", "Campinas", 170.5),
            new Trip(3, renegade, janaina, "São Paulo", "Natal", 2900),
            new Trip(4, kicks, janaina, "Campinas", "São Paulo", 95)
    );

    @Test
    void shouldSumKilometersPerVehicle() {
        Map<String, Double> kmPerVehicle = reportService.totalKmPerVehicle(trips);

        assertEquals(2, kmPerVehicle.size());
        assertEquals(345.5, kmPerVehicle.get("ABC0123"), 0.001);
        assertEquals(2900, kmPerVehicle.get("FZA7928"), 0.001);
    }

    @Test
    void shouldCountTripsPerDriver() {
        Map<String, Long> tripsPerDriver = reportService.tripsPerDriver(trips);

        assertEquals(Map.of("Janaina Ferreira", 3L, "Rafael Augusto", 1L), tripsPerDriver);
    }

    @Test
    void shouldCountTripsPerStatusIncludingEmptyOnes() {
        Map<TripStatus, Long> tripsPerStatus = reportService.tripsPerStatus(trips);

        assertEquals(4L, tripsPerStatus.get(TripStatus.PLANNED));
        assertEquals(0L, tripsPerStatus.get(TripStatus.IN_PROGRESS));
        assertEquals(0L, tripsPerStatus.get(TripStatus.COMPLETED));
    }

    @Test
    void shouldSumTotalKilometers() {
        assertEquals(3245.5, reportService.totalKm(trips), 0.001);
    }

    @Test
    void shouldFindVehicleWithMostKilometers() {
        assertEquals(Optional.of("FZA7928"), reportService.vehicleWithMostKm(trips));
    }

    @Test
    void shouldReturnEmptyReportsWhenThereAreNoTrips() {
        List<Trip> noTrips = List.of();

        assertTrue(reportService.totalKmPerVehicle(noTrips).isEmpty());
        assertTrue(reportService.tripsPerDriver(noTrips).isEmpty());
        assertEquals(0, reportService.totalKm(noTrips));
        assertEquals(Optional.empty(), reportService.vehicleWithMostKm(noTrips));
    }

    @Test
    void shouldRejectNullTrips() {
        assertThrows(NullPointerException.class, () -> reportService.totalKmPerVehicle(null));
        assertThrows(NullPointerException.class, () -> reportService.tripsPerDriver(null));
        assertThrows(NullPointerException.class, () -> reportService.tripsPerStatus(null));
        assertThrows(NullPointerException.class, () -> reportService.totalKm(null));
    }
}
