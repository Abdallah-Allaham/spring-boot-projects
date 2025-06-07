package com.abdallah.product_api.service;

import com.abdallah.product_api.model.Product;
import com.abdallah.product_api.repo.ProductRepo;
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

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(int id){
        return productRepo.findById(id);
    }

    public Product createProduct(Product product){
        return productRepo.save(product);
    }

    public boolean deleteProduct(int id){
        if (productRepo.existsById(id)){
            productRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Product> updateProduct(int id,Product updatedProduct){
        return productRepo.findById(id).map(product -> {
           product.setName(updatedProduct.getName());
           product.setDescription(updatedProduct.getDescription());
           product.setPrice(updatedProduct.getPrice());
           return productRepo.save(product);
        });
    }
}
