package dev.pedroarthur.fleetmanager.report;

import dev.pedroarthur.fleetmanager.model.Trip;
import dev.pedroarthur.fleetmanager.model.TripStatus;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Builds reports from a collection of trips using Java Streams.
 * It does not know where the trips come from, so it works with any storage.
 */
public class TripReportService {

    /** Total kilometers driven by each vehicle, keyed by plate and sorted alphabetically. */
    public Map<String, Double> totalKmPerVehicle(Collection<Trip> trips) {
        Objects.requireNonNull(trips, "trips must not be null");

        return trips.stream()
                .collect(Collectors.groupingBy(
                        trip -> trip.getVehicle().getPlate(),
                        TreeMap::new,
                        Collectors.summingDouble(Trip::getDistanceKm)));
    }

    /** Number of trips made by each driver, keyed by name and sorted alphabetically. */
    public Map<String, Long> tripsPerDriver(Collection<Trip> trips) {
        Objects.requireNonNull(trips, "trips must not be null");

        return trips.stream()
                .collect(Collectors.groupingBy(
                        trip -> trip.getDriver().getName(),
                        TreeMap::new,
                        Collectors.counting()));
    }

    /** Number of trips in each status. Every status is present, with 0 when there are none. */
    public Map<TripStatus, Long> tripsPerStatus(Collection<Trip> trips) {
        Objects.requireNonNull(trips, "trips must not be null");

        Map<TripStatus, Long> result = new EnumMap<>(TripStatus.class);
        for (TripStatus status : TripStatus.values()) {
            result.put(status, 0L);
        }
        trips.stream()
                .collect(Collectors.groupingBy(Trip::getStatus, Collectors.counting()))
                .forEach(result::put);
        return result;
    }

    /** Sum of the distance of all trips, in kilometers. */
    public double totalKm(Collection<Trip> trips) {
        Objects.requireNonNull(trips, "trips must not be null");

        return trips.stream()
                .mapToDouble(Trip::getDistanceKm)
                .sum();
    }

    /** Plate of the vehicle with the most kilometers, or empty when there are no trips. */
    public Optional<String> vehicleWithMostKm(Collection<Trip> trips) {
        return totalKmPerVehicle(trips).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
