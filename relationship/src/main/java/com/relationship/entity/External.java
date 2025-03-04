package com.relationship.entity;

import com.relationship.enums.ApiName;
import com.relationship.enums.EncryptType;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class External extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_name")
    private ApiName apiName;

    @Column(name = "external_code")
    private String externalCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "encrypt_type")
    private EncryptType encryptType;

    @Column(name = "response_body")
    private String responseBody;
}
