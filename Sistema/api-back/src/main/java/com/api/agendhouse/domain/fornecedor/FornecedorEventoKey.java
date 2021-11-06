package com.api.agendhouse.domain.fornecedor;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class FornecedorEventoKey implements Serializable {

    @Column(name="eve_cod_even")
    private Long codeven;

    @Column(name="for_cod_forn")
    private Long forncod;
}
