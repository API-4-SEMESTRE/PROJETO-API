package com.api.agendhouse.domain.DTO;

import lombok.Data;

@Data
public class FornecedorDTOFilter {
    private String nome;
    private String ramo;
    private String cnpj;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;
    private String contato_nome;
    private String contato_fone;
    private String contato_email;
}
