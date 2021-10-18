package com.maz.store.product.web.mappers;

import com.maz.store.model.product.ProductDto;
import com.maz.store.product.domain.Product;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(value = ProductMapperDecorator.class)
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    ProductDto productToProductDtoWithoutQOH(Product product);

    Product productDtoToProduct(ProductDto productDto);
}
