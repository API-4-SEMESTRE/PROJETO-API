package com.api.agendhouse.domain.evento;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="EVENTO")
@SequenceGenerator(name = "EVE_SQ", sequenceName = "EVE_SQ", allocationSize = 1)
@Data
public class Evento {

    @Id
    @Column(name="EVE_COD_EVEN")
    @GeneratedValue(generator = "EVE_SQ", strategy = GenerationType.SEQUENCE)
    private long codeven;

    @Column(name="EVE_DATA_EVEN")
    private Date dataeven;

    @Column(name="EVE_FORMATO")
    private String formato;

    @Column(name="EVE_TIPO")
    @Enumerated(EnumType.STRING)
    private EventoTipo tipo;

    @Column(name="EVE_STATUS")
    @Enumerated(EnumType.STRING)
    private EventoStatus status;

    @Column(name="EVE_DATA_CRIA")
    private LocalDateTime datacria;

    @Column(name="USU_COD_CRIA")
    private long usucodcria;

    @Column(name="USU_COD_APROVA")
    private long usucodaprova;

}
