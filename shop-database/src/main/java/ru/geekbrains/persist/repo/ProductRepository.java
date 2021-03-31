package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

//    List<Product> findProductByNameLike(String nameFilter);

//    @Query("select p from Product p where p.name like concat('%',:nameFilter,'%')")
//    List<Product> findProductByNameLike(@Param("nameFilter") String nameFilter);
//
//    @Query("select p from Product p where p.price between :minPrice and :maxPrice")
//    List<Product> findProductByPriceIn(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    @Query("select p from Product p where " +
            "(p.title like :titleFilter or :titleFilter is null) and " +
            "(p.price >= :minPrice or :minPrice is null) and " +
            "(p.price <= :maxPrice or :maxPrice is null)")
    List<Product> findWithFilter(@Param("titleFilter") String titleFilter,
                                 @Param("minPrice") Double minPrice,
                                 @Param("maxPrice") Double maxPrice);
}
