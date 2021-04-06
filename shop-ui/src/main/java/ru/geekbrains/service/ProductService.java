package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.model.Category;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();
    Optional<ProductRepr> findById(Long id);
    Page<ProductRepr> findWithFilter(Long categoryId, String nameFilter, Double minPrice, Double maxPrice,
                                     Integer page, Integer size, String sortBy);
}
