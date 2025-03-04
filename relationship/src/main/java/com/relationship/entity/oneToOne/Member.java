package com.relationship.entity.oneToOne;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "public", name = "member")
@Getter
@Setter
@ToString
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    @OneToOne(mappedBy = "member")
    @ToString.Exclude
    private Locker locker;
}
