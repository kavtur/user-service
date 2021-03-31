package by.issoft.sample.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID id;

    public Order() {
        this.id = UUID.randomUUID();
    }

    private final List<OrderItem> orderItems = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public List<OrderItem> getOrderItems() {
        return List.copyOf(orderItems);
    }

}
