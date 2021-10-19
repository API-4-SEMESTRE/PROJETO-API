package com.api.agendhouse.domain.fornecedor;

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
    @GeneratedValue(generator = "Con_SQ", strategy =  GenerationType.SEQUENCE)
    private long concod;

    @Column(name = "CON_NOME")
    private String nomecon;

    @Column(name = "CON_FUNCAO")
    private String func_con;

    @Column(name = "CON_TELEFONE")
    private String tel_con;

    @Column(name = "CON_EMAIL")
    private String email_con;

    @Column(name = "FOR_COD_FORN")
    private long fornecod;


}
