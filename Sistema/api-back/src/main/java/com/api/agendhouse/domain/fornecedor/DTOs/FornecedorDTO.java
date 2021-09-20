package com.api.agendhouse.domain.fornecedor.DTOs;

import lombok.Data;

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
