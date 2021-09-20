package com.api.name.domain.fornecedor;

import com.api.name.domain.evento.Evento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "FORNECE")
//@SequenceGenerator(name = "Fon_SQ", sequenceName = "FON_SQ", allocationSize = 1)
@Data
public class Fornece {

    @Column(name = "FOR_COD_FORN")
    private long forn_cod;


    @Column(name = "EVE_COD_EVEN")
    private long cod_eve;

    @Id
    @Column(name = "COD")
    private long cod;
}
