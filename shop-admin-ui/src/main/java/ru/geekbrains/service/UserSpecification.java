package ru.geekbrains.service;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.model.User;

public final class UserSpecification {

    public static Specification<User> usernameLike(String name) {
        return (root, query, cb) -> cb.like(root.get("username"), "%" + name + "%");
    }

    public static Specification<User> minAge(Integer minAge) {
        return (root, query, cb) -> cb.ge(root.get("age"), minAge);
    }

    public static Specification<User> maxAge(Integer maxAge) {
        return (root, query, cb) -> cb.le(root.get("age"), maxAge);
    }
}
