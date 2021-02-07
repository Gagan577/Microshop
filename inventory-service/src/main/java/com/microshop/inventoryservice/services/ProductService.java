package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.repositories.IProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(final Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Product> findByIds(List<Long> ids) {
        return ids.stream().map(id -> productRepository.findById(id).orElse(null)).collect(Collectors.toList());
    }

    @Override
    public Product save(final Product product) {
        return productRepository.save(product);
    }
}
