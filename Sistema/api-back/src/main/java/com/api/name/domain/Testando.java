package com.api.name.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TESTANDOOO")
@SequenceGenerator(name = "TST_SQ", sequenceName = "TST_SQ", allocationSize = 1)
@Data
public class Testando {

    @Id
    @GeneratedValue(generator = "TST_SQ", strategy = GenerationType.SEQUENCE)
    public long id;

    @Column(name = "NAME")
    private String name;

}
