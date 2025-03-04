package com.relationship.entity;

import com.relationship.enums.ApiName;
import com.relationship.enums.EncryptType;
import com.relationship.enums.ParamName;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Parameters extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "param_name")
    private ParamName paramName;

    @Enumerated(EnumType.STRING)
    @Column(name = "encrypt_type")
    private EncryptType encryptType;

    @Column(name = "param_unit")
    private String paramUnit;

    @Column(name = "param_value")
    private String paramValue;

}
