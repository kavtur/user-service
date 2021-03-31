package by.issoft.sample.persistence;

import by.issoft.sample.domain.Order;

import java.util.List;
import java.util.UUID;

public interface OrderStorage {

    void persist(Order order);

    Order loadById(UUID id);

    List<Order> loadByUserId(UUID userId);

}
