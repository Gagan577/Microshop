package com.solidvessel.payment.infra.adapter.in.payment.rest;

import com.solidvessel.payment.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.payment.domain.customer.port.CustomerPort;
import com.solidvessel.payment.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.payment.domain.payment.datamodel.PaymentDetailDataModel;
import com.solidvessel.payment.domain.payment.port.PaymentPort;
import com.solidvessel.payment.domain.payment.service.AcceptPaymentCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentPort paymentPort;
    private final CustomerPort customerPort;
    private final CommandService<AcceptPaymentCommand> acceptPaymentCommandService;

    @GetMapping()
    public List<PaymentDataModel> getAll() {
        return paymentPort.getAll();
    }

    @GetMapping("/{id}")
    public PaymentDataModel getById(@PathVariable final Long id) {
        return paymentPort.getById(id);
    }

    @GetMapping("/{id}/detail")
    public PaymentDetailDataModel getDetailById(@PathVariable final Long id) {
        PaymentDataModel payment = paymentPort.getById(id);
        CustomerDataModel customer = customerPort.getCustomerOfPayment(payment.customerId());
        return PaymentDetailDataModel.from(payment, customer);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<PaymentDataModel> getByCustomerId(@PathVariable final Long customerId) {
        return paymentPort.getByCustomerId(customerId);
    }

    @PostMapping
    public OperationResult acceptPayment(final AcceptPaymentRequest request) {
        return acceptPaymentCommandService.execute(request.toCommand());
    }
}
