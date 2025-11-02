package com.learning.spring_web_jpa.domain.entity;

import com.learning.spring_web_jpa.domain.enums.Gender;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GeneratedColumn;

import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Version
    private Long version;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "gender", columnDefinition = "ENUM('MALE', 'FEMALE', 'OTHER')")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    /*
        INSERT ... RETURNING SQL statement is not supported in MySQL. Postgres supports it.
        So when creating user will get null in response for field createdAt & updatedAt.
        To fetch createdAt & updatedAt we need to make additional select query or use @GeneratedColumn
     */
    @Column(name = "created_at", insertable = false, updatable = false, nullable = false)
    @GeneratedColumn(value = "INSERT")
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, nullable = false)
    @GeneratedColumn(value = "ALWAYS")
    private Timestamp updatedAt;
}
