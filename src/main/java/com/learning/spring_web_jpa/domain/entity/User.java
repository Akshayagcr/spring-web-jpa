package com.learning.spring_web_jpa.domain.entity;

import com.learning.spring_web_jpa.domain.enums.Gender;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Generated;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.sql.Timestamp;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_seq_gen")
    @SequenceGenerator(
            name = "app_user_seq_gen",
            sequenceName = "app_user_id_seq", // existing sequence created by SERIAL
            allocationSize = 1                // must match sequence increment in DB
    )
    private Long id;

    @Version
    private Long version;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "gender")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Gender gender;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Generated(GenerationTime.INSERT)   // DB Generated. Use @CreationTimestamp for Hibernate generated timestamp.
    @Column(name = "created_at", insertable = false, updatable = false, nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp    // Hibernate generated. If we need DB managed then we need a DB trigger
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
