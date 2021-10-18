package com.maz.store.product.services;

import com.maz.store.model.product.ProductDto;
import com.maz.store.product.domain.Category;
import com.maz.store.product.domain.Product;
import com.maz.store.product.repositories.ProductRepository;
import com.maz.store.product.web.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.cms.OriginatorIdentifierOrKey;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Mono<UUID> saveProduct(Mono<ProductDto> productDto) {

        return productDto
                .map(product -> {
                    product.setId(UUID.randomUUID());
                    return productMapper.productDtoToProduct(product);
                })
                .flatMap(productRepository::save)
                .map(product -> productMapper.productToProductDtoWithoutQOH(product).getId());
    }

    @Override
    public Mono<ProductDto> getProduct(UUID productId, Boolean getQOH) {
        return productRepository.findById(productId)
                .map(productMapper::productToProductDto);


    }

    @Override
    public Flux<ProductDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll()
                .map(productMapper::productToProductDto);
    }


    @Override
    public Mono<Void> updateProduct(Mono<ProductDto> productDto) {


        return productDto
                .flatMap(dto -> productRepository.findById(dto.getId())
                        .flatMap(savedProduct -> {
                            savedProduct.setCategory(Category.valueOf(dto.getCategory()));
                            savedProduct.setLabel(dto.getLabel());
                            savedProduct.setCost(dto.getCost());
                            savedProduct.setUpc(dto.getUpc());
                            return productRepository.save(savedProduct);
                        }))
                .then();

    }

    @Override
    public Mono<UUID> validateInventory(String upc) {
        Mono<Product> product = productRepository.findByUpc(upc);
        return product.map(Product::getId);
    }

    @Override
    public Mono<Void> deleteProduct(UUID productIt) {
        return productRepository.deleteById(productIt);
    }
}
