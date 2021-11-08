package com.api.agendhouse.domain.visitante;

import com.api.agendhouse.domain.evento.Evento;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="VISITANTE_EVENTO")
@Data
public class VisitanteEvento {

    @EmbeddedId
    VisitanteEventoKey id;

    @ManyToOne
    @MapsId("codeven")
    @JoinColumn(name="EVE_COD_EVEN")
    @JsonIgnore
    private Evento evento;

    @MapsId("codeven")
    @Column(name="EVE_COD_EVEN", insertable=false, updatable=false)
    private Long codeven;

    @ManyToOne
    @MapsId("viscod")
    @JoinColumn(name="VIS_COD_VISI")
    @JsonIgnore
    private Visitante visitante;

    @MapsId("viscod")
    @Column(name="VIS_COD_VISI", insertable=false, updatable=false)
    private Long viscod;
}
