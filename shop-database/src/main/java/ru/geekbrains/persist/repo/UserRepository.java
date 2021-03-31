package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    List<User> findUserByLoginLike(String login);

    Optional<User> findUserByLogin(String login);

    @Query("select u from User u " +
            "where (u.login like :login or :login is null) and " +
            "      (u.age >= :minAge or :minAge is null) and " +
            "      (u.age <= :maxAge or :maxAge is null)")
    List<User> findWithFilter(@Param("login") String loginFilter,
                              @Param("minAge") Integer minAge,
                              @Param("maxAge") Integer maxAge);

}
