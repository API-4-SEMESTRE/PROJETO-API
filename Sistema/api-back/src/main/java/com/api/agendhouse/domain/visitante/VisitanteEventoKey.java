package com.api.agendhouse.domain.visitante;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class VisitanteEventoKey implements Serializable {

    @Column(name="EVE_COD_EVEN")
    private Long codeven;

    @Column(name="VIS_COD_VISI")
    private Long viscod;
}
