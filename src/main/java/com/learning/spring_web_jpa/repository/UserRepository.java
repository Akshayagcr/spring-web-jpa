package com.learning.spring_web_jpa.repository;

import com.learning.spring_web_jpa.domain.dto.UserResponse;
import com.learning.spring_web_jpa.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, BigInteger> {

    @Query("""
            select new com.learning.spring_web_jpa.domain.dto.UserResponse(
            u.id,
            u.version,
            u.firstName,
            u.lastName,
            u.email,
            u.gender,
            u.city,
            u.country,
            u.createdAt,
            u.updatedAt
            )
            from User u
            where u.id = :id
            """)
    Optional<UserResponse> fetchUserById(@Param("id") BigInteger id);

    @Query("""
            select new com.learning.spring_web_jpa.domain.dto.UserResponse(
            u.id,
            u.version,
            u.firstName,
            u.lastName,
            u.email,
            u.gender,
            u.city,
            u.country,
            u.createdAt,
            u.updatedAt
            )
            from User u
            """)
    List<UserResponse> fetchAllUser();

}
