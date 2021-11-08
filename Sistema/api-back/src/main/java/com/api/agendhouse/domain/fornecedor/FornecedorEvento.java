package com.api.agendhouse.domain.fornecedor;

import com.api.agendhouse.domain.evento.Evento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "FORNECEDOR_EVENTO")
@Data
public class FornecedorEvento {

    @EmbeddedId
    FornecedorEventoKey id;

    @ManyToOne
    @MapsId("codeven")
    @JoinColumn(name = "EVE_COD_EVEN")
    @JsonIgnore
    private Evento evento;

    @MapsId("codeven")
    @Column(name="EVE_COD_EVEN", insertable=false, updatable=false)
    private Long codeven;

    @ManyToOne
    @MapsId("forncod")
    @JoinColumn(name = "FOR_COD_FORN")
    @JsonIgnore
    private Fornecedor fornecedor;

    @MapsId("forncod")
    @Column(name="FOR_COD_FORN", insertable=false, updatable=false)
    private Long forncod;

    @Column(name="FON_DESCRICAO")
    String descricao;

}
