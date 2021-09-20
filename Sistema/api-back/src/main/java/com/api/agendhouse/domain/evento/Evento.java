package com.api.agendhouse.domain.evento;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="EVENTO")
@SequenceGenerator(name = "EVE_SQ", sequenceName = "EVE_SQ", allocationSize = 1)
@Data
public class Evento {

    @Id
    @Column(name="EVE_COD_EVEN")
    @GeneratedValue(generator = "EVE_SQ", strategy = GenerationType.SEQUENCE)
    private long cod_even;

    @Column(name="EVE_DATA_EVEN")
    private Date data_even;

    @Column(name="EVE_FORMATO")
    private String formato;

    //verificar com PO e cliente sobre os tipos de eventos
    @Column(name="EVE_TIPO")
    //@Enumerated(EnumType.STRING)
    private String tipo;

    @Column(name="EVE_STATUS")
    private String status;

    @Column(name="EVE_DATA_CRIA")
    private Date data_cria;

    @Column(name="USU_COD_CRIA")
    private long usu_cod_cria;

    @Column(name="USU_COD_APROVA")
    private long usu_cod_aprova;

}
