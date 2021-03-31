package by.issoft.sample.domain;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Vehicle {

    private final String vin;
    private final String model;
    private final String brand;
    private final LocalDate prodDate;

    protected Vehicle(String vin, String model, String brand, LocalDate prodDate) {
        this.vin = vin;
        this.model = model;
        this.brand = brand;
        this.prodDate = prodDate;
    }

    public String getVin() {
        return vin;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public LocalDate getProdDate() {
        return prodDate;
    }

    public String getInfo() {
        return this.getModel() + " " + this.getBrand() + " " + this.getProdDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vin, vehicle.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    public abstract boolean allowChildren();

    public static Vehicle truckOf(String vin, String model, String brand, LocalDate prodDate, int weight) {
        return new Truck(vin, model, brand, prodDate, weight);
    }

}
