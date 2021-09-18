package com.api.name.domain.fornecedor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CONTATO")
@SequenceGenerator(name = "Con_SQ", sequenceName = "CON_SQ", allocationSize = 1)
@Data
public class Contato {
    @Id
    @Column(name = "CON_COD_CONT")
    //@GeneratedValue(generator = "FOR_SQ", strategy =  GenerationType.SEQUENCE)
    private long concod;

    @Column(name = "CON_NOME")
    private String nome_con;

    @Column(name = "CON_FUNCAO")
    private String func_con;

    public Contato(){}
}
