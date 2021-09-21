package com.api.agendhouse.domain.fornecedor;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "FORNECEDOR")
@SequenceGenerator(name = "for_SQ", sequenceName = "FOR_SQ", allocationSize = 1)
@Data
@NoArgsConstructor
public class Fornecedor {

    @Id
    @Column(name = "FOR_COD_FORN")
    @GeneratedValue(generator = "for_SQ", strategy =  GenerationType.SEQUENCE)
    private long cod;


    @Column(name = "FOR_NOME_FORN")
    private String nomeforn;


    @Column(name = "FOR_RAMO_FORN")
    private String ramo_forn;


    @Column(name = "FOR_CNPJ")
    private long cnpjforn;

    @Column(name = "CON_COD_CONT")
    private long con_cod;

}
