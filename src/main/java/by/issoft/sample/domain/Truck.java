package by.issoft.sample.domain;

import java.time.LocalDate;

class Truck extends Vehicle{

    private final int weight;

    public Truck(String vin, String model, String brand, LocalDate prodDate, int weight) {
        super(vin, model, brand, prodDate);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " Weight: " + this.getWeight();
    }

    @Override
    public boolean allowChildren() {
        return false;
    }

}
