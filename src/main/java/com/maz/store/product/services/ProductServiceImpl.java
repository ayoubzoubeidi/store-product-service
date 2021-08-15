package com.maz.store.product.services;

import com.maz.store.model.ProductDto;
import com.maz.store.product.domain.Product;
import com.maz.store.product.repositories.ProductRepository;
import com.maz.store.product.web.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    @Override
    public ProductDto saveProduct(ProductDto productDto) {

        productDto.setId(null);

        Product savedProduct = productRepository.save(productMapper.productDtoToProduct(productDto)).block();

        return productMapper.productToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProduct(UUID productId) {

        Optional<Product> optionalProduct = productRepository.findById(productId).blockOptional();

        Product product = optionalProduct
                .orElseThrow(() -> new RuntimeException("Product Not Found With Id: " + productId));

        return productMapper.productToProductDto(product);

    }

    @Override
    public List<ProductDto> getAllProducts(Pageable pageable) {

        return  productRepository.findAll()
                .take(pageable.getPageSize())
                .skip(pageable.getOffset())
                .map(productMapper::productToProductDto)
                .collectList().block();

    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {

        Optional<Product> optionalProduct = productRepository.findById(productDto.getId()).blockOptional();

        Product savedProduct = optionalProduct
                .orElseThrow(() -> new RuntimeException("Product Not Found With Id: " + productDto.getId()));

        Product product = productMapper.productDtoToProduct(productDto);

        savedProduct.setCategory(product.getCategory());
        savedProduct.setLabel(product.getLabel());
        savedProduct.setCost(product.getCost());

        return productMapper.productToProductDto(productRepository.save(savedProduct).block());
    }

    @Override
    public void deleteProduct(UUID productIt) {

        productRepository.deleteById(productIt).block();

    }
}
