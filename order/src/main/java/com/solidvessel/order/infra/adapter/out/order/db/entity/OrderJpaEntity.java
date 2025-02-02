package com.solidvessel.order.infra.adapter.out.order.db.entity;

import com.solidvessel.order.domain.order.datamodel.OrderDataModel;
import com.solidvessel.order.domain.order.model.Order;
import com.solidvessel.order.domain.order.model.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private OrderStatus status;

    @NotNull
    private Long customerId;

    @NotNull
    private Long paymentId;

    public OrderDataModel toDataModel() {
        return new OrderDataModel(id, status, customerId, paymentId);
    }

    public static OrderJpaEntity from(Order order) {
        return new OrderJpaEntity(order.getId(), order.getStatus(), order.getCustomerId(), order.getPaymentId());
    }
}
