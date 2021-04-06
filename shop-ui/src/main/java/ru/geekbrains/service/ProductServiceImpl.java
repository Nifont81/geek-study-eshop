package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.service.spec.ProductSpecification;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private final PictureService pictureService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PictureService pictureService) {
        this.productRepository = productRepository;
        this.pictureService = pictureService;
    }

    @Override
    @Transactional
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ProductRepr> findById(Long id) {
        return productRepository.findById(id).map(ProductRepr::new);
    }

    @Override
    public Page<ProductRepr> findWithFilter(Long categoryId, String nameFilter, Double minPrice, Double maxPrice,
                                            Integer page, Integer size, String sortBy) {

        Specification<Product> specification = Specification.where(null);

        if (categoryId != null) {
            specification = specification.and(ProductSpecification.category(categoryId));
        }

        if (nameFilter != null && !nameFilter.isBlank()) {
            specification = specification.and(ProductSpecification.nameLike(nameFilter));
        }

        if (minPrice != null) {
            specification = specification.and(ProductSpecification.minPrice(minPrice));
        }

        if (maxPrice != null) {
            specification = specification.and(ProductSpecification.maxPrice(maxPrice));
        }

        return productRepository.findAll(specification, PageRequest.of(page, size,
                Sort.Direction.ASC, sortBy))
                .map(ProductRepr::new);
    }
}
