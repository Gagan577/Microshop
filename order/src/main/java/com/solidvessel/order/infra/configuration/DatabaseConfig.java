package com.solidvessel.order.infra.configuration;

import com.solidvessel.order.domain.order.model.OrderStatus;
import com.solidvessel.order.infra.adapter.out.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.infra.adapter.out.order.db.repository.OrderRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    InitializingBean seedDatabase(final OrderRepository orderRepository) {
        return () -> {
            orderRepository.save(new OrderJpaEntity(null, OrderStatus.DELIVERED, 1L, 1L));
            orderRepository.save(new OrderJpaEntity(null, OrderStatus.ON_THE_WAY, 2L, 2L));
        };
    }
}
