package com.demo.springboot.SpringBootApp.securityExample;

import com.demo.springboot.SpringBootApp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
//    User save(User user);

    @Query("SELECT new User(t.id, t.userName, " +
            "t.password, t.active, t.role_) " +
            "FROM User t where t.userName = :userName")
    Optional<User> findByUserName(@Param("userName") String userName);
}
