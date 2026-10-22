package dev.pedroarthur.fleetmanager.model;

import java.util.Objects;

public class Driver {

    private final long id;
    private final String name;
    private final String licenseNumber;
    private final LicenseCategory licenseCategory;

    public Driver(long id, String name, String licenseNumber, LicenseCategory licenseCategory) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }

        if (licenseNumber == null || licenseNumber.isBlank()) {
            throw new IllegalArgumentException("licenseNumber must not be blank");
        }

        Objects.requireNonNull(licenseCategory, "licenseCategory must not be null");

        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.licenseCategory = licenseCategory;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LicenseCategory getLicenseCategory() {
        return licenseCategory;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public String toString() {
        return "Número de identificação do motorista: " + id + ", Nome: " + name +
                ", CNH: " + licenseNumber + "Categoria da CNH: " + licenseCategory;
    }
}
