package com.solidvessel.payment.infra.adapter.out.cart.db.repository;

import com.solidvessel.payment.infra.adapter.out.cart.db.entity.CartJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartJpaEntity, Long> {

    Optional<CartJpaEntity> findByCustomerId(Long customerId);
}
