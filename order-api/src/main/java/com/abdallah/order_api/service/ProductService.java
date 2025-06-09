package com.abdallah.order_api.service;

import com.abdallah.order_api.model.Product;
import com.abdallah.order_api.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product createProduct(Product product){
        return productRepo.save(product);
    }

    public Optional<Product> getProductById(Long id){
        return productRepo.findById(id);
    }

    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }

    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }

    public Optional<Product> updateProduct(Long id,Product updatedProduct){
        Optional<Product> productOptional=productRepo.findById(id);

        if(productOptional.isPresent()){
            Product product = productOptional.get();

            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setQuantity(updatedProduct.getQuantity());
            product.setPrice(updatedProduct.getPrice());
            product.setImageUrl(updatedProduct.getImageUrl());

            return Optional.of(productRepo.save(product));
        }

        return Optional.empty();
    }

    public Optional<Product> updateQuantity(Long id,Integer newQuantity){
        Optional<Product> productOptional=productRepo.findById(id);

        if(productOptional.isPresent()){
            Product product = productOptional.get();
            product.setQuantity(newQuantity);
            return Optional.of(productRepo.save(product));
        }

        return Optional.empty();
    }
}
