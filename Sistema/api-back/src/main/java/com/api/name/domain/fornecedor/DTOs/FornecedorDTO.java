package com.api.name.domain.fornecedor.DTOs;

import com.api.name.domain.fornecedor.Contato;
import com.api.name.domain.fornecedor.Fornecedor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
public class FornecedorDTO {
    private long cod;
    private String nome_forn;
    private String ramo_forn;
    private long cnpj_forn;
    private long con_cod;

    /*public Fornecedor changeToFornecedor(){
        return new Fornecedor(cod, nome_forn, ramo_forn, cnpj_forn, con_cod);
    }*/
}
