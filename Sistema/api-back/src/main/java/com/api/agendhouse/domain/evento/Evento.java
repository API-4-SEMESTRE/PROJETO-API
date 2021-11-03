package com.api.agendhouse.domain.evento;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
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

    @JsonFormat(pattern = "dd/MM/yyyy")
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

    @JsonFormat(pattern = "dd/MM/yy HH:mm:ss")
    @Column(name="EVE_DATA_CRIA")
    private LocalDateTime datacria;

    @Column(name="USU_COD_CRIA")
    private long usucodcria;

    @Column(name="USU_COD_APROVA")
    private long usucodaprova;

    @JsonFormat(pattern = "HH:mm")
    @Column (name="EVE_HORA_INICIO")
    private Time horainicio;

    @JsonFormat(pattern = "HH:mm")
    @Column (name="EVE_HORA_FIM")
    private Time horafim;

}
