package com.solidvessel.payment.infra.adapter.payment.db;

import com.solidvessel.payment.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.payment.domain.payment.model.Payment;
import com.solidvessel.payment.domain.payment.port.PaymentPort;
import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import com.solidvessel.payment.infra.adapter.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.infra.adapter.payment.db.entity.ProductEmbeddable;
import com.solidvessel.payment.infra.adapter.payment.db.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentDBAdapter implements PaymentPort {

    private final PaymentRepository paymentRepository;

    @Override
    public List<PaymentDataModel> getAll() {
        return paymentRepository.findAll().stream().map(PaymentJpaEntity::toDataModel).toList();
    }

    @Override
    public PaymentDataModel getById(Long id) {
        PaymentJpaEntity paymentJpaEntity = getJpaEntityById(id);
        return paymentJpaEntity.toDataModel();
    }

    @Override
    public List<PaymentDataModel> getByCustomerId(Long customerId) {
        return paymentRepository.findByCustomerId(customerId).stream().map(PaymentJpaEntity::toDataModel).toList();
    }

    @Override
    public Long save(Payment payment) {
        PaymentJpaEntity paymentJpaEntity = paymentRepository.save(PaymentJpaEntity.from(payment));
        return paymentJpaEntity.getId();
    }

    @Override
    public List<ProductDataModel> getProductsOfPayment(Long paymentId) {
        PaymentJpaEntity paymentJpaEntity = getJpaEntityById(paymentId);
        return paymentJpaEntity.getProducts().stream().map(ProductEmbeddable::toDataModel).toList();
    }

    private PaymentJpaEntity getJpaEntityById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment not found!"));
    }
}
