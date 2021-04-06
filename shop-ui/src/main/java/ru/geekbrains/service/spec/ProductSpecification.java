package ru.geekbrains.service.spec;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.model.Product;

public final class ProductSpecification {

    public static Specification<Product> nameLike(String nameFilter) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + nameFilter + "%");
    }

    public static Specification<Product> minPrice(Double minPrice) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice); // >= ge
    }

    public static Specification<Product> maxPrice(Double maxPrice) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice); // <= le
    }

    public static Specification<Product> category(Long categoryId) {
        return (root, query, cb) -> cb.equal(root.get("category_id"), categoryId);
    }

}
