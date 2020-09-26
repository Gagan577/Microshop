package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Product;
import com.shopping.paymentservice.remote.IAsyncRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Value("${inventoryServiceUrl}")
    private String inventoryServiceUrl;

    private IAsyncRequestService requestService;

    public ProductService(final IAsyncRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public List<Product> getProductsOfPayment(final long paymentId) {
        Product[] products = requestService.createRequest(inventoryServiceUrl)
                .toPath("/products/ofPayment/" + paymentId)
                .withResponseType(Product[].class)
                .send();
        return Arrays.asList(products);
    }
}
