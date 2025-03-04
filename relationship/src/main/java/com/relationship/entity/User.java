package com.relationship.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
public class User extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean emailVerified;//accountVerified

    @Column(name = "max_attempt")
    private Integer maxAttempt;

    @Column(name = "mobile_number", unique = true, nullable = false, length = 10)
    private String mobileNumber;

//    @Column(precision = 4, scale = 1)
//    private BigDecimal bigNum;
//    @Lob
//    private String description;
//    @Transient
//    private int temp;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SecureToken> secureTokens = new ArrayList<>();

}
