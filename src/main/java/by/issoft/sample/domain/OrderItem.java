package by.issoft.sample.domain;

import java.util.UUID;

public class OrderItem {

    private final UUID id;

    private final UUID orderId;

    private final UUID productId;

    public OrderItem(UUID orderId, UUID productId) {
        this.id = UUID.randomUUID();
        this.orderId = orderId;
        this.productId = productId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getProductId() {
        return productId;
    }
}
