package com.api.agendhouse.domain.espaco;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="ESPACO")
@SequenceGenerator(name = "ESP_SQ", sequenceName = "ESP_SQ", allocationSize = 1)
@Data
public class Espaco {

    @Id
    @Column(name="ESP_COD_ESPA")
    @GeneratedValue(generator = "ESP_SQ", strategy = GenerationType.SEQUENCE)
    private long cod_esp;

    @Column(name="ESP_NOME_ESPA")
    @Enumerated(EnumType.STRING)
    private EspacoNome esp_nome;
    
    @Column(name="ESP_CAPA_ESPA")
    private int esp_capa;

}