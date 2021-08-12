package com.maz.store.product.web.mappers;

import com.maz.store.model.ProductDto;
import com.maz.store.product.domain.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);

}
