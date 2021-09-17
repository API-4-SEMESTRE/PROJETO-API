package com.api.name.domain.fornecedor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ENDERECO")
@Data
@NoArgsConstructor
public class Endereco {

    @Column(name = "END_RUA")
    public String rua_end;

    @Column(name = "END_BAIRRO")
    private String bairro_end;

    @Column(name = "END_CEP")
    private String cep_end;

    @Column(name = "END_CIDADE")
    private String cidade_end;

    @Column(name = "END_ESTADO")
    private String estado_end;

    @Id
    @Column(name = "FOR_COD_FORN")
    private long con_cod;
}
