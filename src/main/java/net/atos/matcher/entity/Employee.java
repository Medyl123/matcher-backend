package net.atos.matcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "first_name"
    )
    private String firstName;

    @Column(
            name = "last_name"
    )
    private String lastName;

    @Column(
            name = "email_id",
            nullable = false, unique = true
    )
    @NaturalId(
            mutable = true
    )
    private String email;

    @Column(
            name = "code",
            nullable = false
    )
    private String code;

    @Enumerated(
            value = EnumType.STRING
    )
   private Role role;
}
