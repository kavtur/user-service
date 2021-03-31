package by.issoft.sample.domain;

import java.time.LocalDate;

public class Car extends Vehicle {

    public static final int DEFAULT_NUMBER_OF_PASSANGERS = 4;

    private final int numberOfPassangers;

    public Car(String vin, String model, String brand, LocalDate prodDate) {
       this(vin, model, brand, prodDate, DEFAULT_NUMBER_OF_PASSANGERS);
    }

    public Car(String vin, String model, String brand, LocalDate prodDate, int numberOfPassangers) {
        super(vin, model, brand, prodDate);
        this.numberOfPassangers = numberOfPassangers;
    }

    public int getNumberOfPassangers() {
        return numberOfPassangers;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " " + this.getNumberOfPassangers();
    }


    @Override
    public String toString() {
        return getInfo();
    }

    @Override
    public boolean allowChildren() {
        return true;
    }
}
